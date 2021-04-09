import React from "react";
import { ButtonBase, Grid } from "@material-ui/core";
import ImageBox from "./ImageBox";

function DocumentSizeCard(props) {
  return (
    <ButtonBase
      className={props.selectedDocumentSize === props.name ? "selected-card w-100" : "w-100 scale-down"}
      onClick={() => props.onClick(props.name)}>
      <div className="form-inner form-inner-document w-100">
        <div className="form-header">{props.name}</div>
        <Grid container justify="flex-start" alignItems="center" spacing={3}>
          <Grid item xs={6}>
            <ImageBox image={props.image} alt="small letter" />
          </Grid>
          <Grid item xs={6}>
            <ul className="letter-ul">
              <li>Max weight: {props.dimensions.weight}g</li>
              <li>Max length: {props.dimensions.length}cm</li>
              <li>Max width: {props.dimensions.width}cm</li>
            </ul>
          </Grid>
        </Grid>
      </div>
    </ButtonBase>
  );
}

export default DocumentSizeCard;
