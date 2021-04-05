import React from "react";
import {Grid} from "@material-ui/core";
import "../styles/Summary.css";

export default function SenderSummaryCard(props) {
    return (
        <>
            <div className="form-header summary-subheader">Sender Info</div>
            <Grid container>
                <Grid item xs={12}>
                    <ul className="summary-ul">
                        <li>
                            {props.sender.name} {props.sender.surname}
                        </li>
                        <li>
                            {props.sender.address}, {props.sender.city}
                        </li>
                        <li>Phone Number: {props.sender.number}</li>
                        <li>Pick Up Date: {props.pickUpDate}</li>
                    </ul>
                </Grid>
            </Grid>
        </>
    )
}