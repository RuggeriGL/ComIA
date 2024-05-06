import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './pages/register/register.component';
import { LoginComponent } from './pages/login/login.component';
import { AboutComponent } from './pages/about/about.component';
import { ContactComponent } from './pages/contact/contact.component';
import { ForbiddenErrorComponent } from './components/errors/forbidden-error/forbidden-error.component';
import { WelcomeComponent } from './pages/welcome/welcome.component';
import { authGuardGuard } from './guards/auth-guard.guard';
import { loginGuardGuard } from './guards/login-guard.guard';
import { RecipesComponent } from './pages/recipes/recipes.component';

const routes: Routes = [
  
  {path: '', redirectTo: '', pathMatch:'full'},
  {path: 'register', component: RegisterComponent, canActivate: [loginGuardGuard]},
  {path: 'login', component: LoginComponent, canActivate: [loginGuardGuard]},
  {path: 'welcome', component:WelcomeComponent, canActivate: [authGuardGuard]},
  {path: 'recipes', component:RecipesComponent},
  {path: 'about', component: AboutComponent},
  {path: 'contact', component: ContactComponent},
  {path: '403', component: ForbiddenErrorComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
