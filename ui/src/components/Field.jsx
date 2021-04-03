import React from "react";
import TextField from "@material-ui/core/TextField";
import "../styles/Field.css";
import { useField } from "formik";

const CustomTextField = ({ label, className, ...props }) => {
  const [field, meta] = useField(props);
  const errorText = meta.error && meta.touched ? meta.error : "";
  return <TextField className={className} label={label} {...field} helperText={errorText} error={!!errorText} />;
};

function Field(props) {
  return (
    <div>
      <CustomTextField className="input-field" label={props.label} name={props.name} />
    </div>
  );
}

export default Field;
