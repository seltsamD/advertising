import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpParams} from "@angular/common/http";
import {UserApiService} from "../service/user.service";
import {User} from "../model/user.model";
import {AppComponent} from "../app.component";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  invalidLogin: boolean = false;
  user: User;

  constructor(private formBuilder: FormBuilder, private router: Router, private apiService: UserApiService, private app: AppComponent) {
  }

  onSubmit() {
    if (this.loginForm.invalid) {
      return;
    }

    const body = new HttpParams()
      .set('username', this.loginForm.controls.email.value)
      .set('password', this.loginForm.controls.password.value)
      .set('grant_type', 'password');

    this.apiService.login(body.toString()).subscribe(data => {
      window.sessionStorage.setItem('token', JSON.stringify(data));
      this.setUserInfo();
    }, error => {
      this.invalidLogin = true;
    });
  }

  setUserInfo() {
    this.apiService.getCurrentUser().toPromise().then((data) => {
      this.app.setUsername(data.email);
      this.app.setUserRole(data.userRole);
      this.router.navigate(['user']);
    }).catch(error => {
      console.error("Error: ", error);
    })
  }

  ngOnInit() {
    //
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.compose([Validators.required])],
      password: ['', Validators.required]
    });
    window.sessionStorage.removeItem('token');
  }

}
