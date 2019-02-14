import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Dashboard';
  authorized: boolean;
  username: string;
  userRole: string;

  userRoles = [
    {name: "Adops", value: "ADOPS"},
    {name: "Publisher", value: "PUBLISHER"}
  ];

  logout(): void {
    window.sessionStorage.removeItem("token");
    window.sessionStorage.removeItem("username");
    window.sessionStorage.removeItem("userRole");
  };

  ngDoCheck() {
    this.authorized = window.sessionStorage.getItem("token") != null;
    this.username = window.sessionStorage.getItem('username');
    this.userRole = window.sessionStorage.getItem('userRole');
  }
}
