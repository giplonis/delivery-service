import React from "react";
import { Grid } from "@material-ui/core";

export default function RecipientSummaryCard(props) {
  return (
    <div className="form-wrapper summary-subform-wrapper">
      <div className="form-inner form-inner-summary">
        <div className="form-header summary-subheader">Recipient Info</div>
        <Grid container>
          <Grid item xs={12}>
            <ul className="summary-ul">
              <li>
                {props.recipient.firstName} {props.recipient.lastName}
              </li>
              <li>
                {props.recipient.address.street}, {props.recipient.address.city}
              </li>
              <li>Phone Number: {props.recipient.phoneNumber}</li>
            </ul>
          </Grid>
        </Grid>
      </div>
    </div>
  );
}
