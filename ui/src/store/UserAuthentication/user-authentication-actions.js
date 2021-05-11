import history from "../../history";

export const UPDATE_USER = "UPDATE_USER";

export function updateUser(user) {
  return {
    type: UPDATE_USER,
    payload: user,
  };
}

export function logoutUser() {
  return (dispatch) => {
    localStorage.removeItem("token");
    dispatch(updateUser(null));
    history.push("/");
  };
}
