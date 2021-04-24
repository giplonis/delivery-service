import { Typography } from "@material-ui/core";
import DoneOutlineIcon from "@material-ui/icons/DoneOutline";
import "../styles/PaymentSuccess.css";
import { Button } from "@material-ui/core";
import { Link } from "react-router-dom";

export default function PaymentSuccess() {
  return (
    <div className="success-form-wrapper">
      <DoneOutlineIcon
        className="success-icon-style"
        color="primary"
        style={{ fontSize: 200, margin: 30 }}
      />
      <Typography variant="h4">Order Placed Successfully</Typography>
      <Button
        className="form-button"
        color="primary"
        variant="contained"
        style={{ marginTop: 130 }}
        component={(props) => <Link {...props} to={"/profile"} exact />}
      >
        Go To Orders
      </Button>
    </div>
  );
}
