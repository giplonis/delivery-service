import React from "react";
import { ButtonBase } from "@material-ui/core";
import ImageBox from "./ImageBox";
import "../styles/PaymentTypeCard.css";

function PaymentTypeCard(props) {
  return (
    <ButtonBase
      className={props.name === props.selectedPaymentType ? "selected-card w-100 payment-card" : "w-100 scale-down payment-card"}
      onClick={() => props.onClick(props.name)}>
      <div className={"form-inner form-inner-document w-100 " + props.className}>
        <div className="form-header">{props.name}</div>
        <ImageBox image={props.image} alt={props.name} />
      </div>
    </ButtonBase>
  );
}

export default PaymentTypeCard;
