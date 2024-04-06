import { Component } from '@angular/core';
import { HeaderService } from '../services/header.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  constructor(private service: HeaderService){
    
  }

  isLoggedIn: Boolean = false;


}
