import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { inject } from '@angular/core';

export const loginGuardGuard: CanActivateFn = (route, state) => {
  
  const authService: AuthService = inject(AuthService);
  const router : Router = inject(Router);

// Verifica si el usuario está autenticado
  if (!authService.isAuthenticated()){
    return true; // Permite el acceso si el usuario esta autenticado
  } else {
    router.navigateByUrl("/about");
    return false; // Niega acceso si no esta autenticado
  }
};
