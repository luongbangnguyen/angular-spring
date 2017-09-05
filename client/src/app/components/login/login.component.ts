import { Component } from "@angular/core";
import { LoginForm } from "../../model/login-form";
import { AuthenticationService } from "../../services/authenticaiton.service"
import {User} from "../../model/user";
import {AppSettings} from '../../app-settings'

@Component({
  selector: 'login-component',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{
  constructor(private authenticationService: AuthenticationService ){}

  model = new LoginForm();
  loginError = false;

  onSubmit() {
    this.authenticationService.login(this.model).subscribe((user: User) => {
      console.log(localStorage.getItem(AppSettings.USER_INFORMATION))
    },
    error => {
      this.loginError = true;
    }
    );
  }
}
