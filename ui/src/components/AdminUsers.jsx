import React, { useEffect, useState } from "react";
import { List, ListItem, Grid, Divider, Typography } from "@material-ui/core";
import { ADMIN_USERS } from "../api/config";
import useMessage from "../hooks/messages";
import axiosInstance from "../api/axiosInstance";
import OrderInfo from "./OrderInfo";
import AdminUserModal from "./AdminUserModal";
import { getDateString } from "../services/dateFormat";

function AdminUsers(props) {
  const [users, setUsers] = useState([]);
  const { displayError } = useMessage();

  useEffect(() => {
    (async function fetchData() {
      try {
        const response = await axiosInstance.get(ADMIN_USERS);
        setUsers(response.data);
      } catch (e) {
        displayError("Failed to load orders.");
      }
    })();
  }, [displayError]);

  const [selectedUser, setSelectedUser] = useState(null);
  const selectUser = (user) => {
    setSelectedUser((prevState) => {
      if (prevState === null) return user;
      return null;
    });
  };

  return (
    <div className="form-wrapper">
      <div className="form-inner">
        <div className="form-header">Users</div>
        <List className="admin-list">
          {users.length > 0 ? (
            users.map((user, index) => (
              <div key={index}>
                <ListItem button onClick={() => selectUser(user)}>
                  <Grid container>
                    <Grid item xs={3}>
                      <OrderInfo
                        title="Name"
                        description={`${user.firstName} ${user.lastName}`}
                      />
                    </Grid>

                    <Grid item xs={3}>
                      <OrderInfo
                        title="Orders"
                        description={user.totalOrders}
                      />
                    </Grid>
                    <Grid item xs={3}>
                      <OrderInfo
                        title="Last Order"
                        description={
                          !!user.lastOrderDate
                            ? getDateString(user.lastOrderDate)
                            : "No orders made"
                        }
                      />
                    </Grid>
                    <Grid item xs={3}>
                      <OrderInfo
                        title="Last Login"
                        description={getDateString(user.lastLoginDate)}
                      />
                    </Grid>
                  </Grid>
                </ListItem>
                {index + 1 < users.length && <Divider />}
              </div>
            ))
          ) : (
            <Typography align="center">No users to show.</Typography>
          )}
        </List>
        {selectedUser && (
          <AdminUserModal
            user={selectedUser}
            open={selectedUser !== null}
            onClose={selectUser}
            handleStatusChange={props.handleStatusChange}
          />
        )}
      </div>
    </div>
  );
}

export default AdminUsers;
