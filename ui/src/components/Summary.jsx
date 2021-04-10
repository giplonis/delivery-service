import React from "react";
import { Button, Grid, ButtonBase } from "@material-ui/core";
import PaymentTypeCard from "./PaymentTypeCard";
import CreditCard from "../images/creditCard.png";
import Paypal from "../images/paypal.png";
import Cash from "../images/cash.png";
import "../styles/Summary.css";
import SenderSummaryCard from "./SummaryCards/SenderSummaryCard";
import RecipientSummaryCard from "./SummaryCards/RecipientSummaryCard";
import ParcelSizeSummaryCard from "./SummaryCards/ParcelSizeSummaryCard";

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
              <SenderSummaryCard
                sender = {props.formData.sender}
                pickUpDate = {date}
              />
              <RecipientSummaryCard
                recipient = {props.formData.recipient}
              />
              <ParcelSizeSummaryCard
                selectedPackage={props.selectedPackage}
                selectedPackageType={props.selectedPackageType}
              />
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
