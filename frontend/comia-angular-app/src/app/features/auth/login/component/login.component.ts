import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
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
