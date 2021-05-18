import React from "react";
import AdminOrders from "./AdminOrders";
import AdminUsers from "./AdminUsers";

function Admin() {
  return (
    <div className="admin-wrapper">
      <div className="admin-card">
        <AdminUsers />
      </div>
      <div className="admin-card">
        <AdminOrders />
      </div>
    </div>
  );
}

export default Admin;
