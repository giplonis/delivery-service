import React from "react";
import Header from "./Header";
import Footer from "./Footer";
import { Container, Button } from "@material-ui/core";
import LandingCarousel from "./LandingCarousel";
import LandingPoints from "./LandingPoints";
import LandingAboutUs from "./LandingAboutUs";
import LandingPackageSizes from "./LandingPackageSIzes";
import { Link } from "react-router-dom";
import "../styles/LandingPage.css";

function LandingPage() {
  return (
    <Container>
      <div className="content-wrapper">
        <Header />
        <LandingCarousel />
        <LandingPoints />
        <LandingAboutUs />
        <LandingPackageSizes />
        <Link to="/order" className="remove-link-decoration">
          <Button
            color="primary"
            variant="contained"
            className="landing-button"
          >
            Order Now!
          </Button>
        </Link>
      </div>
      <Footer />
    </Container>
  );
}

export default LandingPage;
