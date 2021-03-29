import React from "react";
import Field from "./Field";
import { Grid } from "@material-ui/core";
import "../styles/SendingInfo.css";

function SendingInfo() {
  const labels = ["First Name", "Surname", "Phone Number", "City", "Street", "Nr"];
  const fields = labels.map((label, key) => <Field label={label} key={key} />);

  return (
    <div>
      <Grid container spacing={9}>
        <Grid item xs={6} className="form-column">
          <div>
            <h2 className="form-header">Sender Info</h2>
            {fields}
          </div>
        </Grid>
        <Grid item xs={6} className="form-column">
          <h2 className="form-header">Recipient Info</h2>
          {fields}
        </Grid>
      </Grid>
    </div>
  );
}

export default SendingInfo;
