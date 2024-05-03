import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';

@Injectable({
  providedIn: 'root'
})
export class ErrorMessagesService {

  private errorMessageSource = new BehaviorSubject<string>('');

  // Observable string streams
  currentMessage = this.errorMessageSource.asObservable();

  constructor() { }

  // Service message commands
  changeMessage(message: string) {
    this.errorMessageSource.next(message);
  }
}
