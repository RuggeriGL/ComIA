import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {

  
  constructor ( private authService : AuthService ) {}
  
  isLoggedIn?: boolean | null;
  
  ngOnInit(): void {
    this.authService.isAuthenticated().subscribe(isAuthenticated => {
      // Actualizar la propiedad isAuthenticated cuando cambie el estado de autenticación
      this.isLoggedIn = isAuthenticated;
    });
  }
  
  logout(): void {
    // Cerrar sesión cuando se hace clic en "Cerrar sesión"
    this.authService.logout();
  }
  
}
