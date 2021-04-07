import React from "react";
import { Formik, Form } from "formik";
import Field from "./Field";
import { Button } from "@material-ui/core";
import * as yup from "yup";

function PasswordChange() {
  const labels = [
    { label: "Current Password", name: "password" },
    { label: "New Password", name: "newPassword" },
    { label: "Confirm Password", name: "confirmPassword" },
  ];

  const validationSchema = yup.object({
    password: yup.string().required("Required").max(50, "Your password is too long!").min(8, "Password must be at least 8 characters long!"),
    newPassword: yup
      .string()
      .required("Required")
      .max(50, "Your password is too long!")
      .min(8, "Password must be at least 8 characters long!")
      .notOneOf([yup.ref("password"), null], "Password must not match your current one!"),
    confirmPassword: yup
      .string()
      .required("Required")
      .oneOf([yup.ref("newPassword"), null], "Passwords don't match!"),
  });

  return (
    <Formik
      initialValues={{
        password: "",
        newPassword: "",
        confirmPassword: "",
      }}
      validationSchema={validationSchema}
      onSubmit={(data) => {
        // Submit data to <Profile /> here
      }}>
      <Form>
        <div className="form-wrapper">
          <div className="form-inner">
            <div className="form-header">Change Password</div>
            {labels.map((label, key) => (
              <Field key={key} label={label.label} name={label.name} type="password" />
            ))}
            <Button color="primary" variant="contained" className="form-button save-personal-data-button" type="submit">
              Change Password
            </Button>
          </div>
        </div>
      </Form>
    </Formik>
  );
}

export default PasswordChange;
