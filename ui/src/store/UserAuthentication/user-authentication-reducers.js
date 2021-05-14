import { UPDATE_USER, UPDATE_USER_ROLES } from "./user-authentication-actions";

const initialState = {
  user: null,
  userRoles: [],
};

export function userAuthenticationReducer(state = initialState, actions) {
  switch (actions.type) {
    case UPDATE_USER:
      return {
        ...state,
        user: actions.payload,
      };
    case UPDATE_USER_ROLES:
      return {
        ...state,
        userRoles: actions.payload,
      }
    default:
      return state;
  }
}
