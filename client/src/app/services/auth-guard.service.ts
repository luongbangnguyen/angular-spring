import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs/Observable";
import {AuthenticationService} from "./authenticaiton.service";
import {Router} from "@angular/router";

@Injectable()
export class AuthGuardService implements CanActivate{

  constructor(private authenticationService: AuthenticationService,
              private router: Router) { }


  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
      return this.authenticationService.checkUserLogin().map(
      () => {
        let user = this.authenticationService.getUserLogin();
        if(!user){
          this.router.navigate(['login']).then();
        }
        return !!user;
      }).catch((error: any) => this.router.navigate(['login']).then());
  }
}
