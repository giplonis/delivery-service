import React from "react";
import Button from "@material-ui/core/Button";
import CircularProgress from "@material-ui/core/CircularProgress";

const CustomCircularProgress = () => (
  <CircularProgress size={20} color="inherit" style={{ marginLeft: 15 }} />
);
const LoadingButton = (props) => {
  const { children, loading, ...rest } = props;
  return (
    <Button {...rest}>
      {children}
      {loading && <CustomCircularProgress />}
    </Button>
  );
};

export default LoadingButton;
