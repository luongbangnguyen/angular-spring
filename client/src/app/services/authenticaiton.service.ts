import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {LoginForm} from "../model/login-form";
import {Api} from "../api";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {Observable} from "rxjs/Observable";

@Injectable()
export class AuthenticationService {
  constructor(private http: HttpClient) {}

  login(loginForm: LoginForm) {
    this.http.post(Api.LOGIN_API,loginForm).map((res: Response) => {
      console.log(res.json())
    }).catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }
}
