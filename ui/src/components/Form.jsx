import React, {useState} from "react";
import ParcelSize from "./ParcelSize";
import ParcelType from "./ParcelType";

var boxSizes = [
  {name: "Small", width: 10, height: 8, length: 15},
  {name: "Medium", width: 15, height: 12, length: 20},
  {name: "Large", width: 20, height: 17, length: 25}
]

function Form() {
  const [selectedPackageType, setSelectedPackageType] = useState("Box");
  const [selectedBoxSize, setSelectedBoxSize] = useState(boxSizes[0].name);
  
  return(
    <>
      <ParcelType
        selectedPackageType = {selectedPackageType}
        onChange = {(name) => setSelectedPackageType(name)}
      />
      {/* <ParcelSize
        selectedBoxSize = {selectedBoxSize}
        onChange = {(name) => setSelectedBoxSize(name)}
        boxSizes = {boxSizes}
      /> */}
    </>
    
  )
}

export default Form;
