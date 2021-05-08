import React, { useState } from "react";
import Header from "./Header";
import Footer from "./Footer";
import { Container, Grid } from "@material-ui/core";
import { Formik, Form } from "formik";
import Field from "./Field";
import * as yup from "yup";
import { Link, useHistory } from "react-router-dom";
import "../styles/Register.css";
import { CURRENT_USER, REGISTER } from "../api/config";
import { useDispatch } from "react-redux";
import { updateUser } from "../store/UserAuthentication/user-authentication-actions";
import useMessage from "../hooks/messages";
import LoadingButton from "./LoadingButton";
import axiosInstance from "../api/axiosInstance";

function Register() {
  const dispatch = useDispatch();
  const { displayError } = useMessage();
  const history = useHistory();
  const [loading, setLoading] = useState(false);

  const labels = [
    { label: "First Name", name: "name", type: "text" },
    { label: "City", name: "city", type: "text" },
    { label: "E-mail", name: "email", type: "text" },
    { label: "Password", name: "password", type: "password" },
    { label: "Last Name", name: "surname", type: "text" },
    { label: "Address", name: "address", type: "text" },
    { label: "Phone Number", name: "number", type: "text" },
    { label: "Confirm Password", name: "confirmPassword", type: "password" },
  ];

  const validationSchema = yup.object({
    name: yup.string().required("Required").max(30, "First Name is too long!"),
    surname: yup
      .string()
      .required("Required")
      .max(30, "Last Name is too long!"),
    number: yup
      .string()
      .required("Required")
      .matches(
        /^(\+?\d{0,4})?\s?-?\s?(\(?\d{3}\)?)\s?-?\s?(\(?\d{3}\)?)\s?-?\s?(\(?\d{4}\)?)?$/,
        "Invalid Phone Number!"
      ),
    city: yup.string().required("Required").max(30, "City Name is too long!"),
    address: yup.string().required("Required").max(50, "Address is too long!"),
    email: yup.string().required("Required").email("Invalid E-mail"),
    password: yup
      .string()
      .required("Required")
      .max(50, "Your password is too long!")
      .min(8, "Password must be at least 8 characters long!"),
    confirmPassword: yup
      .string()
      .required("Required")
      .oneOf([yup.ref("password"), null], "Passwords don't match!"),
  });

  return (
    <Container>
      <div className="content-wrapper">
        <Header />
        <Formik
          initialValues={{
            name: "",
            surname: "",
            city: "",
            address: "",
            email: "",
            number: "",
            password: "",
            confirmPassword: "",
          }}
          validationSchema={validationSchema}
          onSubmit={(data) => {
            // Submit data here
            (async function () {
              setLoading(true);
              try {
                const responseToken = await axiosInstance.post(REGISTER, data);
                localStorage.setItem("token", responseToken.token);
                try {
                  const responseUser = await axiosInstance.get(CURRENT_USER);
                  dispatch(updateUser(responseUser));
                  history.push("/");
                } catch (e) {
                  displayError("Failed to update user.");
                }
              } catch (e) {
                displayError("Failed to register.");
              } finally {
                setLoading(false);
              }
            })();
          }}
        >
          <Form>
            <div className="form-wrapper center-form">
              <div className="form-inner">
                <div className="form-header">Register</div>
                <Grid container spacing={7}>
                  <Grid item xs={6}>
                    {labels.slice(0, 4).map((label, key) => (
                      <Field
                        key={key}
                        name={label.name}
                        label={label.label}
                        type={label.type}
                      />
                    ))}
                  </Grid>
                  <Grid item xs={6}>
                    {labels.slice(4, 8).map((label, key) => (
                      <Field
                        key={key}
                        name={label.name}
                        label={label.label}
                        type={label.type}
                      />
                    ))}
                  </Grid>
                </Grid>
                <div className="mt-2">
                  <Link to="/login" className="remove-link-decoration d-flex">
                    <span className="auth-link">Log in here!</span>
                  </Link>
                </div>
                <LoadingButton
                  color="primary"
                  variant="contained"
                  type="submit"
                  className="auth-button"
                  loading={loading}
                  disabled={loading}
                >
                  Register
                </LoadingButton>
              </div>
            </div>
          </Form>
        </Formik>
      </div>
      <Footer />
    </Container>
  );
}

export default Register;
