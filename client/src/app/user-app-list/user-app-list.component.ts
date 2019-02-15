import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AppComponent} from "../app.component";
import {UserAppService} from "../service/user-app.service";

@Component({
  selector: 'app-user-app-list',
  templateUrl: './user-app-list.component.html',
  styleUrls: ['./user-app-list.component.css']
})
export class UserAppListComponent implements OnInit {
  apps: any;

  constructor(private router: Router, private appComponent: AppComponent, private appService: UserAppService) {

  }

  ngOnInit() {
    let self = this;
    if (this.appComponent.isAdops()) {
      this.appService.findAll()
        .subscribe({
          next(data) {
            self.apps = data;
          },
          error(error) {
            self.appComponent.errorHadling(error);
          }
        });
    } else if (this.appComponent.isPublisher()) {
      this.appService.findByUser()
        .subscribe({
          next(data) {
            self.apps = data;
          },
          error(error) {
            self.appComponent.errorHadling(error);
          }
        });
    } else {
      alert("User must have Adops or Publisher role");
      this.router.navigate(['login']);
    }
  }

  addApp(): void {
    this.router.navigate(['app-add']);
  }

  edit(id: number): void {
    this.router.navigate(['app-edit/', id]);
  };

  delete(id: number): void {
    this.appService.delete(id)
      .subscribe(data => {
        this.apps = this.apps.filter(u => u.id !== id);
      })
  };
}
