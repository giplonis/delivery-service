import React from "react";
import "../styles/ImageBox.css";

function ImageBox(props) {
  return (
    <div className="image-box-wrapper d-flex justify-content-center ">
      <img src={props.image} alt={props.alt} className="image-box-image" />
    </div>
  );
}

export default ImageBox;
