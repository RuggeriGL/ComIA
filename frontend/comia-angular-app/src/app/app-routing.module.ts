import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './pages/register/register.component';
import { LoginComponent } from './pages/login/login.component';
import { AboutComponent } from './pages/about/about.component';
import { ForbiddenErrorComponent } from './components/errors/forbidden-error/forbidden-error.component';
import { WelcomeComponent } from './pages/welcome/welcome.component';
import { authGuardGuard } from './guards/auth-guard.guard';
import { loginGuardGuard } from './guards/login-guard.guard';
import { RecipesComponent } from './pages/recipes/recipes.component';
import { VirtualFridgeComponent } from './pages/virtual-fridge/virtual-fridge.component';
import { HomeComponent } from './pages/home/home.component';
import { ProfileComponent } from './components/profile/profile.component';

const routes: Routes = [
  
  {path: '', redirectTo: '/home', pathMatch:'full'},
  {path: 'register', component: RegisterComponent, canActivate: [loginGuardGuard]},
  {path: 'login', component: LoginComponent, canActivate: [loginGuardGuard]},
  {path: 'welcome', component:WelcomeComponent, canActivate: [authGuardGuard]},
  {path: 'recipes', component:RecipesComponent},
  {path: 'vfridge', component: VirtualFridgeComponent, canActivate: [authGuardGuard]},
  {path: 'profile', component: ProfileComponent, canActivate: [authGuardGuard]},
  {path: 'about', component: AboutComponent},
  {path: 'home', component: HomeComponent },
  {path: '403', component: ForbiddenErrorComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
