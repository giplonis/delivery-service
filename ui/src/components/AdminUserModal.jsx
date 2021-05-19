import React, { useState, useEffect } from "react";
import {
  Dialog,
  Grid,
  List,
  ListItem,
  Divider,
  Typography,
} from "@material-ui/core";
import useMessage from "../hooks/messages";
import axiosInstance from "../api/axiosInstance";
import { ADMIN_ORDERS } from "../api/config";
import { getDateString, getDateStringNoHours } from "../services/dateFormat";
import OrderInfo from "./OrderInfo";
import StatusIcon from "./StatusIcon";
import AdminOrderModal from "./AdminOrderModal";

function AdminUserModal(props) {
  const { displayError } = useMessage();
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    (async function fetchData() {
      try {
        const response = await axiosInstance.get(
          ADMIN_ORDERS + "?senderId=" + props.user.id
        );
        setOrders(response.data);
      } catch (e) {
        displayError("Failed to load user orders.");
      }
    })();
  }, [displayError, props.user.id]);

  const [selectedOrder, setSelectedOrder] = useState(null);
  const selectOrder = (order) => {
    setSelectedOrder((prevState) => {
      if (prevState === null) return order;
      return null;
    });
  };

  const handleStatusChange = (id, status) => {
    props.handleStatusChange(id, status);
    const orderArray = [...orders];
    orderArray.find((o) => o.id === id).status = status;
    setOrders(orderArray);
  };

  return (
    <Dialog open={props.open} onClose={() => props.onClose(props.user)}>
      <div className="modal-wrapper">
        <div className="form-wrapper summary-subform-wrapper">
          <div className="form-inner form-inner-summary">
            <div className="form-header summary-subheader">Personal Info</div>
            <Grid container>
              <Grid item xs={12}>
                <ul className="summary-ul">
                  <li>
                    <span className="admin-info-label">Name:</span>{" "}
                    {props.user.firstName} {props.user.lastName}
                  </li>
                  <li>
                    <span className="admin-info-label">Address:</span>{" "}
                    {props.user.address.street}, {props.user.address.city}
                  </li>
                  <li>
                    <span className="admin-info-label">Phone Number:</span>{" "}
                    {props.user.phoneNumber}
                  </li>
                  <li>
                    <span className="admin-info-label">E-mail:</span>{" "}
                    {props.user.email}
                  </li>
                  <li>
                    <span className="admin-info-label">Orders:</span>{" "}
                    {props.user.totalOrders}
                  </li>
                  <li>
                    <span className="admin-info-label">Last Order:</span>{" "}
                    {!!props.user.lastOrderDate
                      ? getDateString(props.user.lastOrderDate)
                      : "No orders made"}
                  </li>
                  <li>
                    <span className="admin-info-label">Last Login:</span>{" "}
                    {getDateString(props.user.lastLoginDate)}
                  </li>
                </ul>
              </Grid>
            </Grid>
          </div>
        </div>
        <div className="form-wrapper summary-subform-wrapper">
          <div className="form-inner form-inner-admin-orders">
            <div className="form-header summary-subheader">Orders</div>
            <List className="admin-user-orders-list">
              {orders.length > 0 ? (
                orders.map((order, index) => (
                  <div key={index}>
                    <ListItem
                      button
                      className="admin-user-orders-item"
                      onClick={() => selectOrder(order)}
                    >
                      <Grid container>
                        <Grid item xs={6}>
                          <OrderInfo title="Status" description={order.status}>
                            <StatusIcon status={order.status} fontSize={26} />
                          </OrderInfo>
                        </Grid>

                        <Grid item xs={6} className="d-flex">
                          <OrderInfo
                            title="Ordered"
                            description={getDateStringNoHours(order.createdAt)}
                          />
                        </Grid>
                      </Grid>
                    </ListItem>
                    {index + 1 < orders.length && <Divider />}
                  </div>
                ))
              ) : (
                <Typography align="center">No orders to show</Typography>
              )}
            </List>
            {selectedOrder && (
              <AdminOrderModal
                handleStatusChange={handleStatusChange}
                order={selectedOrder}
                open={selectedOrder !== null}
                onClose={selectOrder}
              />
            )}
          </div>
        </div>
      </div>
    </Dialog>
  );
}

export default AdminUserModal;
