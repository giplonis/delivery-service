import React from "react";
import { Grid, Button } from "@material-ui/core";
import BoxImage from "../images/box.png";
import ParcelSizeCard from "./ParcelSizeCard";
import "../styles/SendingInfo.css";

function ParcelSize(props) {
  return (
    <div className="form-wrapper">
      <Grid container justify="space-between" spacing={9}>
        {props.boxSizes.map((size, index) => (
          <Grid item xs={4} key={index}>
            <ParcelSizeCard
              image={BoxImage}
              name={size.name}
              selectedPackageSize={props.selectedBoxSize}
              onClick={props.onChange}
              dimensions={{
                width: size.width,
                height: size.height,
                length: size.length,
                weigth: size.weigth,
              }}
            />
          </Grid>
        ))}
      </Grid>
      <div className="d-flex">
        <Button color="primary" variant="contained" className="form-button form-button-left" onClick={props.PreviousPage}>
          Back
        </Button>
        <Button color="primary" variant="contained" className="form-button" onClick={props.NextPage}>
          Next
        </Button>
      </div>
    </div>
  );
}

export default ParcelSize;
