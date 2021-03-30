import React, { useState } from "react";
import SendingInfo from "./SendingInfo";
import Header from "./Header";
import Footer from "./Footer";
import DocumentSize from "./DocumentSize";
import { Container } from "@material-ui/core";
import "../styles/Form.css";
import ParcelSize from "./ParcelSize";
import ParcelType from "./ParcelType";

function Form() {
  var boxSizes = [
    { name: "Small", width: 35, height: 16, length: 45, weigth: 2 },
    { name: "Medium", width: 46, height: 46, length: 64, weigth: 20 },
    { name: "Large", width: 150, height: 150, length: 150, weigth: 30 },
  ];

  const [selectedPackageType, setSelectedPackageType] = useState("Box");
  const [selectedBoxSize, setSelectedBoxSize] = useState(boxSizes[0].name);

  return (
    <div>
      <Container>
        <div className="content-wrapper">
          <Header />
          <SendingInfo />
          {/* <DocumentSize /> */}
          {/* <ParcelType selectedPackageType={selectedPackageType} onChange={(name) => setSelectedPackageType(name)} /> */}
          {/* <ParcelSize selectedBoxSize={selectedBoxSize} onChange={(name) => setSelectedBoxSize(name)} boxSizes={boxSizes} /> */}
        </div>
        <Footer />
      </Container>
    </div>
  );
}

export default Form;
