import React from "react";
import { Button, Grid, ButtonBase } from "@material-ui/core";
import PaymentTypeCard from "./PaymentTypeCard";
import CreditCard from "../images/creditCard.png";
import Paypal from "../images/paypal.png";
import Cash from "../images/cash.png";
import "../styles/Summary.css";
import Box from "../images/box.png";
import Letter from "../images/letter.png";
import ImageBox from "./ImageBox";

function Summary(props) {
  console.log(props);
  return (
    <div className="form-wrapper">
      <Grid container spacing={9}>
        <Grid item xs={6} className="payment-cards-wrapper">
          <PaymentTypeCard image={CreditCard} name="Credit Card" onClick={props.onChange} selectedPaymentType={props.selectedPaymentType} />
          <PaymentTypeCard image={Paypal} name="Paypal" onClick={props.onChange} selectedPaymentType={props.selectedPaymentType} />
          <PaymentTypeCard image={Cash} name="Cash" className="cash-image" onClick={props.onChange} selectedPaymentType={props.selectedPaymentType} />
        </Grid>
        <Grid item xs={6}>
          <ButtonBase className="w-100">
            <div className="form-inner w-100">
              <div className="form-header summary-header">Summary</div>
              <div className="form-wrapper summary-subform-wrapper">
                <div className="form-inner form-inner-summary">
                  <div className="form-header summary-subheader">Sender Info</div>
                  <Grid container>
                    <Grid item xs={6}>
                      <ul className="summary-ul">
                        <li>First Name: </li>
                        <li>Last Name: </li>
                        <li>Phone Number: </li>
                      </ul>
                    </Grid>
                    <Grid item xs={6}>
                      <ul className="summary-ul">
                        <li>City: </li>
                        <li>Address: </li>
                        <li>Pick Up Date: </li>
                      </ul>
                    </Grid>
                  </Grid>
                </div>
              </div>
              <div className="form-wrapper summary-subform-wrapper">
                <div className="form-inner form-inner-summary">
                  <div className="form-header summary-subheader">Recipient Info</div>
                  <Grid container>
                    <Grid item xs={6}>
                      <ul className="summary-ul">
                        <li>First Name: </li>
                        <li>Last Name: </li>
                        <li>Phone Number: </li>
                      </ul>
                    </Grid>
                    <Grid item xs={6}>
                      <ul className="summary-ul">
                        <li>City: </li>
                        <li>Address: </li>
                      </ul>
                    </Grid>
                  </Grid>
                </div>
              </div>
              <div className="form-wrapper summary-subform-wrapper package-type-summary-wrapper">
                <div className="form-inner form-inner-summary">
                  <div className="form-header summary-subheader">{props.selectedPackageType}</div>
                  <Grid container>
                    <Grid item xs={6}>
                      <ImageBox alt={props.selectedPackageType} image={props.selectedPackageType === "Document" ? Letter : Box} />
                    </Grid>
                    <Grid item xs={6}>
                      <ul className="summary-ul">
                        <li>Max weight: </li>
                        <li>Max length: </li>
                        <li>Max width: </li>
                        {props.selectedPackageType === "Box" ? <li>Max heigth: </li> : ""}
                      </ul>
                    </Grid>
                  </Grid>
                </div>
              </div>
            </div>
          </ButtonBase>
        </Grid>
      </Grid>
      <div className="d-flex">
        <Button color="primary" variant="contained" className="form-button form-button-left" onClick={props.PreviousPage}>
          Back
        </Button>
        <Button color="primary" variant="contained" className="form-button">
          Confirm Order
        </Button>
      </div>
    </div>
  );
}

export default Summary;
