import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { Message } from '../model/Message';

@Injectable({
  providedIn: 'root'
})
export class httpMessagesService {

  private messageSource = new BehaviorSubject<Message>({status:0, message:""});

  // Observable string streams
  currentMessage = this.messageSource.asObservable();

  constructor() { }

  changeMessage(message: any) {
    this.messageSource.next(message);
  }

  clear(){
    this.messageSource.next(
      {status:0, message: ""}
    );
  }


}
