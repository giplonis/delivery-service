import axios from "axios"

const axiosInstance = axios.create({
     timeout: 5000,
     headers: {
                'Authorization': localStorage.getItem('token') ? "JWT " + localStorage.getItem('token') : null,
                'Content-Type': 'application/json',
                'accept': 'application/json'
     }
})
axiosInstance.interceptors.response.use(
    response => response,
    error => {
        if(error.response.status === 401){
            
        }
        return Promise.reject(error)
    }

)

export default axiosInstance