import { Routes } from '@angular/router';
import { RegisterComponent } from './features/auth/register/component/register.component';
import { LoginComponent } from './features/auth/login/component/login.component';
import { AboutComponent } from './features/about/component/about.component';
import { ContactComponent } from './features/contact/component/contact/contact.component';
import { ContentComponent } from './features/content/content/content.component';

export const routes: Routes = [
    {path: '', redirectTo: '', pathMatch:'full'},
    {path: 'register', component: RegisterComponent},
    {path: 'login', component: ContentComponent},
    {path: 'about', component: AboutComponent},
    {path: 'contact', component: ContactComponent}
];
