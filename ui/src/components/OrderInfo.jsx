import React from "react";

function OrderInfo(props) {
  return (
    <span className="order-info-field-wrapper">
      <span className="order-info-field-title">{props.title}:</span>
      {props.children}
      <span className="order-description">{props.description}</span>
    </span>
  );
}

export default OrderInfo;
