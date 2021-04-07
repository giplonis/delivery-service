import React from "react";
import PersonIcon from "@material-ui/icons/Person";
import ExitToAppIcon from "@material-ui/icons/ExitToApp";
import { Divider } from "@material-ui/core";
import "../styles/Header.css";
import { Link } from "react-router-dom";

function Header() {
  return (
    <header>
      <div className="header-inner">
        <Link to="/" className="remove-link-decoration">
          <h2>Logo</h2>
        </Link>
        <div className="d-flex ">
          <Link to="/profile" className="remove-link-decoration d-flex">
            <div className=" mr-2 header-item">
              <PersonIcon />
              Vardenis Pavardenis
            </div>
          </Link>
          <Link to="#" className="remove-link-decoration d-flex">
            <div className=" header-item">
              <ExitToAppIcon />
              Log Out
            </div>
          </Link>
        </div>
      </div>
      <Divider />
    </header>
  );
}

export default Header;
