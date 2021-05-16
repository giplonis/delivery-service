import React from "react";
import { Button } from "@material-ui/core";
import LandingCarousel from "./LandingCarousel";
import LandingPoints from "./LandingPoints";
import LandingAboutUs from "./LandingAboutUs";
import LandingPackageSizes from "./LandingPackageSizes";
import { Link } from "react-router-dom";
import { getOrderPath } from "../services/navigation/paths";
import "../styles/LandingPage.css";

function LandingPage() {
  return (
    <>
      <LandingCarousel />
      <LandingPoints />
      <LandingAboutUs />
      <LandingPackageSizes />
      <Link to={getOrderPath()} className="remove-link-decoration">
        <Button color="primary" variant="contained" className="landing-button">
          Order Now!
        </Button>
      </Link>
    </>
  );
}

export default LandingPage;
