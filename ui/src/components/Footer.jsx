import React from "react";
import { Divider, Container } from "@material-ui/core";
import "../styles/Footer.css";

function Footer() {
  return (
    <Container className="footer-wrapper">
      <Divider />
      <span className="footer-copyright">&copy; Davai Davai Deploy Ltd.</span>
    </Container>
  );
}

export default Footer;
