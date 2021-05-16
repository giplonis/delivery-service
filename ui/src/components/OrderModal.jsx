import { Dialog } from "@material-ui/core";
import "../styles/OrderModal.css";
import OrderModalSummary from "./OrderModalSummary";

export default function OrderModal(props) {
  return (
    <Dialog open={props.open} onClose={() => props.onClose(props.order)}>
      <div className="modal-wrapper">
        <OrderModalSummary order={props.order} />
      </div>
    </Dialog>
  );
}
