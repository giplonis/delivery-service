import React, { useState } from "react";
import { Dialog, Select, MenuItem } from "@material-ui/core";

function AdminUserModal(props) {
  return (
    <Dialog open={props.open} onClose={() => props.onClose(props.user)}>
      <div className="modal-wrapper">
        <div className="form-wrapper summary-subform-wrapper">
          <div className="form-inner form-inner-summary">
            <div className="form-header summary-subheader">Personal Info</div>
          </div>
        </div>
        {/* <OrderModalSummary order={props.order} /> */}
      </div>
    </Dialog>
  );
}

export default AdminUserModal;
