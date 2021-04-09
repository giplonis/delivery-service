import { Divider, Grid, List, ListItem } from "@material-ui/core";
import { useState } from "react";
import OrderModal from "./OrderModal";
import "../styles/OrderHistory.css";
import StatusIcon from "./StatusIcon";

export default function OrderHistory() {
  const orders = [
    {
      sender: {
        name: "qwer",
        surname: "asdf",
        number: "+6546464646",
        city: "Vilnius",
        address: "asdfafds g. 10",
      },
      recipient: {
        name: "dsfgsrg",
        surname: "dfghdfgdh",
        number: "+98494984484",
        city: "Vilnius",
        address: "sdfdsfsf g. 10",
      },
      email: "sdfgsgfd@dfgdfg.dsf",
      pickUpDate: new Date(2021, 3, 28, 11, 0),
      status: "New",
      packageId: 1,
    },
    {
      sender: {
        name: "fghnfhgn",
        surname: "fnhgn",
        number: "+6546464646",
        city: "Vilnius",
        address: "asdfafds g. 10",
      },
      recipient: {
        name: "bghdn",
        surname: "dfbdfgb",
        number: "+98494984484",
        city: "Vilnius",
        address: "sdfdsfsf g. 10",
      },
      email: "sfaf@gmail.com",
      pickUpDate: new Date(2021, 3, 10, 18, 50),
      status: "New",
      packageId: 2,
    },
    {
      sender: {
        name: "nhtrh",
        surname: "fbggbd",
        number: "+6546464646",
        city: "Vilnius",
        address: "vfvgv g. 10",
      },
      recipient: {
        name: "dsfgsrg",
        surname: "bbvgb",
        number: "+98494984484",
        city: "Vilnius",
        address: "sdfdsfsf g. 10",
      },
      email: "asdfsd@gmail.com",
      pickUpDate: new Date(2021, 4, 7, 9, 5),
      status: "Delivered",
      packageId: 5,
    },
  ];
  const packageSizes = [
    {
      id: 1,
      type: "Box",
      name: "Small Box",
      width: 35,
      height: 16,
      length: 45,
      weight: 2,
    },
    {
      id: 2,
      type: "Box",
      name: "Medium Box",
      width: 46,
      height: 46,
      length: 64,
      weight: 20,
    },
    {
      id: 3,
      type: "Box",
      name: "Large Box",
      width: 150,
      height: 150,
      length: 150,
      weight: 30,
    },
    {
      id: 4,
      type: "Document",
      name: "Small Letter",
      weight: 100,
      length: 24,
      width: 16,
    },
    {
      id: 5,
      type: "Document",
      name: "Large Letter",
      weight: 750,
      length: 35,
      width: 25,
    },
  ];

  const [selectedOrder, setSelectedOrder] = useState(null);
  const selectOrder = (order) => {
    setSelectedOrder((prevState) => {
      if (prevState === null) return order;
      return null;
    });
  };

  const getPackageSize = (order) => {
    if (order === null) return null;
    return packageSizes.find((x) => x.id === order.packageId);
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
          {orders.map((order, index) => (
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
                      description={`${order.recipient.name} ${order.recipient.surname}`}
                    />
                  </Grid>
                  <Grid item xs={4}>
                    <OrderInfo
                      title="Package"
                      description={getPackageSize(order).name}
                    />
                  </Grid>
                </Grid>
              </ListItem>
              {index + 1 < orders.length && <Divider />}
            </div>
          ))}
        </List>
        {selectedOrder && (
          <OrderModal
            order={selectedOrder}
            packageSize={getPackageSize(selectedOrder)}
            open={selectedOrder !== null}
            onClose={selectOrder}
          />
        )}
      </div>
    </div>
  );
}
