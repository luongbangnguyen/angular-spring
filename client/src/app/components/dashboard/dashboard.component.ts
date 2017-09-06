import { Component, OnInit } from '@angular/core';
import {User} from "../../model/user";
import {AuthenticationService} from "../../services/authenticaiton.service";

@Component({
  selector: 'dashboard-component',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],

})
export class DashboardComponent implements OnInit {
  constructor(private authenticationService: AuthenticationService) { }

  userLogin:User = null;

  ngOnInit() {
    this.userLogin = this.authenticationService.getUserLogin();
  }

}
