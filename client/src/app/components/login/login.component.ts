import {Component, OnInit} from "@angular/core";
import {LoginForm} from "../../model/login-form";
import {AuthenticationService} from "../../services/authenticaiton.service";
import {Router} from '@angular/router'

@Component({
  selector: 'login-component',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{
  constructor(private authenticationService: AuthenticationService, private router: Router,){
  }

  model = new LoginForm();
  loginError = false;

  resetFormLogin() {
    this.model = new LoginForm;
    this.loginError = false;
  }

  onSubmit() {
    this.authenticationService.login(this.model).subscribe(_ => {
      this.router.navigate(['']).catch();
    },
    error => {
      this.loginError = true;
    }
    );
  }
}
