import React from "react";
import { Formik, Form } from "formik";
import * as yup from "yup";
import { Container, Button } from "@material-ui/core";
import Footer from "./Footer";
import Header from "./Header";
import Field from "./Field";
import { Link, useHistory } from "react-router-dom";
import "../styles/Login.css";
import axiosInstance from "../api/axiosInstance";
import { CURRENT_USER, LOGIN } from "../api/config";
import { useDispatch } from "react-redux";
import { updateUser } from "../store/UserAuthentication/user-authentication-actions";
import useMessage from "../hooks/messages";

function Login() {
  const validationSchema = yup.object({
    email: yup.string().required("Required").email("Invalid E-mail"),
    password: yup
      .string()
      .required("Required")
      .max(50, "Your password is too long!")
      .min(8, "Password must be at least 8 characters long!"),
  });

  const dispatch = useDispatch();
  const { displayError } = useMessage()
  const history = useHistory()

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
            (async function () {
              try{
                const responseToken = await axiosInstance.post(LOGIN, data)
                localStorage.setItem("token", responseToken.token)
                const responseUser = await axiosInstance.get(CURRENT_USER)
                dispatch(updateUser(responseUser))
                history.push('/')
              }
              catch (e){
                displayError("Failed to login.")
              }
            })()
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
