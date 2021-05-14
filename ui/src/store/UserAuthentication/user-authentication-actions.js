export const UPDATE_USER = "UPDATE_USER";
export const UPDATE_USER_ROLES = "UPDATE_USER_ROLES";

export function updateUser(user) {
  return {
    type: UPDATE_USER,
    payload: user,
  };
}

export function updateUserRoles(roles) {
  return {
    type: UPDATE_USER_ROLES,
    payload: roles,
  }
}

export function logoutUser() {
  return (dispatch) => {
    localStorage.removeItem("token");
    dispatch(updateUser(null));
    dispatch(updateUserRoles([]));
  };
}

export function setAuthToken(token) {
  return (dispatch) => {
    localStorage.setItem("token", token);
    let jwtData = token.split('.')[1]
    const decodedJwtData = JSON.parse(window.atob(jwtData));
    dispatch(updateUserRoles(decodedJwtData.roles));
  }
}
