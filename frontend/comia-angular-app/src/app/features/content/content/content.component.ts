import { Component } from '@angular/core';
import { WelcomeComponent } from '../../welcome/welcome/welcome.component';
import { LoginComponent } from '../../auth/login/component/login.component';
import { AxiosService } from '../../../core/header/services/axios.service';

@Component({
  selector: 'app-content',
  standalone: true,
  imports: [ WelcomeComponent , LoginComponent],
  templateUrl: './content.component.html',
  styleUrl: './content.component.css'
})
export class ContentComponent {

  constructor(private axiosService : AxiosService){

  }

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
}
