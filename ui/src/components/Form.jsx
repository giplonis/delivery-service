import React, { useState, useEffect } from "react";
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
import { PACKAGE_SIZES } from "../config";
import PaymentSuccess from "./PaymentSuccess";

function Form() {
  const { displayError } = useMessage();
  const [selectedPackageType, setSelectedPackageType] = useState(undefined);
  const [selectedBoxSize, setSelectedBoxSize] = useState(undefined);
  const [selectedDocumentSize, setSelectedDocumentSize] = useState(undefined);
  const [selectedPaymentType, setSelectedPaymentType] = useState(undefined);
  const [currentPage, setCurrentPage] = useState(0);
  const [progress, setProgress] = useState(25);
  const [formData, setFormData] = useState(undefined);
  const [packageSizes, setPackageSizes] = useState(undefined);

  useEffect(() => {
    const requestOptions = {
      method: "GET",
      headers: { "Content-Type": "application/json" },
    };
    (async function () {
      try {
        const response = await fetch(PACKAGE_SIZES, requestOptions);
        const responseJson = await response.json();
        setPackageSizes(responseJson);
      } catch (e) {
        displayError("Failed to load package sizes");
      }
    })();
  }, [displayError]);

  function NextPage(e) {
    if (currentPage === 0 && selectedPackageType === undefined) {
      displayError("Parcel type is not selected!");
      return;
    } else if (
      currentPage === 1 &&
      selectedBoxSize === undefined &&
      selectedPackageType === "Box"
    ) {
      displayError("Box size is not selected!");
      return;
    } else if (
      currentPage === 1 &&
      selectedDocumentSize === undefined &&
      selectedPackageType === "Document"
    ) {
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
          <LinearProgress
            variant="determinate"
            color="primary"
            className="progress-bar"
            value={progress}
          />
        </>
      );
    } else if (currentPage === 1 && selectedPackageType === "Document") {
      return (
        <>
          <DocumentSize
            NextPage={NextPage}
            PreviousPage={PreviousPage}
            onChange={(name) => setSelectedDocumentSize(name)}
            letterSizes={packageSizes.data.slice(3, 6)}
            selectedDocumentSize={selectedDocumentSize}
          />
          <LinearProgress
            variant="determinate"
            className="progress-bar"
            value={progress}
          />
        </>
      );
    } else if (currentPage === 1 && selectedPackageType === "Box") {
      return (
        <>
          <ParcelSize
            selectedBoxSize={selectedBoxSize}
            onChange={(name) => setSelectedBoxSize(name)}
            boxSizes={packageSizes.data.slice(0, 3)}
            NextPage={NextPage}
            PreviousPage={PreviousPage}
          />
          <LinearProgress
            variant="determinate"
            className="progress-bar"
            value={progress}
          />
        </>
      );
    } else if (currentPage === 2) {
      return (
        <>
          <SendingInfo
            NextPage={NextPage}
            PreviousPage={PreviousPage}
            submitForm={(data) => setFormData(data)}
            formData={formData}
          />
          <LinearProgress
            variant="determinate"
            className="progress-bar"
            value={progress}
          />
        </>
      );
    } else if (currentPage === 3) {
      return (
        <>
          <Summary
            PreviousPage={PreviousPage}
            onOrderSuccess={() => setCurrentPage(1000)}
            onChange={(name) => setSelectedPaymentType(name)}
            selectedPaymentType={selectedPaymentType}
            selectedPackageType={selectedPackageType}
            selectedPackage={
              selectedPackageType === "Document"
                ? packageSizes.data.find(
                    (o) => o.title === selectedDocumentSize
                  )
                : packageSizes.data.find((o) => o.title === selectedBoxSize)
            }
            formData={formData}
          />
          <LinearProgress
            variant="determinate"
            className="progress-bar"
            value={progress}
          />
        </>
      );
    }
    else {
      return (
        <PaymentSuccess/>
      )
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
