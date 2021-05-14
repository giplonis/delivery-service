import React from "react";
import Form from "./components/Form";
import { createMuiTheme } from "@material-ui/core/styles";
import { ThemeProvider } from "@material-ui/core/styles";
import { SnackbarProvider } from "notistack";
import { Router, Switch, Route } from "react-router-dom";
import Profile from "./components/Profile";
import Login from "./components/Login";
import Register from "./components/Register";
import history from "./history";
import DataLoader from "./components/DataLoader";
import LandingPage from "./components/LandingPage";
import { UserRoute } from "./components/routes/UserRoute";
import Header from "./components/Header";
import Footer from "./components/Footer";
import { Container } from "@material-ui/core";
import {
  getAdminPath,
  getHomePath,
  getLoginPath,
  getProfilePath,
  getRegisterPath,
  getOrderPath,
} from "./services/navigation/paths";
import { AdminRoute } from "./components/routes/AdminRoute";

function App() {
  const theme = createMuiTheme({
    palette: {
      type: "dark",
      primary: {
        main: "#cee002",
      },
      secondary: {
        main: "#246A73",
      },
      background: {
        default: "#272727",
        paper: "#272727",
      },
      divider: "#cee002",
    },
    shape: {
      borderRadius: 0,
    },
  });

  return (
    <div>
      <ThemeProvider theme={theme}>
        <SnackbarProvider>
          <Router history={history}>
            <DataLoader>
              <Container>
                <div className="content-wrapper">
                  <Header />
                  <Switch>
                    <Route exact path={getHomePath()}>
                      <LandingPage />
                    </Route>
                    <Route exact path={getOrderPath()}>
                      <Form />
                    </Route>
                    <UserRoute path={getProfilePath()}>
                      <Profile />
                    </UserRoute>
                    <Route path={getLoginPath()}>
                      <Login />
                    </Route>
                    <Route path={getRegisterPath()}>
                      <Register />
                    </Route>
                    <AdminRoute path={getAdminPath()}></AdminRoute>
                  </Switch>
                </div>
                <Footer />
              </Container>
            </DataLoader>
          </Router>
        </SnackbarProvider>
      </ThemeProvider>
    </div>
  );
}

export default App;
