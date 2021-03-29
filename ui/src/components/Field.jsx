import React from "react";
import TextField from "@material-ui/core/TextField";

function Field(props) {
  return (
    <div>
      <TextField label={props.label} />
    </div>
  );
}

export default Field;
