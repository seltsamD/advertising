import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {UserApiService} from "../service/user.service";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: any;

  constructor(private router: Router, private apiService: UserApiService) {
  }

  ngOnInit() {
    if (window.sessionStorage.getItem('token') &&
      (window.sessionStorage.getItem("userRole") != "ADMIN" || window.sessionStorage.getItem("userRole") != "OPERATOR")) {
      this.apiService.getUsers()
        .subscribe(data => {
          this.users = data;
        });

    } else {
      this.router.navigate(['login']);
      return;
    }
  }

  addUser(): void {
    this.router.navigate(['add-user']);
  };
}
