import { userAuthenticationReducer } from "./UserAuthentication/reducer";
import thunk from "redux-thunk";
import { combineReducers, createStore, applyMiddleware } from "redux";

const rootReducer = combineReducers({
    userAuthentication: userAuthenticationReducer
})

const store = createStore(rootReducer, applyMiddleware(thunk))

export default store
