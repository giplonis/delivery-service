import React, { useState } from "react";
import SendingInfo from "./SendingInfo";
import Header from "./Header";
import Footer from "./Footer";
import DocumentSize from "./DocumentSize";
import { Container, LinearProgress } from "@material-ui/core";
import "../styles/Form.css";
import ParcelSize from "./ParcelSize";
import ParcelType from "./ParcelType";
import Summary from "./Summary";
import useMessage from "../hooks/messages";

function Form() {
  const { displayError } = useMessage();

  var boxSizes = [
    { id: 1, name: "Small Box", width: 35, height: 16, length: 45, weight: 2 },
    { id: 2, name: "Medium Box", width: 46, height: 46, length: 64, weight: 20 },
    { id: 3, name: "Large Box", width: 150, height: 150, length: 150, weight: 30 },
  ];

  var letterSizes = [
    { id: 4, name: "Small Letter", weight: 100, length: 24, width: 16 },
    { id: 5, name: "Large Letter", weight: 750, length: 35, width: 25 },
  ];

  const [selectedPackageType, setSelectedPackageType] = useState(undefined);
  const [selectedBoxSize, setSelectedBoxSize] = useState(undefined);
  const [selectedDocumentSize, setSelectedDocumentSize] = useState(undefined);
  const [selectedPaymentType, setSelectedPaymentType] = useState(undefined);
  const [currentPage, setCurrentPage] = useState(0);
  const [progress, setProgress] = useState(25);
  const [formData, setFormData] = useState(undefined);

  function NextPage(e) {
    if (currentPage === 0 && selectedPackageType === undefined) {
      displayError("Parcel type is not selected!");
      return;
    } else if (currentPage === 1 && selectedBoxSize === undefined && selectedPackageType === "Box") {
      displayError("Box size is not selected!");
      return;
    } else if (currentPage === 1 && selectedDocumentSize === undefined && selectedPackageType === "Document") {
      displayError("Letter size is not selected!");
      return;
    }
    setCurrentPage(currentPage + 1);
    setProgress(progress + 25);
  }

  function PreviousPage(e) {
    setCurrentPage(currentPage - 1);
    setProgress(progress - 25);
  }

  function selectPage() {
    if (currentPage === 0) {
      return (
        <>
          <ParcelType
            selectedPackageType={selectedPackageType}
            onChange={(name) => setSelectedPackageType(name)}
            NextPage={NextPage}
            PreviousPage={PreviousPage}
          />
          <LinearProgress variant="determinate" color="primary" className="progress-bar" value={progress} />
        </>
      );
    } else if (currentPage === 1 && selectedPackageType === "Document") {
      return (
        <>
          <DocumentSize
            NextPage={NextPage}
            PreviousPage={PreviousPage}
            onChange={(name) => setSelectedDocumentSize(name)}
            letterSizes={letterSizes}
            selectedDocumentSize={selectedDocumentSize}
          />
          <LinearProgress variant="determinate" className="progress-bar" value={progress} />
        </>
      );
    } else if (currentPage === 1 && selectedPackageType === "Box") {
      return (
        <>
          <ParcelSize
            selectedBoxSize={selectedBoxSize}
            onChange={(name) => setSelectedBoxSize(name)}
            boxSizes={boxSizes}
            NextPage={NextPage}
            PreviousPage={PreviousPage}
          />
          <LinearProgress variant="determinate" className="progress-bar" value={progress} />
        </>
      );
    } else if (currentPage === 2) {
      return (
        <>
          <SendingInfo NextPage={NextPage} PreviousPage={PreviousPage} submitForm={(data) => setFormData(data)} formData={formData} />
          <LinearProgress variant="determinate" className="progress-bar" value={progress} />
        </>
      );
    } else {
      return (
        <>
          <Summary
            PreviousPage={PreviousPage}
            onChange={(name) => setSelectedPaymentType(name)}
            selectedPaymentType={selectedPaymentType}
            selectedPackageType={selectedPackageType}
            selectedPackage={
              selectedPackageType === "Document"
                ? letterSizes.find((o) => o.name === selectedDocumentSize)
                : boxSizes.find((o) => o.name === selectedBoxSize)
            }
            formData={formData}
          />
          <LinearProgress variant="determinate" className="progress-bar" value={progress} />
        </>
      );
    }
  }
  return (
    <div>
      <Container>
        <div className="content-wrapper">
          <Header />
          {selectPage()}
        </div>
        <Footer />
      </Container>
    </div>
  );
}

export default Form;
