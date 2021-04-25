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
import PaymentSuccess from "./PaymentSuccess";
import { PACKAGE_OPTIONS } from "../config";

function Form() {
  const { displayError } = useMessage();
  const [selectedPackageType, setSelectedPackageType] = useState(undefined);
  const [selectedBoxSize, setSelectedBoxSize] = useState(undefined);
  const [selectedDocumentSize, setSelectedDocumentSize] = useState(undefined);
  const [selectedPaymentType, setSelectedPaymentType] = useState(undefined);
  const [currentPage, setCurrentPage] = useState(0);
  const [progress, setProgress] = useState(25);
  const [formData, setFormData] = useState(undefined);
  const [packageOptions, setPackageOptions] = useState(undefined);

  useEffect(() => {
    const requestOptions = {
      method: "GET",
      headers: { "Content-Type": "application/json" },
    };
    (async function () {
      try {
        const response = await fetch(PACKAGE_OPTIONS, requestOptions);
        const responseJson = await response.json();
        setPackageOptions(responseJson.data);
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
            letterSizes={packageOptions.filter(
              (o) => o.packageType.title === "Document"
            )}
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
            boxSizes={packageOptions.filter(
              (o) => o.packageType.title === "Package" && o.fragile === false
            )}
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
                ? packageOptions.find(
                    (o) => o.packageSize.title === selectedDocumentSize
                  )
                : packageOptions.find(
                    (o) => o.packageSize.title === selectedBoxSize
                  )
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
    } else {
      return <PaymentSuccess />;
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
