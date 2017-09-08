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
      let user = this.covertUser(res);
      localStorage.setItem(AppConstants.USER_INFORMATION, JSON.stringify(user));
      return user;
    }).catch((error: any) => Observable.throw(error));
  }

  logout(): Observable<Response>{
    return this.http.post(Api.LOGOUT_API,null)
      .map((res: Response) => {
        localStorage.removeItem(AppConstants.USER_INFORMATION);
        return res;
      })
      .catch((error: any) => Observable.throw(error));
  }

  getUserLogin(): User {
      return JSON.parse(localStorage.getItem(AppConstants.USER_INFORMATION))
  }

  checkLogin(): Observable<boolean> {
    return this.http.get(Api.CHECK_LOGIN_API)
      .map(() => {
        let user = this.getUserLogin();
        return !!user;
      })
      .catch(() => Observable.create((ob) => {
        ob.next(false);
        ob.completed()
      }));
  }

  private covertUser(res: Response): User {
    let user = new User();
    user.username = res["username"];
    user.role = res["authorities"][0]["authority"];
    return user;
  }
}
