import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Constants} from "../constants";
import {UserApp} from "../model/user-app.model";

@Injectable({
  providedIn: 'root'
})
export class UserAppService {

  constructor(private http: HttpClient) {
  }

  findAll() {
    return this.http.get(Constants.appApiURL + 'all' + Constants.tokenPart);
  }

  findByUser() {
    return this.http.get(Constants.appApiURL + 'user' + Constants.tokenPart);
  }

  add(app: UserApp) {
    return this.http.post(Constants.appApiURL + Constants.tokenPart, app);
  }

  edit(app: UserApp) {
    return this.http.put(Constants.appApiURL + Constants.tokenPart, app);
  }

  delete(id: number) {
    return this.http.delete(Constants.appApiURL + id + Constants.tokenPart);
  }

  get(id: string) {
    return this.http.get(Constants.appApiURL + id + Constants.tokenPart);
  }


}
