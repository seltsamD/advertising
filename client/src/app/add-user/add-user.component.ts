import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {UserApiService} from "../service/user.service";
import {AppComponent} from "../app.component";

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  addForm: FormGroup;
  app: AppComponent;
  possibleRoles: any;

  constructor(private formBuilder: FormBuilder, private router: Router, private apiService: UserApiService, private appComponent: AppComponent) {
    this.app = appComponent;
  }

  ngOnInit() {
    if (this.app.isAdmin()) {
     this.possibleRoles = this.appComponent.userRoles;
    } else {
      this.possibleRoles =  [{name: "Publisher", value: this.appComponent.rolePublisher}];
    }
    this.addForm = this.formBuilder.group({
      id: [],
      name: ['', Validators.required],
      email: ['', Validators.compose([Validators.required, Validators.email])],
      password: ['', Validators.required],
      userRole: ['', Validators.required]
    });
  }

  onSubmit() {
    this.apiService.addUser(this.addForm.value)
      .subscribe(data => {
        this.router.navigate(['user-list']);
      });
  }

}
