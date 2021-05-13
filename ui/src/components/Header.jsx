import React from "react";
import PersonIcon from "@material-ui/icons/Person";
import ExitToAppIcon from "@material-ui/icons/ExitToApp";
import SupervisedUserCircleOutlinedIcon from "@material-ui/icons/SupervisedUserCircleOutlined";
import { Divider } from "@material-ui/core";
import "../styles/Header.css";
import { Link, useHistory } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { logoutUser } from "../store/UserAuthentication/user-authentication-actions";
import {
  getAdminPath,
  getHomePath,
  getLoginPath,
  getProfilePath,
} from "../services/navigation/paths";
import { ADMIN_ROLE } from "../services/authentication/roles";

function Header() {
  const user = useSelector((state) => state.userAuthentication.user);
  const roles = useSelector((state) => state.userAuthentication.userRoles);
  const dispatch = useDispatch();
  const history = useHistory();

  return (
    <header>
      <div className="header-inner">
        <Link to={getHomePath()} className="remove-link-decoration">
          <h2>Logo</h2>
        </Link>
        <div className="d-flex ">
          {roles.includes(ADMIN_ROLE) && (
            <Link
              to={getAdminPath()}
              exact
              className="remove-link-decoration d-flex"
            >
              <div className="mr-2 header-item">
                <SupervisedUserCircleOutlinedIcon />
                Admin
              </div>
            </Link>
          )}
          {user && (
            <Link
              to={getProfilePath()}
              className="remove-link-decoration d-flex"
            >
              <div className=" mr-2 header-item">
                <PersonIcon />
                {`${user.firstName} ${user.lastName}`}
              </div>
            </Link>
          )}
          {user ? (
            <Link
              to="#"
              className="remove-link-decoration d-flex"
              onClick={() => {
                dispatch(logoutUser());
                history.push(getHomePath());
              }}
            >
              <div className=" header-item">
                <ExitToAppIcon />
                Log Out
              </div>
            </Link>
          ) : (
            <Link to={getLoginPath()} className="remove-link-decoration d-flex">
              <div className=" header-item">
                <ExitToAppIcon />
                Log In
              </div>
            </Link>
          )}
        </div>
      </div>
      <Divider />
    </header>
  );
}

export default Header;
