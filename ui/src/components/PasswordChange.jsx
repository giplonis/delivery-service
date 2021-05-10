import React, { useState } from "react";
import { Formik, Form } from "formik";
import Field from "./Field";
import * as yup from "yup";
import axiosInstance from "../api/axiosInstance";
import useMessage from "../hooks/messages";
import { USER_PASSWORD } from "../api/config";
import LoadingButton from "./LoadingButton";

function PasswordChange() {
  const { displayError, displaySuccess } = useMessage();
  const [loading, setLoading] = useState(false);

  const labels = [
    { label: "Current Password", name: "oldPassword" },
    { label: "New Password", name: "password" },
    { label: "Confirm Password", name: "passwordConfirm" },
  ];

  const validationSchema = yup.object({
    oldPassword: yup
      .string()
      .required("Required")
      .max(50, "Your password is too long!")
      .min(8, "Password must be at least 8 characters long!"),
    password: yup
      .string()
      .required("Required")
      .max(50, "Your password is too long!")
      .min(8, "Password must be at least 8 characters long!")
      .notOneOf(
        [yup.ref("oldPassword"), null],
        "Password must not match your current one!"
      ),
    passwordConfirm: yup
      .string()
      .required("Required")
      .oneOf([yup.ref("password"), null], "Passwords don't match!"),
  });

  return (
    <Formik
      initialValues={{
        oldPassword: "",
        password: "",
        passwordConfirm: "",
      }}
      validationSchema={validationSchema}
      onSubmit={(data, { resetForm }) => {
        (async function () {
          setLoading(true);
          try {
            const response = await axiosInstance.put(USER_PASSWORD, data);
            localStorage.setItem("token", response.token);
            resetForm();
            displaySuccess("Password changed successfully.");
          } catch (e) {
            if (e.response.data.message === "Password is incorrect")
              displayError("Password is incorrect!");
            else displayError("Failed to change password.");
          } finally {
            setLoading(false);
          }
        })();
      }}
    >
      <Form>
        <div className="form-wrapper">
          <div className="form-inner">
            <div className="form-header">Change Password</div>
            {labels.map((label, key) => (
              <Field
                key={key}
                label={label.label}
                name={label.name}
                type="password"
              />
            ))}
            <LoadingButton
              color="primary"
              variant="contained"
              className="form-button save-personal-data-button"
              type="submit"
              loading={loading}
              disabled={loading}
            >
              Change Password
            </LoadingButton>
          </div>
        </div>
      </Form>
    </Formik>
  );
}

export default PasswordChange;
