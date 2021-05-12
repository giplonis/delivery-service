import React from "react";
import LandingPoint from "./LandingPoint";
import LocalShippingOutlinedIcon from "@material-ui/icons/LocalShippingOutlined";
import CreditCardOutlinedIcon from "@material-ui/icons/CreditCardOutlined";
import EuroOutlinedIcon from "@material-ui/icons/EuroOutlined";
import { Grid } from "@material-ui/core";

function LandingPoints() {
  const points = [
    {
      title: "Fast Delivery",
      text:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse quam orci, pulvinar ac tincidunt et, posuere at dolor. Quisque viverra tristique pharetra. Donec eget mattis dui, vel vestibulum nisi.",
      icon: LocalShippingOutlinedIcon,
    },
    {
      title: "Competitive Prices",
      text:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse quam orci, pulvinar ac tincidunt et, posuere at dolor. Quisque viverra tristique pharetra. Donec eget mattis dui, vel vestibulum nisi.",
      icon: EuroOutlinedIcon,
    },
    {
      title: "Easy Payment",
      text:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse quam orci, pulvinar ac tincidunt et, posuere at dolor. Quisque viverra tristique pharetra. Donec eget mattis dui, vel vestibulum nisi.",
      icon: CreditCardOutlinedIcon,
    },
  ];

  return (
    <div>
      <Grid container spacing={10} style={{ marginTop: 80, marginBottom: 80 }}>
        {points.map((point, index) => (
          <Grid item xs={4}>
            <LandingPoint
              key={index}
              title={point.title}
              description={point.text}
              icon={point.icon}
            />
          </Grid>
        ))}
      </Grid>
    </div>
  );
}

export default LandingPoints;
