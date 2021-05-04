import React from "react";
import { Grid } from "@material-ui/core";
import Box from "../../images/box.png";
import Letter from "../../images/letter.png";
import ImageBox from "../ImageBox";

export default function ParcelSizeSummaryCard(props) {
  return (
    <div className="form-wrapper summary-subform-wrapper package-type-summary-wrapper">
      <div className="form-inner form-inner-summary">
        <div className="form-header summary-subheader">
          {props.selectedPackageSize.title}
        </div>
        <Grid container>
          <Grid item xs={6}>
            <ImageBox
              alt={props.selectedPackageType}
              image={props.selectedPackageType === "Document" ? Letter : Box}
            />
          </Grid>
          <Grid item xs={6}>
            <ul className="summary-ul">
              <li>
                Max weight:{" "}
                {props.selectedPackageType === "Document"
                  ? props.selectedPackageSize.maxWeight + "g"
                  : props.selectedPackageSize.maxWeight / 1000 + "kg"}
              </li>
              <li>Max length: {props.selectedPackageSize.length}cm</li>
              <li>Max width: {props.selectedPackageSize.width}cm</li>
              {props.selectedPackageType === "Box" ? (
                <li>Max height: {props.selectedPackageSize.height}cm</li>
              ) : (
                ""
              )}
              {props.attributes.map((attr) => (
                <li>{attr.label}</li>
              ))}
            </ul>
          </Grid>
        </Grid>
      </div>
    </div>
  );
}
