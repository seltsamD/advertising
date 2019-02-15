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
    return this.http.get(Constants.userApiURL + 'all' + Constants.tokenPart);
  }

  getAdopsAndPublisher() {
    return this.http.get(Constants.userApiURL + 'adops-publishers' + Constants.tokenPart);
  }

  getPublishers() {
    return this.http.get(Constants.userApiURL + 'publishers' + Constants.tokenPart);
  }

  getCurrentUser(): Observable<User> {
    return this.http.get<User>(Constants.userApiURL + Constants.tokenPart);
  }

  getUser(id: string): Observable<User> {
    return this.http.get<User>(Constants.userApiURL + 'id/' + id + Constants.tokenPart);
  }

  isEmailUnique(email: string): Observable<boolean> {
    return this.http.get<boolean>(Constants.userApiURL + 'email/' + email + Constants.tokenPart);
  }


  addUser(user: User) {
    return this.http.post(Constants.userApiURL + Constants.tokenPart, user);
  }

  updateUser(user: User) {
    return this.http.put(Constants.userApiURL + user.id + Constants.tokenPart, user);
  }

  deleteUser(id: number) {
    return this.http.delete(Constants.userApiURL + id + Constants.tokenPart);
  }
  
}

