import React from "react";
import PersonIcon from "@material-ui/icons/Person";
import ExitToAppIcon from "@material-ui/icons/ExitToApp";
import LocalShippingIcon from "@material-ui/icons/LocalShipping";
import LockOpenIcon from "@material-ui/icons/LockOpen";
import PersonAddIcon from "@material-ui/icons/PersonAdd";
import { Divider } from "@material-ui/core";
import "../styles/Header.css";
import { Link } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { logoutUser } from "../store/UserAuthentication/user-authentication-actions";

function Header() {
  const user = useSelector((state) => state.userAuthentication.user);
  const dispatch = useDispatch();
  return (
    <header>
      <div className="header-inner">
        <Link to="/" className="remove-link-decoration">
          <h2>Logo</h2>
        </Link>
        <div className="d-flex ">
          <Link to="/order" className="remove-link-decoration d-flex">
            <div className="header-item mr-2">
              <LocalShippingIcon />
              Order
            </div>
          </Link>
          {user && (
            <Link to="/profile" className="remove-link-decoration d-flex">
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
              onClick={() => dispatch(logoutUser())}
            >
              <div className=" header-item">
                <ExitToAppIcon />
                Log Out
              </div>
            </Link>
          ) : (
            <>
              <Link to="/login" className="remove-link-decoration d-flex">
                <div className=" header-item mr-2">
                  <LockOpenIcon />
                  Log In
                </div>
              </Link>
              <Link to="/register" className="remove-link-decoration d-flex">
                <div className=" header-item">
                  <PersonAddIcon />
                  Sign In
                </div>
              </Link>
            </>
          )}
        </div>
      </div>
      <Divider />
    </header>
  );
}

export default Header;
