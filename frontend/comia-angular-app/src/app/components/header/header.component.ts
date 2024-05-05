import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { AxiosService } from '../../services/axios.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {
  
  
  constructor ( private authService : AuthService , private axiosService : AxiosService ) {}
  
  isLoggedIn?: boolean | null;
  nombreUsuario?: string | undefined;
  classUserIcon?: string ="fa fa-user";

  
  ngOnInit(): void {
    this.authService.isAuthenticated().subscribe(isAuthenticated => {
      // Actualizar la propiedad isAuthenticated cuando cambie el estado de autenticación
      this.isLoggedIn = isAuthenticated;
      if (this.isLoggedIn && this.authService.getFirstName() != "default"){
        this.setNombreUsuario(this.authService.getFirstName());
        this.setClassLoginIcon();
      } else {
        
      }
    });
  }
  
  logout(): void {
    // Cerrar sesión   se hace clic en "Cerrar sesión"
    this.authService.logout();
  }

  setNombreUsuario(nombreUsuario : string){
    this.nombreUsuario = nombreUsuario;
  }

  setClassLoginIcon():void{
    if(this.nombreUsuario){
      let fln = this.nombreUsuario.trim().split('')[0].toLocaleLowerCase;
      this.classUserIcon="fa-solid fa-"+  this.nombreUsuario.trim().split('')[0].toLowerCase().toString();
      console.log("first letter name =" + fln );
    }
  }
  
}
