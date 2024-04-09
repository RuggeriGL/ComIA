import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './core/header/components/header.component';
import { FooterComponent } from './core/footer/component/footer.component';
import { TestRecipesComponent } from './recipes/test-recipes/component/test-recipes.component';
import { HttpClientModule } from '@angular/common/http';
import { AuthContentComponent } from './features/auth/auth-content/auth-content.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent, FooterComponent, TestRecipesComponent, HttpClientModule, AuthContentComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})



export class AppComponent {
  title = 'ComIA - Recetas inteligentes';


}


