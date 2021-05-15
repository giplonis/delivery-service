import React, { useState } from "react";
import { Dialog, Select, MenuItem } from "@material-ui/core";
import "../styles/OrderModal.css";
import { ADMIN_ORDERS } from "../api/config";
import axiosInstance from "../api/axiosInstance";
import useMessage from "../hooks/messages";
import OrderModalSummary from "./OrderModalSummary";
import { getOrderStatusList } from "../services/orderStatus";
import LoadingButton from "./LoadingButton";

export default function AdminOrderModal(props) {
  const [status, setStatus] = useState(props.order.status);
  const { displayError, displaySuccess } = useMessage();
  const [isChangingStatus, setIsChangingStatus] = useState(false);

  const handleChange = (event) => {
    setStatus(event.target.value);
  };

  const handleSave = () => {
    (async function fetchData() {
      setIsChangingStatus(true);
      try {
        await axiosInstance.put(
          ADMIN_ORDERS + "/" + props.order.id + "/status",
          { status: status }
        );
        displaySuccess("Status changed");
        props.handleStatusChange(props.order.id, status);
      } catch (e) {
        displayError("Failed to change status");
      } finally {
        setIsChangingStatus(false);
      }
    })();
  };

  return (
    <Dialog open={props.open} onClose={() => props.onClose(props.order)}>
      <div className="modal-wrapper">
        <OrderModalSummary order={props.order} />
        <div className="admin-status">
          <span className="admin-modal-status">Status: </span>
          <Select
            value={status}
            onChange={handleChange}
            className="admin-modal-select"
          >
            {getOrderStatusList().map((status, index) => (
              <MenuItem value={status} key={index}>
                {status}
              </MenuItem>
            ))}
          </Select>
        </div>
        <div className="admin-modal-buttons">
          <LoadingButton
            color="primary"
            variant="contained"
            className="form-button admin-modal-save"
            onClick={handleSave}
            loading={isChangingStatus}
            disabled={isChangingStatus}
          >
            Save
          </LoadingButton>
        </div>
      </div>
    </Dialog>
  );
}
