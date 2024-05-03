import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { httpMessagesService } from '../../services/http-messages.service';
import { Message } from '../../model/Message';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  constructor ( private authService : AuthService , private httpMessagesService : httpMessagesService ){}

  httpMessage : Message | undefined;

  ngOnInit(): void {
    this.httpMessagesService.currentMessage.subscribe(message => {
      this.httpMessage = message;
      if (message.status===200){
        message = {
          status:0,
          message:'0'
        };
        this.login = "";
        this.password= "";
        //todo redirecciona welcome, activar logouts, etc.
      }
    });
  }

  login: string = "";
  password: string = "";

  OnSubmitLogin():void{
    //this.onSubmitLoginEvent.emit({"login": this.login, "password":this.password});
    this.authService.onLogin({"login": this.login, "password":this.password});
  }
}
