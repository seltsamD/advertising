import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {UserAppService} from "../service/user-app.service";
import {UserApiService} from "../service/user.service";
import {AppComponent} from "../app.component";
import {Constants} from "../constants";

@Component({
  selector: 'app-user-app-edit',
  templateUrl: './user-app-edit.component.html',
  styleUrls: ['./user-app-edit.component.css']
})
export class UserAppEditComponent implements OnInit {
  editForm: FormGroup;
  appTypes: any;
  possibleContentTypes: any;
  users: any;
  app: any;

  constructor(private formBuilder: FormBuilder, private router: Router, private route: ActivatedRoute, private apiService: UserAppService,
              private userApiService: UserApiService, private appComponent: AppComponent) {

    this.appTypes = Constants.APP_TYPES;
    this.possibleContentTypes = Constants.CONTENT_TYPES;
    this.app = appComponent;
  }

  ngOnInit() {
    this.editForm = this.formBuilder.group({
      id: [],
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

    let id = this.route.snapshot.paramMap.get('id');
    this.apiService.get(id)
      .subscribe(data => {
        data['user'] = data['user'].email;
        this.editForm.setValue(data);
      });
  }

  onSubmit() {
    let value = this.editForm.value;
    let self = this;
    let userMail;
    if (this.appComponent.isPublisher()) {
      userMail = this.appComponent.username();
    } else {
      userMail = value.user;
    }
    value.user = this.users.find(u => u.email === userMail);
    this.apiService.edit(value)
      .subscribe(
        data => {
          alert('App updated successfully.');
          self.router.navigate(['app-list']);
        },
        error => {
          self.appComponent.errorHadling(error);
        });
  }
}
