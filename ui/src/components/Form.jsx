import React from "react";
import SendingInfo from "./SendingInfo";
import Header from "./Header";
import { Container } from "@material-ui/core";

function Form() {
  return (
    <div>
      <Container>
        <Header />
        <SendingInfo />
      </Container>
    </div>
  );
}

export default Form;
