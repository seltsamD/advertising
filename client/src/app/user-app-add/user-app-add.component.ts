import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AppComponent} from "../app.component";
import {Router} from "@angular/router";
import {UserApiService} from "../service/user.service";
import {UserAppService} from "../service/user-app.service";
import {Constants} from "../constants";

@Component({
  selector: 'app-user-app-add',
  templateUrl: './user-app-add.component.html',
  styleUrls: ['./user-app-add.component.css']
})
export class UserAppAddComponent implements OnInit {

  addForm: FormGroup;
  appTypes: any;
  possibleContentTypes: any;
  users: any;
  app: any;

  constructor(private formBuilder: FormBuilder, private router: Router, private apiService: UserAppService,
              private userApiService: UserApiService, private appComponent: AppComponent) {
    this.appTypes = Constants.APP_TYPES;
    this.possibleContentTypes = Constants.CONTENT_TYPES;
    this.app = appComponent;
  }

  ngOnInit() {
    this.addForm = this.formBuilder.group({
      name: [],
      type: [],
      user: [],
      contentTypes: []
    });

    let self = this;
    this.userApiService.getAdopsAndPublisher()
      .subscribe({
        next(data) {
          self.users = data;
        },
        error(error) {
          self.app.errorHadling(error);
        }
      });

  }

  onSubmit() {
    let value = this.addForm.value;
    let userMail;
    if (this.appComponent.isPublisher()) {
      userMail = this.appComponent.username();
    } else {
      userMail = value.user;
    }
    value.user = this.users.find(u => u.email === userMail);
    this.apiService.add(value)
      .subscribe(data => {
        this.router.navigate(['app-list']);
      });
  }
}
