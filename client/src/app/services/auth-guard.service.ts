import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {AuthenticationService} from "./authenticaiton.service";
import {Observable} from "rxjs/Observable";

@Injectable()
export class AuthGuardService implements CanActivate{

  constructor(private authenticationService: AuthenticationService,
              private router: Router) { }


  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    return this.authenticationService.checkLogin()
      .map((result) => {
        if(!result) {
          this.router.navigate(['login']).then();
          return false;
        }
        return true;
      });
  }
}
