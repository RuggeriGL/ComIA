import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service'; // Asegúrate de importar tu servicio de autenticación aquí
import { inject } from '@angular/core';

export const authGuardGuard: CanActivateFn = (route, state) => {
  
  const authService: AuthService = inject(AuthService);
  const router : Router = inject(Router);

  // Verifica si el usuario está autenticado
  if (authService.isAuthenticated()){
    return true; // Permite el acceso si el usuario esta autenticado
  } else {  
    router.navigateByUrl("/403");
    return false; // Niega acceso si no esta autenticado
  }

};
