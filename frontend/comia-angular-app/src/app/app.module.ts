import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { ContactComponent } from './pages/contact/contact.component';
import { AboutComponent } from './pages/about/about.component';
import { WelcomeComponent } from './pages/welcome/welcome.component';
import { AuthContentComponent } from './components/auth-content/auth-content.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterOutlet } from '@angular/router';
import { ButtonsComponent } from './components/buttons/buttons.component';
import { ForbiddenErrorComponent } from './components/errors/forbidden-error/forbidden-error.component';
import { RecipesComponent } from './pages/recipes/recipes.component';
import { NgSelectComponent, NgSelectModule } from '@ng-select/ng-select';
import { VirtualFridgeComponent } from './pages/virtual-fridge/virtual-fridge.component';
import { HomeComponent } from './pages/home/home.component';
import { ProfileComponent } from './components/profile/profile.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    ContactComponent,
    AboutComponent,
    WelcomeComponent,
    AuthContentComponent,
    LoginComponent,
    RegisterComponent,
    ButtonsComponent,
    ForbiddenErrorComponent,
    RecipesComponent,
    VirtualFridgeComponent,
    HomeComponent,
    ProfileComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterOutlet,
    NgSelectModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
