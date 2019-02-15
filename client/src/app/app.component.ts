import {Component} from '@angular/core';
import {Constants} from "./constants";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Dashboard';

  roleAdmin = "ADMIN";
  roleAdops = "ADOPS";
  rolePublisher = "PUBLISHER"
  userRoles = [
    {name: "Adops", value: this.roleAdops},
    {name: "Publisher", value: this.rolePublisher}
  ];

  constructor(private cons: Constants, private router: Router) {

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

  errorHadling(error: any) {
    if (error.status === 401) {
      alert("User must be logged in");
      this.logout();
      this.router.navigate(['login']);
    } else {
      alert(error.message);
    }
  }
}
