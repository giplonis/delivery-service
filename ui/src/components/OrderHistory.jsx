import { Divider, Grid, List, ListItem, Typography } from "@material-ui/core";
import { useEffect, useState } from "react";
import OrderModal from "./OrderModal";
import "../styles/OrderHistory.css";
import StatusIcon from "./StatusIcon";
import useMessage from "../hooks/messages";
import axiosInstance from "../api/axiosInstance";
import OrderInfo from "./OrderInfo";

export default function OrderHistory(props) {
  const [orders, setOrders] = useState([]);
  const { displayError } = useMessage();
  useEffect(() => {
    (async function fetchData() {
      try {
        const response = await axiosInstance.get(props.fetchEndpoint);
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
  }, [displayError, props.fetchEndpoint]);

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
        <div className="form-header">{props.name}</div>
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
                    {props.showRecipient && (
                      <Grid item xs={4}>
                        <OrderInfo
                          title="Recipient"
                          description={`${order.recipientInfo.firstName} ${order.recipientInfo.lastName}`}
                        />
                      </Grid>
                    )}
                    {props.showSender && (
                      <Grid item xs={4}>
                        <OrderInfo
                          title="Sender"
                          description={`${order.senderInfo.firstName} ${order.senderInfo.lastName}`}
                        />
                      </Grid>
                    )}
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
