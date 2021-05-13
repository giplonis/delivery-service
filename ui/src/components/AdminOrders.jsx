import React, { useEffect, useState } from "react";
import { Divider, Grid, List, ListItem, Typography } from "@material-ui/core";
import StatusIcon from "./StatusIcon";
import useMessage from "../hooks/messages";
import OrderModal from "./OrderModal";

function AdminOrders() {
  var orders = [
    {
      id: 1,
      createdAt: 1620919721388,
      status: "DELIVERED",
      pickupDateTime: 1621092521388,
      totalPrice: 42069,
      senderInfo: {
        id: 1,
        firstName: "Jonas",
        lastName: "Jonauskas",
        email: "jonas@gmail.com",
        phoneNumber: "862594785",
        address: {
          id: 1,
          city: "Vilnius",
          street: "Antakalnio g., 32",
        },
      },
      recipientInfo: {
        id: 1,
        firstName: "Jonas",
        lastName: "Jonauskas",
        email: "jonas@gmail.com",
        phoneNumber: "862594785",
        address: {
          id: 1,
          city: "Vilnius",
          street: "Antakalnio g., 32",
        },
      },
      sender: null,
      packageOption: {
        id: 1,
        price: 80,
        packageSize: {
          id: 4,
          title: "Small Letter",
          maxWeight: 100,
          length: 165,
          height: 240,
          width: 5,
        },
        packageType: {
          id: 1,
          title: "Document",
          description: "Send a document",
        },
      },
      attributes: [],
    },
    {
      id: 3,
      createdAt: 1620919721388,
      status: "DELIVERED",
      pickupDateTime: 1621092521388,
      totalPrice: 42069,
      senderInfo: {
        id: 1,
        firstName: "Jonas",
        lastName: "Jonauskas",
        email: "jonas@gmail.com",
        phoneNumber: "862594785",
        address: {
          id: 1,
          city: "Vilnius",
          street: "Antakalnio g., 32",
        },
      },
      recipientInfo: {
        id: 1,
        firstName: "Jonas",
        lastName: "Jonauskas",
        email: "jonas@gmail.com",
        phoneNumber: "862594785",
        address: {
          id: 1,
          city: "Vilnius",
          street: "Antakalnio g., 32",
        },
      },
      sender: null,
      packageOption: {
        id: 1,
        price: 80,
        packageSize: {
          id: 4,
          title: "Small Letter",
          maxWeight: 100,
          length: 165,
          height: 240,
          width: 5,
        },
        packageType: {
          id: 1,
          title: "Document",
          description: "Send a document",
        },
      },
      attributes: [],
    },
    {
      id: 1,
      createdAt: 1620919721388,
      status: "DELIVERED",
      pickupDateTime: 1621092521388,
      totalPrice: 42069,
      senderInfo: {
        id: 1,
        firstName: "Jonas",
        lastName: "Jonauskas",
        email: "jonas@gmail.com",
        phoneNumber: "862594785",
        address: {
          id: 1,
          city: "Vilnius",
          street: "Antakalnio g., 32",
        },
      },
      recipientInfo: {
        id: 1,
        firstName: "Jonas",
        lastName: "Jonauskas",
        email: "jonas@gmail.com",
        phoneNumber: "862594785",
        address: {
          id: 1,
          city: "Vilnius",
          street: "Antakalnio g., 32",
        },
      },
      sender: null,
      packageOption: {
        id: 1,
        price: 80,
        packageSize: {
          id: 4,
          title: "Small Letter",
          maxWeight: 100,
          length: 165,
          height: 240,
          width: 5,
        },
        packageType: {
          id: 1,
          title: "Document",
          description: "Send a document",
        },
      },
      attributes: [],
    },
  ];

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

export default AdminOrders;
