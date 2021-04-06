import React from "react";
import {Grid} from "@material-ui/core";
import "../../styles/Summary.css";

export default function RecipientSummaryCard(props) {
    return (
        <div className="form-wrapper summary-subform-wrapper">
            <div className="form-inner form-inner-summary">
                <div className="form-header summary-subheader">Recipient Info</div>
                <Grid container>
                    <Grid item xs={12}>
                        <ul className="summary-ul">
                            <li>
                                {props.recipient.name} {props.recipient.surname}
                            </li>
                            <li>
                                {props.recipient.address}, {props.recipient.city}
                            </li>
                            <li>Phone Number: {props.recipient.number}</li>
                        </ul>
                    </Grid>
                </Grid>
            </div>
        </div>
    )
}