import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { AxiosService } from '../../core/header/services/axios.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor( private axiosService : AxiosService ) { }

  onLogin(input : any){
    this.axiosService.request(
      "POST",
      "/login",
      {
        login: input.login,
        password: input.password
      }
    )
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
    )
  }


}
