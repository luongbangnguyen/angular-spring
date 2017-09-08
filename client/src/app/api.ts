import { environment } from '../environments/environment';
export class Api {
  public static LOGIN_API = `${environment.apiUrl}/login`;
  public static LOGOUT_API = `${environment.apiUrl}/logout`;
  public static GET_USER_API = `${environment.apiUrl}/api/users`;
  public static CHECK_LOGIN_API = `${environment.apiUrl}/api/users/isLogin`;
}
