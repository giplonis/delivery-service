import { UPDATE_USER } from "./actions";

const initialState = {
    user: null
}

export function userReducer(state = initialState, actions){
    switch(actions.type){
        case UPDATE_USER:
            return {
                user: actions.payload
            }
        default:
            return state
    }
}
