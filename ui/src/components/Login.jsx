import React, { useState } from "react";
import { Formik, Form } from "formik";
import * as yup from "yup";
import Field from "./Field";
import { Link, useHistory } from "react-router-dom";
import "../styles/Login.css";
import axiosInstance from "../api/axiosInstance";
import { CURRENT_USER, LOGIN } from "../api/config";
import { useDispatch } from "react-redux";
import { setAuthToken, updateUser } from "../store/UserAuthentication/user-authentication-actions";
import useMessage from "../hooks/messages";
import LoadingButton from "./LoadingButton";
import { getHomePath, getRegisterPath } from "../services/navigation/paths";

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
  const { displayError } = useMessage();
  const history = useHistory();
  const [loading, setLoading] = useState(false);

  return (
        <Formik
          initialValues={{
            email: "",
            password: "",
          }}
          validationSchema={validationSchema}
          onSubmit={(data) => {
            // Submit data here
            (async function () {
              setLoading(true);
              try {
                const responseToken = await axiosInstance.post(LOGIN, data);
                dispatch(setAuthToken(responseToken.token));
                const responseUser = await axiosInstance.get(CURRENT_USER);
                dispatch(updateUser(responseUser));
                history.push(getHomePath());
              } catch (e) {
                displayError("Failed to login.");
              } finally {
                setLoading(false);
              }
            })();
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
                    to={getRegisterPath()}
                    className="remove-link-decoration d-flex"
                  >
                    <span className="auth-link">Sign up here!</span>
                  </Link>
                  <span className="ml-auto auth-link">Forgot password?</span>
                </div>
                <LoadingButton
                  color="primary"
                  variant="contained"
                  type="submit"
                  className="auth-button"
                  loading={loading}
                  disabled={loading}
                >
                  Login
                </LoadingButton>
              </div>
            </div>
          </Form>
        </Formik>
  );
}

export default Login;
