import { Component } from "@angular/core";
import { LoginForm } from "../../model/login-form";
import { AuthenticationService } from "../../services/authenticaiton.service"

@Component({
  selector: 'login-component',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{
  constructor(private authenticationService: AuthenticationService ){}

  model = new LoginForm();

  onSubmit() {
    this.authenticationService.login(this.model);
  }
}
