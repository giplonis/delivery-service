import React, { useState } from "react";
import Field from "./Field";
import { Grid, Button } from "@material-ui/core";
import { KeyboardDatePicker, MuiPickersUtilsProvider } from "@material-ui/pickers";
import "../styles/SendingInfo.css";
import "date-fns";
import DateFnsUtils from "@date-io/date-fns";
import * as yup from "yup";
import { Formik, Form, useField } from "formik";

const CustomKeyboardDatePicker = ({ variant, format, margin, label, onChange, className, ...props }) => {
  const [fields, meta] = useField(props);
  const errorText = meta.error && meta.touched ? meta.error : "";
  return (
    <KeyboardDatePicker
      disableToolbar
      variant={variant}
      format={format}
      margin={margin}
      label={label}
      onChange={onChange}
      value={fields.value}
      className={className}
      helperText={errorText}
      error={!!errorText}
      name={fields.name}
      onBlur={fields.onBlur}
    />
  );
};

function SendingInfo(props) {
  const labels = [
    { label: "First Name", name: "name" },
    { label: "Last Name", name: "surname" },
    { label: "Phone Number", name: "number" },
    { label: "City", name: "city" },
    { label: "Address", name: "address" },
  ];

  const [selectedDate, setSelectedDate] = useState(null);

  const handleDateChange = (date) => {
    setSelectedDate(date);
  };

  const validationSchema = yup.object({
    sender: yup.object({
      name: yup.string().required("Required").max(30, "First Name is too long!"),
      surname: yup.string().required("Required").max(30, "Last Name is too long!"),
      number: yup
        .string()
        .required("Required")
        .matches(/^(\+?\d{0,4})?\s?-?\s?(\(?\d{3}\)?)\s?-?\s?(\(?\d{3}\)?)\s?-?\s?(\(?\d{4}\)?)?$/, "Invalid Phone Number!"),
      city: yup.string().required("Required").max(30, "City Name is too long!"),
      address: yup.string().required("Required").max(50, "Address is too long!"),
    }),
    recipient: yup.object({
      name: yup.string().required("Required").max(30, "First Name is too long!"),
      surname: yup.string().required("Required").max(30, "Last Name is too long!"),
      number: yup
        .string()
        .required("Required")
        .matches(/^(\+?\d{0,4})?\s?-?\s?(\(?\d{3}\)?)\s?-?\s?(\(?\d{3}\)?)\s?-?\s?(\(?\d{4}\)?)?$/, "Invalid Phone Number!"),
      city: yup.string().required("Required").max(30, "City Name is too long!"),
      address: yup.string().required("Required").max(50, "Address is too long!"),
    }),
    email: yup.string().required("Required").email("Invalid E-mail"),
    pickUpDate: yup
      .date()
      .required("Required")
      .typeError("Invalid date")
      .min(new Date(Date.now() - 86400000), "Back to the future!"),
  });

  return (
    <Formik
      initialValues={
        !!props.formData
          ? props.formData
          : {
              sender: { name: "", surname: "", number: "", city: "", address: "" },
              recipient: { name: "", surname: "", number: "", city: "", address: "" },
              email: "",
              pickUpDate: selectedDate,
            }
      }
      validationSchema={validationSchema}
      onSubmit={(data) => {
        props.submitForm(data);
        props.NextPage();
      }}>
      {({ values, errors, setFieldValue }) => (
        <Form>
          <div className="form-wrapper">
            <Grid container spacing={9}>
              <Grid item xs={12} sm={6} className="form-column">
                <div className="form-inner">
                  <div className="form-header">Sender Info</div>
                  {labels.map((label, key) => (
                    <Field key={key} label={label.label} name={`sender.${label.name}`} />
                  ))}
                  <Field label="E-mail" name="email" />
                  <MuiPickersUtilsProvider utils={DateFnsUtils}>
                    <CustomKeyboardDatePicker
                      disableToolbar
                      variant="inline"
                      format="yyyy/MM/dd"
                      margin="normal"
                      label="Pick Up Date"
                      onChange={(value) => {
                        setFieldValue("pickUpDate", value !== null ? value.getFullYear() + "/" + (value.getMonth() + 1) + "/" + value.getDate() : "");
                        handleDateChange(value);
                      }}
                      value={selectedDate}
                      className="input-field"
                      name="pickUpDate"
                    />
                  </MuiPickersUtilsProvider>
                </div>
              </Grid>
              <Grid item xs={12} sm={6} className="form-column">
                <div className="form-inner">
                  <div className="form-header">Recipient Info</div>
                  {labels.map((label, key) => (
                    <Field key={key} label={label.label} name={`recipient.${label.name}`} />
                  ))}
                </div>
              </Grid>
            </Grid>
            <div className="d-flex">
              <Button color="primary" variant="contained" className="form-button form-button-left" onClick={props.PreviousPage}>
                Back
              </Button>
              <Button color="primary" variant="contained" className="form-button" type="submit">
                Next
              </Button>
            </div>
          </div>
        </Form>
      )}
    </Formik>
  );
}

export default SendingInfo;
