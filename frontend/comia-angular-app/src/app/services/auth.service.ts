import { Injectable } from '@angular/core';
import { AxiosService } from './axios.service';
import { HttpMessagesService } from './http-messages.service';
import { Message } from '../model/Message';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { Observable } from 'rxjs/internal/Observable';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private componentToShow: string = "";
  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);

  constructor( private axiosService : AxiosService , private httpMessagesService : HttpMessagesService , private router : Router ){ }

  firstName: string = "default";

  onLogin(input : any){
    this.axiosService.request(
      "POST",
      "/login",
      {
        login: input.login,
        password: input.password
      }
    ).then(response => {
      if(response.status===200){
        this.handleHttpOk({
          status:response.status,
          message:"Usuario logeado"
        })
        this.firstName=response.data.firstName;
        console.log(this.firstName);

        // Redireccionar a welcome
        this.router.navigateByUrl("/welcome")
      }
      this.axiosService.setAuthToken(response.data.token);
      this.componentToShow = "messages";
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
  isAuthenticatedObservable(): Observable<boolean | null> {
    return this.axiosService.getLoggedIn();
  }

  isAuthenticated(){
    return this.axiosService.isAuthenticated();
  }

  getFirstName():string{
    return this.firstName;
  }

}
