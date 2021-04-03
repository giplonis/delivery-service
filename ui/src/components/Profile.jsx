import React from "react";
import Header from "./Header";
import Footer from "./Footer";
import { Container } from "@material-ui/core";

function Profile() {
  return (
    <Container>
      <div className="content-wrapper">
        <Header />
        {/* Content Here */}
      </div>
      <Footer />
    </Container>
  );
}

export default Profile;
