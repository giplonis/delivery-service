import React from "react";
import { Formik, Form } from "formik";
import * as yup from "yup";
import { Container, Button } from "@material-ui/core";
import Footer from "./Footer";
import Header from "./Header";
import Field from "./Field";
import { Link } from "react-router-dom";
import "../styles/Login.css";

function Login() {
  const validationSchema = yup.object({
    email: yup.string().required("Required").email("Invalid E-mail"),
    password: yup
      .string()
      .required("Required")
      .max(50, "Your password is too long!")
      .min(8, "Password must be at least 8 characters long!"),
  });

  return (
    <Container>
      <div className="content-wrapper">
        <Header />
        <Formik
          initialValues={{
            email: "",
            password: "",
          }}
          validationSchema={validationSchema}
          onSubmit={(data) => {
            // Submit data here
          }}
        >
          <Form>
            <div className="form-wrapper center-form">
              <div className="form-inner">
                <div className="form-header">Login</div>
                <Field label="E-mail" name="email" />
                <Field label="Password" name="password" type="password" />
                <div className="d-flex mt-2">
                  <Link
                    to="/register"
                    className="remove-link-decoration d-flex"
                  >
                    <span className="auth-link">Sign up here!</span>
                  </Link>
                  <span className="ml-auto auth-link">Forgot password?</span>
                </div>
                <Button
                  color="primary"
                  variant="contained"
                  type="submit"
                  className="auth-button"
                >
                  Login
                </Button>
              </div>
            </div>
          </Form>
        </Formik>
      </div>
      <Footer />
    </Container>
  );
}

export default Login;
