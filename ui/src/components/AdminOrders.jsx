import React, { useState } from "react";
import { Divider, Grid, List, ListItem, Typography } from "@material-ui/core";
import StatusIcon from "./StatusIcon";

import AdminOrderModal from "./AdminOrderModal";

import OrderInfo from "./OrderInfo";
import { getDateString } from "../services/dateFormat";
import "../styles/Admin.css";

function AdminOrders(props) {
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
          {props.orders.length > 0 ? (
            props.orders.map((order, index) => (
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
                        description={getDateString(order.createdAt)}
                      />
                    </Grid>
                  </Grid>
                </ListItem>
                {index + 1 < props.orders.length && <Divider />}
              </div>
            ))
          ) : (
            <Typography align="center">No orders to show.</Typography>
          )}
        </List>
        {selectedOrder && (
          <AdminOrderModal
            handleStatusChange={props.handleStatusChange}
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
