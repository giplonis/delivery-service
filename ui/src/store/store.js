import { userReducer } from "./UserAuthentication/reducer";
import thunk from "redux-thunk";
import { combineReducers, createStore, applyMiddleware } from "redux";

const rootReducer = combineReducers({
    user: userReducer
})

const store = createStore(rootReducer, applyMiddleware(thunk))

export default store
