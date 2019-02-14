import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from "../model/user.model";
import {Observable} from "rxjs";


@Injectable()
export class UserApiService {

  baseUrl: string = 'http://localhost:8082/api/user/';
  userGl: User;

  constructor(private http: HttpClient) {
  }

  login(loginPayload) {
    const headers = {
      'Authorization': 'Basic ' + btoa('dashboard-client:dashboard-server'),
      'Content-type': 'application/x-www-form-urlencoded'
    }
    return this.http.post('http://localhost:8082/' + 'oauth/token', loginPayload, {headers});
  }

  getUsers() {
    return this.http.get(this.baseUrl + 'all?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token);
  }

  getUser(): Observable<User> {
    return this.http.get<User>(this.baseUrl + '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token);
  }

  addUser(user: User) {
    return this.http.post(this.baseUrl + '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token, user);
  }
}

