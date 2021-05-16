import React from "react";
import { Grid } from "@material-ui/core";
import Team from "../images/team.jpg";

function LandingAboutUs() {
  return (
    <Grid container spacing={10}>
      <Grid item xs={6}>
        <h2>About Us</h2>
        <div style={{ textAlign: "justify", color: "var(--gray)" }}>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse
          quam orci, pulvinar ac tincidunt et, posuere at dolor. Quisque viverra
          tristique pharetra. Donec eget mattis dui, vel vestibulum nisi. Nulla
          mattis id nulla non sodales. Morbi luctus ultricies erat, non finibus
          metus. Duis malesuada velit id magna vulputate, quis cursus eros
          vehicula. Aenean semper eu risus in elementum. Cras in sapien erat.
        </div>
      </Grid>
      <Grid item xs={6}>
        <img src={Team} style={{ width: "100%", height: "auto" }} alt="team" />
      </Grid>
    </Grid>
  );
}

export default LandingAboutUs;
