import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class AxiosService {
  constructor() {
    axios.defaults.baseURL = "http://localhost:9998"
    axios.defaults.headers.post["Content-Type"]= "application/json";
  }

  request(method: string, url: string, data: any): Promise<any>{
    let headers = {}

    if (this.getAuthToken() !== null){
      headers = {"Authorization": "Bearer" + this.getAuthToken()};
    }

    return axios({
      method: method,
      url: url,
      data: data
    });
  }

  getAuthToken(): string | null {
    return window.localStorage.getItem("auth-token");
  }

  setAuthToken(authToken: string) {
    if(authToken !== null){
      window.localStorage.setItem('auth-token', authToken);
    } else {
      window.localStorage.removeItem('auth-token');
    }
  }

}
