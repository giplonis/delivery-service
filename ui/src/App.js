import React from "react";
import Form from "./components/Form";
import { createMuiTheme } from "@material-ui/core/styles";
import { ThemeProvider } from "@material-ui/core/styles";
import { SnackbarProvider } from "notistack";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Profile from "./components/Profile";

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
      borderRadius: 0
    }
  });

  return (
    <div>
      <ThemeProvider theme={theme}>
        <SnackbarProvider>
          <Router>
            <Switch>
              <Route exact path="/">
                <Form />
              </Route>
              <Route path="/profile">
                <Profile />
              </Route>
            </Switch>
          </Router>
        </SnackbarProvider>
      </ThemeProvider>
    </div>
  );
}

export default App;
