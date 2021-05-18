import React from "react";
import { Grid } from "@material-ui/core";
import PersonalData from "./PersonalData";
import PasswordChange from "./PasswordChange";
import OrderHistory from "./OrderHistory";
import { ORDERS_RECEIVED, ORDERS_SENT } from "../api/config";

function Profile() {
  return (
    <Grid container spacing={9}>
      <Grid item xs={6}>
        <PersonalData />
      </Grid>
      <Grid item xs={6}>
        <PasswordChange />
      </Grid>
      <Grid item xs={12}>
        <OrderHistory
          name="Orders Sent"
          fetchEndpoint={ORDERS_SENT}
          showRecipient
        />
      </Grid>
      <Grid item xs={12}>
        <OrderHistory
          name="Orders Received"
          fetchEndpoint={ORDERS_RECEIVED}
          showSender
        />
      </Grid>
    </Grid>
  );
}

export default Profile;
