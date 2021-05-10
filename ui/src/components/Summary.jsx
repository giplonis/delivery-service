import React, { useState } from "react";
import { Button, Grid, ButtonBase } from "@material-ui/core";
import PaymentTypeCard from "./PaymentTypeCard";
import CreditCard from "../images/creditCard.png";
import Paypal from "../images/paypal.png";
import Cash from "../images/cash.png";
import "../styles/Summary.css";
import SenderSummaryCard from "./SummaryCards/SenderSummaryCard";
import RecipientSummaryCard from "./SummaryCards/RecipientSummaryCard";
import ParcelSizeSummaryCard from "./SummaryCards/ParcelSizeSummaryCard";
import CreditCardModal from "./CreditCardModal";
import useMessage from "../hooks/messages";
import { ORDERS } from "../api/config";
import LoadingButton from "./LoadingButton";
import axiosInstance from "../api/axiosInstance";

function Summary(props) {
  const date = props.formData.pickUpDate;
  const [creditCardModalOpen, setCreditCardModalOpen] = useState(false);
  const { displayError } = useMessage();
  const [isPostingOrder, setIsPostingOrder] = useState(false);

  const orderObject = {
    ...props.formData,
    packageOptionId: props.selectedPackage.id,
    attributes: props.selectedAttributes.map((attribute) => attribute.id),
  };

  const placeOrder = () => {
    (async function () {
      let success = true;
      setIsPostingOrder(true);
      try {
        await axiosInstance.post(ORDERS, orderObject);
      } catch (e) {
        success = false;
        displayError("Failed to place order");
      } finally {
        setIsPostingOrder(false);
      }
      if (success) {
        props.onOrderSuccess();
      }
    })();
  };

  const handleConfirmOrder = () => {
    if (props.selectedPaymentType === undefined) {
      displayError("Payment method is not selected");
      return;
    }

    if (props.selectedPaymentType === "Credit Card") {
      toggleCreditCardModal();
    } else {
      placeOrder();
    }
  };
  const toggleCreditCardModal = () => {
    setCreditCardModalOpen((prevState) => !prevState);
  };

  var additionalPrice = 0;
  additionalPrice = props.selectedAttributes.map((attribute) => {
    return additionalPrice + attribute.additionalPrice;
  });
  if (additionalPrice.length !== 0) additionalPrice = parseInt(additionalPrice);
  else additionalPrice = 0;

  return (
    <div className="form-wrapper">
      <Grid container spacing={9}>
        <Grid item xs={6} className="payment-cards-wrapper">
          <PaymentTypeCard
            image={CreditCard}
            name="Credit Card"
            onClick={props.onChange}
            selectedPaymentType={props.selectedPaymentType}
          />
          <PaymentTypeCard
            image={Paypal}
            name="Paypal"
            onClick={props.onChange}
            selectedPaymentType={props.selectedPaymentType}
          />
          <PaymentTypeCard
            image={Cash}
            name="Cash"
            className="cash-image"
            onClick={props.onChange}
            selectedPaymentType={props.selectedPaymentType}
          />
        </Grid>
        <Grid item xs={6}>
          <ButtonBase className="w-100">
            <div className="form-inner w-100">
              <div className="form-header summary-header">Summary</div>
              <SenderSummaryCard
                sender={props.formData.sender}
                pickUpDate={date}
              />
              <RecipientSummaryCard recipient={props.formData.recipient} />
              <ParcelSizeSummaryCard
                selectedPackageSize={props.selectedPackage.packageSize}
                selectedPackageType={props.selectedPackageType}
                attributes={props.selectedAttributes}
                price={props.selectedPackage.price + additionalPrice}
              />
            </div>
          </ButtonBase>
        </Grid>
      </Grid>
      <div className="d-flex">
        <Button
          color="primary"
          variant="contained"
          className="form-button form-button-left"
          onClick={props.PreviousPage}
        >
          Back
        </Button>
        <LoadingButton
          color="primary"
          variant="contained"
          className="form-button"
          onClick={handleConfirmOrder}
          loading={isPostingOrder}
          disabled={isPostingOrder}
        >
          Confirm Order
        </LoadingButton>
        <CreditCardModal
          open={creditCardModalOpen}
          toggleModal={toggleCreditCardModal}
          placeOrder={placeOrder}
          price={props.selectedPackage.price + additionalPrice}
        />
      </div>
    </div>
  );
}

export default Summary;
