import React, { useState } from "react";
import Field from "./Field";
import { Grid, Button } from "@material-ui/core";
import { KeyboardDatePicker, MuiPickersUtilsProvider } from "@material-ui/pickers";
import "../styles/SendingInfo.css";
import "date-fns";
import DateFnsUtils from "@date-io/date-fns";

function SendingInfo() {
  const labels = ["First Name", "Last Name", "Phone Number", "City", "Address"];
  const fields = labels.map((label, key) => <Field label={label} key={key} />);

  const [selectedDate, setSelectedDate] = useState(null);

  const handleDateChange = (date) => {
    setSelectedDate(date);
  };

  return (
    <div className="form-wrapper">
      <Grid container spacing={9}>
        <Grid item xs={12} sm={6} className="form-column">
          <div className="form-inner">
            <div className="form-header">Sender Info</div>
            {fields}
            <MuiPickersUtilsProvider utils={DateFnsUtils}>
              <KeyboardDatePicker
                disableToolbar
                variant="inline"
                format="MM/dd/yyyy"
                margin="normal"
                label="Pick Up Date"
                onChange={handleDateChange}
                value={selectedDate}
                className="input-field"
              />
            </MuiPickersUtilsProvider>
          </div>
        </Grid>
        <Grid item xs={12} sm={6} className="form-column">
          <div className="form-inner">
            <div className="form-header">Recipient Info</div>
            {fields}
          </div>
        </Grid>
      </Grid>
      <Button color="primary" variant="contained" className="form-button">
        Next
      </Button>
    </div>
  );
}

export default SendingInfo;
