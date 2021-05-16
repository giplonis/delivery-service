import React from "react";
import { Icon } from "@material-ui/core";

function LandingPoint(props) {
  return (
    <div>
      <Icon component={props.icon} style={{ fontSize: 70 }} />
      <h2 style={{ marginTop: 0 }}>{props.title}</h2>
      <div style={{ textAlign: "justify", color: "var(--gray)" }}>
        {props.description}
      </div>
    </div>
  );
}

export default LandingPoint;
