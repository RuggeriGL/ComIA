import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { httpMessagesService } from '../../services/http-messages.service';
import { Message } from '../../model/Message';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  httpMessage: Message | undefined;

  constructor(private fb: FormBuilder, private authService: AuthService, private httpMessagesService: httpMessagesService) {
    this.registerForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: [''],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
    }, { validators: this.passwordMatchValidator.bind(this) });
  }

  ngOnInit() {
    this.httpMessagesService.currentMessage.subscribe(message => {
      this.httpMessage = message;
      if(message.status===201){
        this.registerForm.reset();
      }
    });
  }

  passwordMatchValidator(form: FormGroup): ValidationErrors | null {
    const password = form.get('password')?.value;
    const confirmPassword = form.get('confirmPassword')?.value;
    if (password !== confirmPassword) {
      form.get('confirmPassword')?.setErrors({ mismatch: true });
      return { mismatch: true };
    }
    return null;
  }

  onSubmitRegister(): void {
    if (this.registerForm.valid) {
      this.authService.onRegister(this.registerForm.value);
    } 
  }
}
