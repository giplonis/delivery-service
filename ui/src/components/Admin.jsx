import React, { useEffect, useState } from "react";
import AdminOrders from "./AdminOrders";
import AdminUsers from "./AdminUsers";
import { ADMIN_ORDERS } from "../api/config";
import axiosInstance from "../api/axiosInstance";
import useMessage from "../hooks/messages";

function Admin() {
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

  return (
    <div className="admin-wrapper">
      <div className="admin-card">
        <AdminUsers handleStatusChange={handleStatusChange} />
      </div>
      <div className="admin-card">
        <AdminOrders handleStatusChange={handleStatusChange} orders={orders} />
      </div>
    </div>
  );
}

export default Admin;
