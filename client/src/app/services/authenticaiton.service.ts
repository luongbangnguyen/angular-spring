import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {LoginForm} from "../model/login-form";
import {Api} from "../api";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {Observable} from "rxjs/Observable";
import 'rxjs/add/observable/throw';
import {User} from "../model/user";
import {AppConstants} from "../app-constants"

@Injectable()
export class AuthenticationService {
  constructor(private http: HttpClient) {}

  login(loginForm: LoginForm): Observable<User>{
    return this.http.post(Api.LOGIN_API,loginForm).map((res: Response) => {
      let user = new User();
      user.username = res["username"];
      user.role = res["authorities"][0]["authority"];
      localStorage.setItem(AppConstants.USER_INFORMATION, JSON.stringify(user));
      return user;
    }).catch((error: any) => Observable.throw(error));
  }

  getUserLogin(): User {
    return JSON.parse(localStorage.getItem(AppConstants.USER_INFORMATION));
  }
}
