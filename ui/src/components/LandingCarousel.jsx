import React from "react";
import "react-responsive-carousel/lib/styles/carousel.min.css"; // requires a loader
import { Carousel } from "react-responsive-carousel";
import Courier1 from "../images/courier1.jpg";
import Courier2 from "../images/courier2.jpg";
import Courier3 from "../images/courier3.jpg";
import "../styles/LandingCarousel.css";
import { Button, Divider } from "@material-ui/core";
import { Link } from "react-router-dom";

function LandingCarousel() {
  return (
    <div>
      <Carousel
        showStatus={false}
        showThumbs={false}
        infiniteLoop={true}
        interval={4000}
        autoPlay={true}
        transitionTime={500}
      >
        <div className="carousel-slide">
          <div className="carousel-slide-content-wrapper">
            <div className="carousel-slide-text">
              Adding Speed to your Deliveries
              <Divider className="carousel-divider" />
            </div>
            <Link to="/order" className="remove-link-decoration">
              <Button
                color="primary"
                variant="contained"
                className="carousel-button"
              >
                Order Now
              </Button>
            </Link>
          </div>
          <img src={Courier1} alt="courier-1" />
        </div>
        <div className="carousel-slide">
          <div className="carousel-slide-content-wrapper">
            <div className="carousel-slide-text">
              When Trust is a Must
              <Divider className="carousel-divider" />
            </div>
            <Link to="/order" className="remove-link-decoration">
              <Button
                color="primary"
                variant="contained"
                className="carousel-button"
              >
                Order Now
              </Button>
            </Link>
          </div>
          <img src={Courier2} alt="courier-2" />
        </div>
        <div className="carousel-slide">
          <div className="carousel-slide-content-wrapper">
            <div className="carousel-slide-text">
              Always on time
              <Divider className="carousel-divider" />
            </div>
            <Link to="/order" className="remove-link-decoration">
              <Button
                color="primary"
                variant="contained"
                className="carousel-button"
              >
                Order Now
              </Button>
            </Link>
          </div>
          <img src={Courier3} alt="courier-3" />
        </div>
      </Carousel>
    </div>
  );
}

export default LandingCarousel;
