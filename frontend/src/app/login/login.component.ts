import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username = '';
  password = '';

  login() {
    // TODO: replace with real authentication logic
    console.log(`Logging in with ${this.username}/${this.password}`);
  }
}
