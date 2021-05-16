import React from "react";
import { Grid } from "@material-ui/core";
import { getDateString } from "../../services/dateFormat";

export default function SenderSummaryCard(props) {
  return (
    <div className="form-wrapper summary-subform-wrapper">
      <div className="form-inner form-inner-summary">
        <div className="form-header summary-subheader">Sender Info</div>
        <Grid container>
          <Grid item xs={12}>
            <ul className="summary-ul">
              <li>
                {props.sender.firstName} {props.sender.lastName}
              </li>
              <li>
                {props.sender.address.street}, {props.sender.address.city}
              </li>
              <li>Phone Number: {props.sender.phoneNumber}</li>
              <li>Pick Up Date: {getDateString(props.pickUpDate)}</li>
            </ul>
          </Grid>
        </Grid>
      </div>
    </div>
  );
}
