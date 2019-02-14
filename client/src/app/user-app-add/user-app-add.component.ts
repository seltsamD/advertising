import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
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

  constructor(private formBuilder: FormBuilder, private router: Router, private apiService: UserAppService,
              private userApiService: UserApiService, private appComponent: AppComponent) {
    this.appTypes = Constants.APP_TYPES;
    this.possibleContentTypes = Constants.CONTENT_TYPES;
  }

  ngOnInit() {
    this.addForm = this.formBuilder.group({
      name: new FormControl([], Validators.required),
      type: new FormControl([], Validators.required),
      user: new FormControl([], Validators.required),
      contentType: new FormControl([], Validators.required)
    });

    if (this.appComponent.isAdops()) {
      this.userApiService.getUsers()
        .subscribe(data => {
          this.users = data;
        });
    } else {
      this.userApiService.getCurrentUser()
        .subscribe(data => {
          this.users = data;
        });
    }
  }

  onSubmit() {
    if (this.addForm.invalid) {
      console.log(this.addForm.errors);
    }
    var value = this.addForm.value;

    value.user = this.users.find(u => u.email === value.user);
    this.apiService.add(value)
      .subscribe(data => {
        this.router.navigate(['app-list']);
      });
  }
}
