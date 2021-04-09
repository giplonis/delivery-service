import React from "react";
import ImageBox from "./ImageBox";
import { ButtonBase } from "@material-ui/core";
import "../styles/ParcelSizeCard.css";

function ParcelSizeCard(props) {
  return (
    <ButtonBase
      className={props.name === props.selectedPackageSize ? "selected-card w-100" : "w-100 scale-down"}
      onClick={() => props.onClick(props.name)}>
      <div className="form-inner form-inner-document w-100">
        <div className="form-header">{props.name}</div>
        <ImageBox image={props.image} alt={props.name} />
        <ul className="parcel-ul">
          <li>Max weigth: {props.dimensions.weight}kg</li>
          <li>Max length: {props.dimensions.length}cm</li>
          <li>Max width: {props.dimensions.width}cm</li>
          <li>Max height: {props.dimensions.height}cm</li>
        </ul>
      </div>
    </ButtonBase>
  );
}

export default ParcelSizeCard;
