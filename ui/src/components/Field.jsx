import React from "react";
import TextField from "@material-ui/core/TextField";
import "../styles/Field.css";

function Field(props) {
  return (
    <div>
      <TextField label={props.label} className="input-field" />
    </div>
  );
}

export default Field;
