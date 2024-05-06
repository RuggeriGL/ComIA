import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import axios from 'axios';
import { HttpMessagesService } from './http-messages.service';
import { Message } from '../model/Message';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AxiosService {

  private authTokenSubject = new BehaviorSubject<boolean | null>(null);

  constructor( private router : Router , private httpMessagesService : HttpMessagesService ) {

    axios.defaults.baseURL = "http://localhost:9998"
    axios.defaults.headers.post["Content-Type"]= "application/json";
    // Obtenemos el token si existe
    this.authTokenSubject.next(this.getAuthToken()?true:false);
  }

  

  request(method: string, url: string, data?: any): Promise<any>{
    let headers = {}

    const token = this.getAuthToken();

    if (token){
      headers = {"Authorization": `Bearer ${token}`};
    }

    return axios({
      method: method,
      url: url,
      data: data,
    }).catch(error => {
      if (error.response && error.request.status===403){
        this.router.navigateByUrl('/403');
      }
      if(error.response.status===404 || error.response.status===400){
        this.handleHttpError({
          status: error.response.status,
          message: error.response.data.message
        })
      }
    });
  }

  getAuthToken(): string | null {
    this.authTokenSubject.next(window.localStorage.getItem("auth-token")?true:false);
    return window.localStorage.getItem("auth-token");
  }


  // Método para obtener un Observable del token de autenticación
  getLoggedIn() : Observable<boolean | null> {
    return this.authTokenSubject.asObservable();
  }

  isAuthenticated(){
    return this.getAuthToken()? true:false;
  }
  

  setAuthToken(authToken: string) {
    if(authToken !== null){
      window.localStorage.setItem('auth-token', authToken);
    } else {
      window.localStorage.removeItem('auth-token');
    }
    this.authTokenSubject.next(authToken? true : false);
  }

  logout(){
    window.localStorage.removeItem('auth-token');
    this.authTokenSubject.next(this.getAuthToken()?true:false);
    this.router.navigateByUrl("/login");
  }

  private handleHttpError(message: Message) {
    this.httpMessagesService.clear();
    this.httpMessagesService.changeMessage(message);
  } 


}
