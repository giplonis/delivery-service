import React, { useEffect, useState } from "react";
import { Divider, Grid, List, ListItem, Typography } from "@material-ui/core";
import StatusIcon from "./StatusIcon";
import useMessage from "../hooks/messages";
import AdminOrderModal from "./AdminOrderModal";
import { ADMIN_ORDERS } from "../api/config";
import axiosInstance from "../api/axiosInstance";
import "../styles/Admin.css";

function AdminOrders() {
  const [orders, setOrders] = useState([]);
  const { displayError } = useMessage();

  const handleStatusChange = (id, status) => {
    const orderArray = [...orders];
    orderArray.find((o) => o.id === id).status = status;
    setOrders(orderArray);
  };

  useEffect(() => {
    (async function fetchData() {
      try {
        const response = await axiosInstance.get(ADMIN_ORDERS);
        for (let index in response.data) {
          response.data[index].pickupDateTime = new Date(
            response.data[index].pickupDateTime
          );
        }
        setOrders(response.data);
      } catch (e) {
        displayError("Failed to load orders.");
      }
    })();
  }, [displayError]);

  function OrderInfo(props) {
    return (
      <span className="order-info-field-wrapper">
        <span className="order-info-field-title">{props.title}:</span>
        {props.children}
        <span className="order-description">{props.description}</span>
      </span>
    );
  }

  const [selectedOrder, setSelectedOrder] = useState(null);
  const selectOrder = (order) => {
    setSelectedOrder((prevState) => {
      if (prevState === null) return order;
      return null;
    });
  };

  return (
    <div className="form-wrapper">
      <div className="form-inner">
        <div className="form-header">Orders</div>
        <List className="admin-list">
          {orders.length > 0 ? (
            orders.map((order, index) => (
              <div key={index}>
                <ListItem button onClick={() => selectOrder(order)}>
                  <Grid container>
                    <Grid item xs={3}>
                      <OrderInfo title="Status" description={order.status}>
                        <StatusIcon status={order.status} />
                      </OrderInfo>
                    </Grid>
                    <Grid item xs={3}>
                      <OrderInfo
                        title="Sender"
                        description={`${order.senderInfo.firstName} ${order.senderInfo.lastName}`}
                      />
                    </Grid>
                    <Grid item xs={3}>
                      <OrderInfo
                        title="Recipient"
                        description={`${order.recipientInfo.firstName} ${order.recipientInfo.lastName}`}
                      />
                    </Grid>
                    <Grid item xs={3}>
                      <OrderInfo
                        title="Ordered"
                        description={new Date(
                          order.createdAt
                        ).toLocaleDateString("lt", {
                          year: "numeric",
                          month: "2-digit",
                          day: "2-digit",
                          hour: "2-digit",
                          minute: "2-digit",
                          hour12: false,
                        })}
                      />
                    </Grid>
                  </Grid>
                </ListItem>
                {index + 1 < orders.length && <Divider />}
              </div>
            ))
          ) : (
            <Typography align="center">No orders to show.</Typography>
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
  );
}

export default AdminOrders;
