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
    if (this.appComponent.isAdops() || this.appComponent.isPublisher()) {
      this.appService.findAll()
        .subscribe(data => {
          this.apps = data;
        });
    } else {
      alert("User must have Adops or Publisher role");
      this.router.navigate(['login']);
    }
  }

  addApp(): void {
    this.router.navigate(['app-add']);
  }
}
