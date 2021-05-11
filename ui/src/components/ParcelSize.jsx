import React from "react";
import { Grid, Button } from "@material-ui/core";
import BoxImage from "../images/box.png";
import ParcelSizeCard from "./ParcelSizeCard";
import "../styles/SendingInfo.css";
import AttributesSelection from "./AttributesSelection";
import { getAdditionalPrice } from "../services/priceCalculation";

function ParcelSize(props) {
  return (
    <div className="form-wrapper">
      <Grid container justify="space-between" spacing={5}>
        {props.boxSizes.map((size, index) => (
          <Grid item xs={3} key={index}>
            <ParcelSizeCard
              image={BoxImage}
              name={size.packageSize.title}
              selectedPackageSize={props.selectedBoxSize}
              onClick={props.onChange}
              price={size.price + getAdditionalPrice(props.selectedAttributes)}
              dimensions={{
                width: size.packageSize.width,
                height: size.packageSize.height,
                length: size.packageSize.length,
                weight: size.packageSize.maxWeight / 1000,
              }}
            />
          </Grid>
        ))}
      </Grid>
      <AttributesSelection
        style={{ marginTop: 60, maxWidth: 180 }}
        toggleAttribute={props.toggleAttribute}
        selectedAttributes={props.selectedAttributes}
      />
      <div className="d-flex">
        <Button
          color="primary"
          variant="contained"
          className="form-button form-button-left"
          onClick={props.PreviousPage}
        >
          Back
        </Button>
        <Button
          color="primary"
          variant="contained"
          className="form-button"
          onClick={props.NextPage}
        >
          Next
        </Button>
      </div>
    </div>
  );
}

export default ParcelSize;
