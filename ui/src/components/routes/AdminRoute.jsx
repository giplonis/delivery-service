import { Typography, Button } from "@material-ui/core";
import { useSelector } from "react-redux";
import { ADMIN_ROLE } from "../../services/authentication/roles";
import { Route } from "react-router-dom";
import { Link } from "react-router-dom";
import { getHomePath } from "../../services/navigation/paths";

export function AdminRoute(props) {
  const { children, ...rest } = props;
  const roles = useSelector((state) => state.userAuthentication.userRoles);

  return (
    <Route {...rest}>
      {roles.includes(ADMIN_ROLE) ? (
        children
      ) : (
        <>
          <Typography align="center">
            Only an Administrator can access this page
          </Typography>
          <Link to={getHomePath()} className="remove-link-decoration">
            <Button
              color="primary"
              variant="contained"
              className="forbidden-button"
            >
              Back to Main Page
            </Button>
          </Link>
        </>
      )}
    </Route>
  );
}
