import { Injectable } from '@angular/core';
import { AxiosService } from './axios.service';
import { HttpMessagesService } from './http-messages.service';
import { Message } from '../model/Message';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private componentToShow: string = "";
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);

  constructor( private axiosService : AxiosService , private httpMessagesService : HttpMessagesService ){ }



  onLogin(input : any){
    this.axiosService.request(
      "POST",
      "/login",
      {
        login: input.login,
        password: input.password
      }
    ).then(response => {
      this.axiosService.setAuthToken(response.data.token);
      this.componentToShow = "messages";
      if(response.status===200){
        this.handleHttpOk({
          status:response.status,
          message:"Usuario logeado"
        })
      }
    });
  }

  onRegister(input : any){
    this.axiosService.request(
      "POST",
      "/register",
      {
        firstName: input.firstName,
        lastName: input.lastName,
        email: input.email,
        password: input.password
      }
    ).then(response => {
      if (response.status === 201){
        this.handleHttpOk({
          status: response.status,
          message: "Usuario registrado correctamente"
        });
      }
      this.axiosService.setAuthToken(response.data.token);
      this.componentToShow = "messages";
    });
  }


  private handleHttpOk(message: Message){
    this.httpMessagesService.clear();
    this.httpMessagesService.changeMessage(message);
  }

  logout(){
    this.axiosService.logout();
  }

  // Método para obtener un Observable del estado de autenticación
  isAuthenticated(): Observable<boolean | null> {
    return this.axiosService.getLoggedIn();
  }

}
