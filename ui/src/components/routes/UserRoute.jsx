import { Typography } from "@material-ui/core";
import { useSelector } from "react-redux";
import { USER_ROLE } from "../../services/authentication/roles";
import { Route } from "react-router-dom";

export function UserRoute(props) {
  const { children, ...rest } = props;
  const roles = useSelector((state) => state.userAuthentication.userRoles);

  return (
    <Route {...rest}>
      {roles.includes(USER_ROLE) ? (
        children
      ) : (
        <Typography align="center">Login to view this page</Typography>
      )}
    </Route>
  );
}
