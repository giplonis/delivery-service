import { Typography } from "@material-ui/core";
import { useSelector } from "react-redux";
import { ADMIN_ROLE } from "../../services/authentication/roles";
import { Route } from "react-router-dom";

export function AdminRoute(props) {
  const { children, ...rest } = props;
  const roles = useSelector((state) => state.userAuthentication.userRoles);

  return (
    <Route {...rest}>
      {roles.includes(ADMIN_ROLE) ? (
        children
      ) : (
        <Typography align="center">
          Need to be an admin to view this page
        </Typography>
      )}
    </Route>
  );
}
