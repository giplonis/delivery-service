import React from "react";
import {Grid} from "@material-ui/core";
import "../styles/Summary.css";

export default function ParcelSizeSummaryCard(props) {
    return (
        <>
            <div className="form-header summary-subheader">{props.selectedPackage.name}</div>
            <Grid container>
                <Grid item xs={6}>
                    <ImageBox alt={props.selectedPackageType} image={props.selectedPackageType === "Document" ? Letter : Box} />
                </Grid>
                <Grid item xs={6}>
                    <ul className="summary-ul">
                        <li>
                            Max weight: {props.selectedPackage.weight}
                            {props.selectedPackageType === "Document" ? "g" : "kg"}
                        </li>
                        <li>Max length: {props.selectedPackage.length}cm</li>
                        <li>Max width: {props.selectedPackage.width}cm</li>
                        {props.selectedPackageType === "Box" ? <li>Max height: {props.selectedPackage.height}cm</li> : ""}
                    </ul>
                </Grid>
            </Grid>
        </>
    )
}