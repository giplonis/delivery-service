import React from "react";
import SendingInfo from "./SendingInfo";
import Header from "./Header";
import Footer from "./Footer";
import { Container } from "@material-ui/core";
import "../styles/Form.css";

function Form() {
  return (
    <div>
      <Container>
        <div className="content-wrapper">
          <Header />
          <SendingInfo />
        </div>
        <Footer />
      </Container>
    </div>
  );
}

export default Form;
