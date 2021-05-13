import React from "react";
import { Container } from "@material-ui/core";
import Header from "./Header";
import Footer from "./Footer";
import AdminOrders from "./AdminOrders";

function Admin() {
  return (
    <Container>
      <div className="content-wrapper">
        <Header />
        <AdminOrders />
      </div>
      <Footer />
    </Container>
  );
}

export default Admin;
