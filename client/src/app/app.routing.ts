import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {UserListComponent} from "./user-list/user-list.component";
import {AddUserComponent} from "./add-user/add-user.component";
import {EditUserComponent} from "./edit-user/edit-user.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'user-list', component: UserListComponent},
  {path: 'add-user', component: AddUserComponent},
  {path: 'edit-user/:id', component: EditUserComponent}
];

export const routing = RouterModule.forRoot(routes);

