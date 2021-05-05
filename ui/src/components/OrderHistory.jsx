import { Divider, Grid, List, ListItem, Typography } from "@material-ui/core";
import { useEffect, useState } from "react";
import OrderModal from "./OrderModal";
import "../styles/OrderHistory.css";
import StatusIcon from "./StatusIcon";
import { ORDERS } from "../config";
import useMessage from "../hooks/messages";
import axiosInstance from "../api/axiosInstance";

export default function OrderHistory() {
  const [orders, setOrders] = useState([]);
  const { displayError } = useMessage();
  useEffect(() => {
    (async function fetchData() {
      try {
        const response = await axiosInstance.get(ORDERS);
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

  const [selectedOrder, setSelectedOrder] = useState(null);
  const selectOrder = (order) => {
    setSelectedOrder((prevState) => {
      if (prevState === null) return order;
      return null;
    });
  };

  function OrderInfo(props) {
    return (
      <span className="order-info-field-wrapper">
        <span className="order-info-field-title">{props.title}:</span>
        {props.children}
        <span className="order-description">{props.description}</span>
      </span>
    );
  }

  return (
    <div className="form-wrapper">
      <div className="form-inner">
        <div className="form-header">Order History</div>
        <List>
          {orders.length > 0 ? (
            orders.map((order, index) => (
              <div key={index}>
                <ListItem button onClick={() => selectOrder(order)}>
                  <Grid container>
                    <Grid item xs={4}>
                      <OrderInfo title="Status" description={order.status}>
                        <StatusIcon status={order.status} />
                      </OrderInfo>
                    </Grid>
                    <Grid item xs={4}>
                      <OrderInfo
                        title="Recipient"
                        description={`${order.recipientInfo.firstName} ${order.recipientInfo.lastName}`}
                      />
                    </Grid>
                    <Grid item xs={4}>
                      <OrderInfo
                        title="Package"
                        description={order.packageOption.packageType.title}
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
          <OrderModal
            order={selectedOrder}
            open={selectedOrder !== null}
            onClose={selectOrder}
          />
        )}
      </div>
    </div>
  );
}
