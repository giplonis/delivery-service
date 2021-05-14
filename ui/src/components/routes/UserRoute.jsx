import { Typography, Button } from "@material-ui/core";
import { useSelector } from "react-redux";
import { USER_ROLE } from "../../services/authentication/roles";
import { Route } from "react-router-dom";
import { Link } from "react-router-dom";
import { getLoginPath } from "../../services/navigation/paths";

export function UserRoute(props) {
  const { children, ...rest } = props;
  const roles = useSelector((state) => state.userAuthentication.userRoles);

  return (
    <Route {...rest}>
      {roles.includes(USER_ROLE) ? (
        children
      ) : (
        <>
          <Typography align="center">Log In to view this page</Typography>
          <Link to={getLoginPath()} className="remove-link-decoration">
            <Button
              color="primary"
              variant="contained"
              className="forbidden-button"
            >
              Log In
            </Button>
          </Link>
        </>
      )}
    </Route>
  );
}
