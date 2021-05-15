import React from "react";
import ParcelSizeSummaryCard from "./SummaryCards/ParcelSizeSummaryCard";
import RecipientSummaryCard from "./SummaryCards/RecipientSummaryCard";
import SenderSummaryCard from "./SummaryCards/SenderSummaryCard";
import StatusIcon from "./StatusIcon";

function OrderModalSummary(props) {
  return (
    <>
      <div className="order-status-wrapper order-info-field-wrapper">
        <span className="order-status-title ">Order Status:</span>
        <StatusIcon status={props.order.status} fontSize={30} />
        <span className="description-text">{props.order.status}</span>
      </div>
      <SenderSummaryCard
        sender={props.order.senderInfo}
        pickUpDate={props.order.pickupDateTime}
      />
      <RecipientSummaryCard recipient={props.order.recipientInfo} />
      <ParcelSizeSummaryCard
        selectedPackageSize={props.order.packageOption.packageSize}
        selectedPackageType={props.order.packageOption.packageType.title}
        attributes={props.order.attributes}
        price={props.order.totalPrice}
      />
    </>
  );
}

export default OrderModalSummary;
