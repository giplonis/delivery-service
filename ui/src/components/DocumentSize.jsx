import React from "react";
import ImageBox from "./ImageBox";
import Letter from "../images/letter.png";
import { Grid, Button, ButtonBase } from "@material-ui/core";
import "../styles/DocumentSize.css";

function DocumentSize() {
  const smallLetter = ["Max weight: 100g", "Max length: 24cm", "Max width: 16.5cm"];
  const largeLetter = ["Max weight: 750g", "Max length: 35.3cm", "Max width: 25cm"];

  const small = smallLetter.map((item, key) => <li key={key}>{item}</li>);
  const large = largeLetter.map((item, key) => <li key={key}>{item}</li>);

  return (
    <div className="form-wrapper">
      <Grid container justify="center" spacing={9}>
        <Grid item xs={12} md={6}>
          <ButtonBase className="w-100">
            <div className="form-inner form-inner-document w-100">
              <div className="form-header">Small letter</div>
              <Grid container justify="flex-start" alignItems="center" spacing={3}>
                <Grid item xs={6}>
                  <ImageBox image={Letter} alt="small letter" />
                </Grid>
                <Grid item xs={6}>
                  <ul className="letter-ul">{small}</ul>
                </Grid>
              </Grid>
            </div>
          </ButtonBase>
        </Grid>
        <Grid item xs={12} md={6}>
          <ButtonBase className="w-100">
            <div className="form-inner form-inner-document w-100">
              <div className="form-header">Large letter</div>
              <Grid container justify="flex-start" alignItems="center" spacing={3}>
                <Grid item xs={6}>
                  <ImageBox image={Letter} alt="large letter" />
                </Grid>
                <Grid item xs={6}>
                  <ul className="letter-ul">{large}</ul>
                </Grid>
              </Grid>
            </div>
          </ButtonBase>
        </Grid>
      </Grid>
      <div className="d-flex">
        <Button color="primary" variant="contained" className="form-button form-button-left">
          Back
        </Button>
        <Button color="primary" variant="contained" className="form-button">
          Next
        </Button>
      </div>
    </div>
  );
}

export default DocumentSize;
