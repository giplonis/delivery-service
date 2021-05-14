import React, { useState } from "react";
import ParcelSizeSummaryCard from "./SummaryCards/ParcelSizeSummaryCard";
import RecipientSummaryCard from "./SummaryCards/RecipientSummaryCard";
import SenderSummaryCard from "./SummaryCards/SenderSummaryCard";
import { Dialog, Select, MenuItem, Button } from "@material-ui/core";
import "../styles/OrderModal.css";
import StatusIcon from "./StatusIcon";
import { ADMIN_ORDERS } from "../api/config";
import axiosInstance from "../api/axiosInstance";
import useMessage from "../hooks/messages";

export default function OrderModal(props) {
  const [status, setStatus] = useState(props.order.status);
  const { displayError, displaySuccess } = useMessage();

  const handleChange = (event) => {
    setStatus(event.target.value);
  };

  const handleSave = () => {
    (async function fetchData() {
      try {
        await axiosInstance.put(
          ADMIN_ORDERS + "/" + props.order.id + "/status",
          { status: status }
        );
        displaySuccess("Status changed");
        props.handleStatusChange(props.order.id, status);
      } catch (e) {
        displayError("Failed to change status");
      }
    })();
  };

  return (
    <Dialog open={props.open} onClose={() => props.onClose(props.order)}>
      <div className="modal-wrapper">
        <div className="order-status-wrapper order-info-field-wrapper">
          <span className="order-status-title ">Order Status:</span>
          <StatusIcon status={props.order.status} fontSize={30} />
          <span className="description-text">{props.order.status}</span>
        </div>
        <SenderSummaryCard
          sender={props.order.senderInfo}
          pickUpDate={props.order.pickupDateTime}
        />
        <RecipientSummaryCard recipient={props.order.recipientInfo} />
        <ParcelSizeSummaryCard
          selectedPackageSize={props.order.packageOption.packageSize}
          selectedPackageType={props.order.packageOption.packageType.title}
          attributes={props.order.attributes}
          price={props.order.totalPrice}
        />
        <div className="admin-status">
          <span className="admin-modal-status">Status: </span>
          <Select
            value={status}
            onChange={handleChange}
            className="admin-modal-select"
          >
            <MenuItem value={"NEW"}>NEW</MenuItem>
            <MenuItem value={"DELIVERED"}>DELIVERED</MenuItem>
          </Select>
        </div>
        <div className="admin-modal-buttons">
          <Button variant="outlined" className="form-button admin-modal-delete">
            Delete Order
          </Button>
          <Button
            color="primary"
            variant="contained"
            className="form-button admin-modal-save"
            onClick={handleSave}
          >
            Save
          </Button>
        </div>
      </div>
    </Dialog>
  );
}
