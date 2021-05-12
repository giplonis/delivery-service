import React from "react";
import Header from "./Header";
import Footer from "./Footer";
import { Container } from "@material-ui/core";
import LandingCarousel from "./LandingCarousel";

function LandingPage() {
  return (
    <Container>
      <div className="content-wrapper">
        <Header />
        <LandingCarousel />
      </div>
      <Footer />
    </Container>
  );
}

export default LandingPage;
