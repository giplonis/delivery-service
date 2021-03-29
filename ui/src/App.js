import React from "react";
import Form from "./components/Form";
import { createMuiTheme } from "@material-ui/core/styles";
import { ThemeProvider } from "@material-ui/core/styles";

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
        paper: "#3c3c3c",
      },
      divider: "#cee002",
    },
  });

  return (
    <div>
      <ThemeProvider theme={theme}>
        <Form />
      </ThemeProvider>
    </div>
  );
}

export default App;
