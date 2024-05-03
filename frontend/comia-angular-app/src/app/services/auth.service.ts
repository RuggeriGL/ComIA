import { Injectable } from '@angular/core';
import { AxiosService } from './axios.service';
import { httpMessagesService } from './http-messages.service';
import { Message } from '../model/Message';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private componentToShow: string = "";

  constructor( private axiosService : AxiosService, private httpMessagesService : httpMessagesService ){ }

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
    }).catch(error=>{
      if(error.response.status===400 || error.response.status===404){
        this.handleHttpError({
          status: error.response.status,
          message: error.response.data.message
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
    }).catch(error => {
      if (error.response.status === 400){
        this.handleHttpError({
          status: error.response.status, 
          message: error.response.data.message
        });
      }
    });
  }

  private handleHttpError(message: Message) {
    this.httpMessagesService.clear();
    this.httpMessagesService.changeMessage(message);
  } 

  private handleHttpOk(message: Message){
    this.httpMessagesService.clear();
    this.httpMessagesService.changeMessage(message);
  }
}
