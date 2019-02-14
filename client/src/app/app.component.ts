import {Component} from '@angular/core';
import {Constants} from "./constants";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  roleAdmin = "ADMIN";
  title = 'Dashboard';
  roleAdops = "ADOPS";
  rolePublisher = "PUBLISHER"
  userRoles = [
    {name: "Adops", value: this.roleAdops},
    {name: "Publisher", value: this.rolePublisher}
  ];

  constructor(private cons: Constants) {

  }

  public username(): string {
    return window.sessionStorage.getItem("username");
  }

  public setUsername(value: string) {
    window.sessionStorage.setItem("username", value);
  }

  public isAdmin(): boolean {
    return this.userRole() === this.roleAdmin;
  }

  public isAdops(): boolean {
    return this.userRole() === this.roleAdops;
  }

  public isPublisher(): boolean {
    return this.userRole() === this.rolePublisher;
  }

  public userRole(): string {
    return window.sessionStorage.getItem("userRole");
  }

  public setUserRole(value: string) {
    window.sessionStorage.setItem("userRole", value);
  }

  public isAuthorized(): boolean {
    return window.sessionStorage.getItem('token') !== null;
  }

  logout(): void {
    window.sessionStorage.removeItem("token");
    window.sessionStorage.removeItem("username");
    window.sessionStorage.removeItem("userRole");
  };

  // ngDoCheck() {
  //   this.authorized = this.isAuthorized();
  //   this.username = this.cons.username;
  //   this.userRole = this.cons.userRole;
  // }
}
