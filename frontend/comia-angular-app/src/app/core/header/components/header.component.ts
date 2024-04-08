import { Component } from '@angular/core';
import { HeaderService } from '../services/header.service';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  constructor(private service: HeaderService){
    
  }

  isLoggedIn: Boolean = false;


}
