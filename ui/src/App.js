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
          <DataLoader>
            <Router history={history}>
              <Switch>
                <Route exact path="/">
                  <LandingPage />
                </Route>
                <Route path="/order">
                  <Form />
                </Route>
                <Route path="/profile">
                  <Profile />
                </Route>
                <Route path="/login">
                  <Login />
                </Route>
                <Route path="/register">
                  <Register />
                </Route>
              </Switch>
            </Router>
          </DataLoader>
        </SnackbarProvider>
      </ThemeProvider>
    </div>
  );
}

export default App;
