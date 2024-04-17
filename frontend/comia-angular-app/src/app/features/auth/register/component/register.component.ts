import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  @Output() onSubmitRegisterEvent = new EventEmitter();

  firstName: string = "";
  lastName: string = "";
  email: string = "";
  password: string = "";
  confirmPassword: string = "";

  OnSubmitRegister(): void {
    // Aquí puedes agregar validaciones adicionales si es necesario
    if (this.password === this.confirmPassword) {
      this.onSubmitRegisterEvent.emit({
        "firstname": this.firstName,
        "lastname": this.lastName,
        "email": this.email,
        "password": this.password,
        "confirmpassword": this.confirmPassword
      });
    } else {
      // Manejar el error de contraseñas no coincidentes
      console.error("Las contraseñas no coinciden");
    }
  }
}
