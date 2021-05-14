import React from "react";
import { Grid } from "@material-ui/core";
import PersonalData from "./PersonalData";
import PasswordChange from "./PasswordChange";
import OrderHistory from "./OrderHistory";

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
        <OrderHistory/>
      </Grid>
    </Grid>
  );
}

export default Profile;
