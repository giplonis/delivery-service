import ParcelSizeSummaryCard from "./SummaryCards/ParcelSizeSummaryCard";
import RecipientSummaryCard from "./SummaryCards/RecipientSummaryCard";
import SenderSummaryCard from "./SummaryCards/SenderSummaryCard";
import { Dialog } from "@material-ui/core";
import "../styles/OrderModal.css";
import StatusIcon from "./StatusIcon";

export default function OrderModal(props) {
  return (
    <Dialog open={props.open} onClose={() => props.onClose(props.order)}>
      <div className="modal-wrapper">
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
      </div>
    </Dialog>
  );
}
