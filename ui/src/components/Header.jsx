import React from "react";
import PersonIcon from "@material-ui/icons/Person";
import ExitToAppIcon from "@material-ui/icons/ExitToApp";
import { Divider } from "@material-ui/core";
import "../styles/Header.css";

function Header() {
  return (
    <header>
      <div className="header-inner">
        <h2>Logo</h2>
        <div className="d-flex">
          <div className="header-item mr-2">
            <PersonIcon />
            Vardenis Pavardenis
          </div>
          <div className="header-item">
            <ExitToAppIcon />
            Log Out
          </div>
        </div>
      </div>
      <Divider />
    </header>
  );
}

export default Header;
