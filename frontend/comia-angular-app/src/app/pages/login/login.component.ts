import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  constructor ( private authService : AuthService ){}

  //@Output() onSubmitLoginEvent = new EventEmitter();

  login: string = "";
  password: string = "";

  OnSubmitLogin():void{
    //this.onSubmitLoginEvent.emit({"login": this.login, "password":this.password});
    this.authService.onLogin({"login": this.login, "password":this.password});
  }
}
