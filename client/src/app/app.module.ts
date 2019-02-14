import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {UserApiService} from "./service/user.service";
import {HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";
import {routing} from "./app.routing";
import {UserListComponent} from './user-list/user-list.component';
import {AddUserComponent} from './add-user/add-user.component';
import {EditUserComponent} from './edit-user/edit-user.component';
import {Constants} from "./constants";
import {UserAppListComponent} from './user-app-list/user-app-list.component';
import {UserAppAddComponent} from './user-app-add/user-app-add.component';
import {UserAppEditComponent} from './user-app-edit/user-app-edit.component';
import {UserAppService} from "./service/user-app.service";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserListComponent,
    AddUserComponent,
    EditUserComponent,
    UserAppListComponent,
    UserAppAddComponent,
    UserAppEditComponent
  ],
  imports: [
    BrowserModule,
    routing,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [UserApiService, Constants, UserAppService],
  bootstrap: [AppComponent]
})
export class AppModule {

}
