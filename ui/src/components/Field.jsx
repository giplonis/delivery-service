import React from "react";
import TextField from "@material-ui/core/TextField";
import "../styles/Field.css";
import { useField } from "formik";

const CustomTextField = ({ label, className, type, ...props }) => {
  const [field, meta] = useField(props);
  const errorText = meta.error && meta.touched ? meta.error : "";
  return <TextField className={className} label={label} {...field} helperText={errorText} error={!!errorText} type={type} />;
};

function Field(props) {
  return (
    <div>
      <CustomTextField className="input-field" label={props.label} name={props.name} type={props.type} />
    </div>
  );
}

export default Field;
