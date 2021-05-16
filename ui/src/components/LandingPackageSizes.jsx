import React, { useState, useEffect } from "react";
import { PACKAGE_OPTIONS } from "../api/config";
import axiosInstance from "../api/axiosInstance";
import useMessage from "../hooks/messages";
import BoxImage from "../images/box.png";
import LetterImage from "../images/letter.png";
import ParcelSizeCard from "./ParcelSizeCard";
import DocumentSizeCard from "./DocumentSizeCard";
import { Grid } from "@material-ui/core";

function LandingPackageSizes() {
  const { displayError } = useMessage();
  const [parcelSizes, setParcelSizes] = useState(undefined);
  const [letterSizes, setLetterSizes] = useState(undefined);

  useEffect(() => {
    (async function () {
      try {
        const response = await axiosInstance.get(PACKAGE_OPTIONS);
        setParcelSizes(
          response.data.filter((o) => o.packageType.title === "Package")
        );
        setLetterSizes(
          response.data.filter((o) => o.packageType.title === "Document")
        );
      } catch (e) {
        displayError("Failed to load package sizes");
      }
    })();
  }, [displayError]);

  return (
    <>
      <div style={{ marginTop: 120 }}>
        <h2
          style={{
            display: "flex",
            justifyContent: "center",
            marginBottom: 60,
          }}
        >
          Variety of Parcel Sizes
        </h2>
        <div className="form-wrapper">
          <Grid container justify="space-between" spacing={5}>
            {!!parcelSizes &&
              parcelSizes.map((size, index) => (
                <Grid item xs={3} key={index}>
                  <ParcelSizeCard
                    image={BoxImage}
                    name={size.packageSize.title}
                    price={size.price}
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
        </div>
      </div>
      <div style={{ marginTop: 120 }}>
        <h2
          style={{
            display: "flex",
            justifyContent: "center",
            marginBottom: 60,
          }}
        >
          Different Letter Sizes
        </h2>
        <div className="form-wrapper">
          <Grid container justify="space-between" spacing={9}>
            {!!letterSizes &&
              letterSizes.map((size, index) => (
                <Grid item xs={6} key={index}>
                  <DocumentSizeCard
                    image={LetterImage}
                    name={size.packageSize.title}
                    price={size.price}
                    dimensions={{
                      weight: size.packageSize.maxWeight,
                      length: size.packageSize.length,
                      width: size.packageSize.width,
                    }}
                  />
                </Grid>
              ))}
          </Grid>
        </div>
      </div>
    </>
  );
}

export default LandingPackageSizes;
