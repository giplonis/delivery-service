import { UPDATE_USER } from "./user-authentication-actions";

const initialState = {
  user: null,
};

export function userAuthenticationReducer(state = initialState, actions) {
  switch (actions.type) {
    case UPDATE_USER:
      return {
        ...state,
        user: actions.payload,
      };
    default:
      return state;
  }
}
