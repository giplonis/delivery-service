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
  const date = props.formData.pickUpDate;
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
                    <Grid item xs={12}>
                      <ul className="summary-ul">
                        <li>
                          {props.formData.sender.name} {props.formData.sender.surname}
                        </li>
                        <li>
                          {props.formData.sender.address}, {props.formData.sender.city}
                        </li>
                        <li>Phone Number: {props.formData.sender.number}</li>
                        <li>
                          Pick Up Date:{" "}
                          {date.toLocaleString("en-US", {
                            year: "numeric",
                            month: "2-digit",
                            day: "2-digit",
                            hour: "2-digit",
                            minute: "2-digit",
                            hour12: false,
                          })}
                        </li>
                      </ul>
                    </Grid>
                  </Grid>
                </div>
              </div>
              <div className="form-wrapper summary-subform-wrapper">
                <div className="form-inner form-inner-summary">
                  <div className="form-header summary-subheader">Recipient Info</div>
                  <Grid container>
                    <Grid item xs={12}>
                      <ul className="summary-ul">
                        <li>
                          {props.formData.recipient.name} {props.formData.recipient.surname}
                        </li>
                        <li>
                          {props.formData.recipient.address}, {props.formData.recipient.city}
                        </li>
                        <li>Phone Number: {props.formData.recipient.number}</li>
                      </ul>
                    </Grid>
                  </Grid>
                </div>
              </div>
              <div className="form-wrapper summary-subform-wrapper package-type-summary-wrapper">
                <div className="form-inner form-inner-summary">
                  <div className="form-header summary-subheader">{props.selectedPackage.name}</div>
                  <Grid container>
                    <Grid item xs={6}>
                      <ImageBox alt={props.selectedPackageType} image={props.selectedPackageType === "Document" ? Letter : Box} />
                    </Grid>
                    <Grid item xs={6}>
                      <ul className="summary-ul">
                        <li>
                          Max weight: {props.selectedPackage.weight}
                          {props.selectedPackageType === "Document" ? "g" : "kg"}
                        </li>
                        <li>Max length: {props.selectedPackage.length}cm</li>
                        <li>Max width: {props.selectedPackage.width}cm</li>
                        {props.selectedPackageType === "Box" ? <li>Max height: {props.selectedPackage.height}cm</li> : ""}
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
