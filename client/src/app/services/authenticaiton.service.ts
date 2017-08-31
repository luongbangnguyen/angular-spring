import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";

@Injectable()
export class AuthenticationService {
  constructor(private http: HttpClient) {}

  login(username: string, password: string) {
    this.http.post("http://localhost:8080/login",null,{
      params: new HttpParams().set('username', username).set("password", password)
    }).subscribe(
      data => {
        console.log(data['username']);
        console.log(data['authorities']);
      },
      error => {
        console.log(error);
      });
  }
}
