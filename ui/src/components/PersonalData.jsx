import React from "react";
import { Formik, Form } from "formik";
import Field from "./Field";
import { Grid, Button } from "@material-ui/core";
import * as yup from "yup";
import "../styles/PersonalData.css";
import { useSelector } from "react-redux";

function PersonalData() {
  const user = useSelector((state) => state.userAuthentication.user);

  const labels = [
    { label: "First Name", name: "firstName" },
    { label: "Last Name", name: "lastName" },
    { label: "Phone Number", name: "phoneNumber" },
    { label: "City", name: "address.city" },
    { label: "Address", name: "address.street" },
    { label: "E-mail", name: "email" },
  ];

  const validationSchema = yup.object({
    firstName: yup.string().required("Required").max(30, "First Name is too long!"),
    lastName: yup.string().required("Required").max(30, "Last Name is too long!"),
    email: yup.string().required("Required").email("Invalid E-mail"),
    phoneNumber: yup
      .string()
      .required("Required")
      .matches(/^(\+?\d{0,4})?\s?-?\s?(\(?\d{3}\)?)\s?-?\s?(\(?\d{3}\)?)\s?-?\s?(\(?\d{4}\)?)?$/, "Invalid Phone Number!"),
    address: yup.object({
      city: yup.string().required("Required").max(30, "City Name is too long!"),
      street: yup.string().required("Required").max(50, "Address is too long!"),
    }),
  });

  return (
    <Formik
      // GET initial values here when API is done
      initialValues={{
        firstName: user.firstName,
        lastName: user.lastName,
        email: user.email,
        phoneNumber: user.phoneNumber,
        address: {
          city: user.address.city,
          street: user.address.street,
        },
      }}
      validationSchema={validationSchema}
      onSubmit={(data) => {
        // Submit data to <Profile /> here
      }}>
      <Form>
        <div className="form-wrapper">
          <div className="form-inner">
            <div className="form-header">Personal Data</div>
            <Grid container spacing={7}>
              <Grid item xs={6}>
                {labels.slice(0, 3).map((label, key) => (
                  <Field key={key} label={label.label} name={label.name} />
                ))}
              </Grid>
              <Grid item xs={6}>
                {labels.slice(3, 6).map((label, key) => (
                  <Field key={key} label={label.label} name={label.name} />
                ))}
              </Grid>
            </Grid>
            <Button color="primary" variant="contained" className="form-button save-personal-data-button" type="submit">
              Save
            </Button>
          </div>
        </div>
      </Form>
    </Formik>
  );
}

export default PersonalData;
