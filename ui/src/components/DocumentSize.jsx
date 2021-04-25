import React from "react";
import Letter from "../images/letter.png";
import { Grid, Button } from "@material-ui/core";
import "../styles/DocumentSize.css";
import DocumentSizeCard from "./DocumentSizeCard";

function DocumentSize(props) {
  return (
    <div className="form-wrapper">
      <Grid container justify="center" spacing={9}>
        {props.letterSizes.map((size, key) => (
          <Grid item xs={12} md={6} key={key}>
            <DocumentSizeCard
              onClick={props.onChange}
              image={Letter}
              dimensions={{
                weight: size.packageSize.maxWeight,
                length: size.packageSize.length,
                width: size.packageSize.width,
              }}
              name={size.packageSize.title}
              selectedDocumentSize={props.selectedDocumentSize}
            />
          </Grid>
        ))}
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

export default DocumentSize;
