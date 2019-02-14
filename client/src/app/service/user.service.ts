import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from "../model/user.model";
import {Observable} from "rxjs";
import {Constants} from "../constants";

@Injectable()
export class UserApiService {

  constructor(private http: HttpClient) {
  }

  login(loginPayload) {
    const headers = {
      'Authorization': 'Basic ' + btoa('dashboard-client:dashboard-server'),
      'Content-type': 'application/x-www-form-urlencoded'
    }
    return this.http.post(Constants.baseURL + 'oauth/token', loginPayload, {headers});
  }

  getUsers() {
    return this.http.get(Constants.userApiURL + 'all' + this.tokenPart());
  }

  getCurrentUser(): Observable<User> {
    return this.http.get<User>(Constants.userApiURL + this.tokenPart());
  }

  getUser(id: string): Observable<User> {
    return this.http.get<User>(Constants.userApiURL + 'id/' + id + this.tokenPart());
  }


  addUser(user: User) {
    return this.http.post(Constants.userApiURL + this.tokenPart(), user);
  }

  updateUser(user: User) {
    return this.http.put(Constants.userApiURL + user.id + this.tokenPart(), user);
  }

  deleteUser(id: number) {
    return this.http.delete(Constants.userApiURL + id + this.tokenPart());
  }

  tokenPart(): string {
    return '?access_token=' + JSON.parse(window.sessionStorage.getItem('token')).access_token;
  }
}

