import { Injectable } from '@angular/core';
import { AxiosService } from './axios.service';
import { ErrorMessagesService } from './error-messages.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private componentToShow: string = "";

  constructor( private axiosService : AxiosService, private errorMessagesService : ErrorMessagesService ) { }

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
      this.axiosService.setAuthToken(response.data.token);
      this.componentToShow = "messages";
    }).catch(error => {
      if (error.response.status == "400"){
        this.handleHttpError(error);
      }
    });
  }

  private handleHttpError(error: any) {
    let errorMessage = 'Error desconocido';
    if (error.response) {
      errorMessage = error.response.data.message || 'Error en el servidor';
      console.log(error.response.data.message);
    } else if (error.request) {
      errorMessage = 'El servidor no respondió a tiempo';
    }
    this.errorMessagesService.changeMessage(errorMessage);
  }
}
