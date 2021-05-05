import axiosInstance from "../../api/axiosInstance"
import { LOGIN } from "../../config"

export const UPDATE_USER = "UPDATE_USER"
export const TOGGLE_AUTHENTICATION = "TOGGLE_AUTHENTICATION"

export function updateUser(user){
    return {
        type: UPDATE_USER,
        payload: user,
    }
}

export function logoutUser(){
    return dispatch => {
        localStorage.removeItem('token')
        dispatch(updateUser(null))
    }
}
