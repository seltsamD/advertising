import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {UserListComponent} from "./user-list/user-list.component";
import {AddUserComponent} from "./add-user/add-user.component";
import {EditUserComponent} from "./edit-user/edit-user.component";
import {UserAppListComponent} from "./user-app-list/user-app-list.component";
import {UserAppAddComponent} from "./user-app-add/user-app-add.component";
import {UserAppEditComponent} from "./user-app-edit/user-app-edit.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'user-list', component: UserListComponent},
  {path: 'add-user', component: AddUserComponent},
  {path: 'edit-user/:id', component: EditUserComponent},
  {path: 'app-list', component: UserAppListComponent},
  {path: 'app-add', component: UserAppAddComponent},
  {path: 'app-edit', component: UserAppEditComponent}
];

export const routing = RouterModule.forRoot(routes);

