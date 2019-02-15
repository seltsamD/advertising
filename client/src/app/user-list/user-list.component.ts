import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {UserApiService} from "../service/user.service";
import {User} from "../model/user.model";
import {AppComponent} from "../app.component";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: any;

  constructor(private router: Router, private apiService: UserApiService, private app: AppComponent) {
  }

  ngOnInit() {
    var self = this;
    if (this.app.isAdmin() || this.app.isAdops()) {
      this.apiService.getUsers()
        .subscribe({
          next(data) {
            self.users = data
          },
          error(error) {
            self.app.errorHadling(error);
          }
        })
    } else {
      alert("User must have Admin or Adops role");
      this.router.navigate(['login']);
    }
  }

  addUser(): void {
    this.router.navigate(['add-user']);
  };

  editUser(id: number): void {
    this.router.navigate(['user-edit/', id]);
  };

  deleteUser(user: User): void {
    this.apiService.deleteUser(user.id)
      .subscribe(data => {
        this.users = this.users.filter(u => u !== user);
      })
  };
}
