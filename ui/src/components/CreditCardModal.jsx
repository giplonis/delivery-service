import React, { useState, useCallback } from "react";
import { CardElement, Elements, useStripe } from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";
import { Dialog, Grid, TextField } from "@material-ui/core";
import "../styles/CreditCardModal.css";
import { Formik, Form } from "formik";
import Field from "./Field";
import * as yup from "yup";
import LoadingButton from "./LoadingButton";

const stripePromise = loadStripe(
  "pk_test_51IfQyPIHDg9f7KlHkMcHHqEsqWeYWHIBr6gKv2YSkcXQLGepH1GVe4M3DxnrmU201jQZXolYLNgjZHGw4LRSo0Bw00sxArXSdt"
);
const CARD_OPTIONS = {
  iconStyle: "solid",
  style: {
    base: {
      iconColor: "#bbb",
      color: "white",
      fontFamily: "Roboto, sans-serif",
      fontSize: "16px",
      "::placeholder": {
        color: "#bbb",
      },
      ":focus": {
        iconColor: "#cee002",
      },
    },
    invalid: {
      color: "#f44336",
      iconColor: "#f44336",
    },
  },
};

const CreditCardSchema = yup.object().shape({
  firstname: yup.string().max(50, "Too Long!").required("Required"),
  lastname: yup.string().max(50, "Too Long!").required("Required"),
});

const CheckoutForm = (props) => {
  const stripe = useStripe();
  const [error, setError] = useState(null);
  const [cardNumberEmpty, setCardNumberEmpty] = useState(true);
  const [isPaying, setIsPaying] = useState(false);
  const cardElement = useCallback(
    (props) => (
      <CardElement
        {...props}
        options={CARD_OPTIONS}
        onChange={(e) => {
          setCardNumberEmpty(e.empty);
          setError(e.error);
        }}
      />
    ),
    []
  );

  return (
    <Formik
      initialValues={{
        firstname: "",
        lastname: "",
      }}
      validationSchema={CreditCardSchema}
      onSubmit={(values) => {
        if (error) {
          return;
        }
        setIsPaying(true);
        new Promise((res) => setTimeout(res, 1500)).then(() => {
          setIsPaying(false);
          props.toggleModal();
          props.placeOrder();
        });
      }}
    >
      <Form className="credit-card-form-wrapper">
        <Grid container spacing={5}>
          <Grid item xs={6}>
            <Field label="First name" name="firstname" />
          </Grid>
          <Grid item xs={6}>
            <Field label="Last name" name="lastname" />
          </Grid>

          <Grid item xs={12}>
            <TextField
              label="Card Number"
              name="cardNumber"
              error={!!error}
              helperText={error ? error.message : null}
              InputProps={{
                inputComponent: cardElement,
              }}
              InputLabelProps={{ shrink: true }}
              onBlur={() => {
                if (cardNumberEmpty) {
                  setError(null);
                }
              }}
              fullWidth
            />
          </Grid>
        </Grid>

        <LoadingButton
          color="primary"
          variant="contained"
          className="form-button form-button-credit-card"
          disabled={!stripe || isPaying}
          type="submit"
          loading={isPaying}
          onClick={() => {
            if (cardNumberEmpty) {
              setError({ message: "Required" });
            }
          }}
        >
          Pay {props.price / 100 + "€"}
        </LoadingButton>
      </Form>
    </Formik>
  );
};

export default function CreditCardModal(props) {
  return (
    <Dialog open={props.open} onClose={props.toggleModal}>
      <div className="credit-card-modal-wrapper">
        <Elements stripe={stripePromise}>
          <CheckoutForm
            price={props.price}
            toggleModal={props.toggleModal}
            placeOrder={props.placeOrder}
          />
        </Elements>
      </div>
    </Dialog>
  );
}
