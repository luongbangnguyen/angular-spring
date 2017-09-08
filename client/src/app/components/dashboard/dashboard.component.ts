import { Component, OnInit } from '@angular/core';
import {User} from "../../model/user";
import {AuthenticationService} from "../../services/authenticaiton.service";
import {Router} from '@angular/router';

@Component({
  selector: 'dashboard-component',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],

})
export class DashboardComponent implements OnInit {

  userLogin:User = null;

  constructor(private authenticationService: AuthenticationService,
              private router: Router) { }

  ngOnInit() {
    this.getUserLogin();
  }

  logout() {
    this.authenticationService.logout().subscribe(
      () => {
        this.router.navigate(['login']).then();
      },
      () => {
        this.router.navigate(['login']).then();
      }
    );
  }

  private getUserLogin() {
    this.userLogin = this.authenticationService.getUserLogin();
  }

}
