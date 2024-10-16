import axios from 'axios'

const ACCOUNT_API_URL = 'http://localhost:8081';
const DATA_API_URL = 'http://localhost:8080';

export const accountApi = axios.create({
  baseURL: ACCOUNT_API_URL,
  headers: {
    'Content-Type': 'application/json',},});


const api = axios.create({
   baseURL: DATA_API_URL,
   headers: {
     'Content-Type': 'application/json',},})

export const setAuthToken = (token) => {
  if (token) {
      api.defaults.headers.common['Authorization'] = 'Bearer ${token}';
  } else {
  delete api.defaults.headers.common['Authorization'];
  }
}
export default api;