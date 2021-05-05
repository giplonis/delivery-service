import { TOGGLE_AUTHENTICATION, UPDATE_USER } from "./actions";

const initialState = {
    isAuthenticating: false,
    user: null,
}

export function userAuthenticationReducer(state = initialState, actions){
    switch(actions.type){
        case UPDATE_USER:
            return {
                ...state,
                user: actions.payload
            }
        case TOGGLE_AUTHENTICATION:
            return {
                ...state,
                isAuthenticating: !state.isAuthenticating,
            }
        default:
            return state
    }
}
