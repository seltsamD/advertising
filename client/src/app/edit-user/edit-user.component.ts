import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {UserApiService} from "../service/user.service";
import {AppComponent} from "../app.component";

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  editForm: FormGroup;

  constructor(private route: ActivatedRoute, private formBuilder: FormBuilder, private router: Router, private apiService: UserApiService,
              private app: AppComponent) {
  }

  ngOnInit() {
    this.editForm = this.formBuilder.group({
      id: [],
      name: [],
      email: [],
      password: [],
      userRole: []
    });

    let id = this.route.snapshot.paramMap.get('id');
    this.apiService.getUser(id)
      .subscribe(data => {
        this.editForm.setValue(data);
      });
  }

  onSubmit() {
    let self = this;
    this.apiService.updateUser(this.editForm.value)
      .subscribe({
        next(data) {
          alert('User updated successfully.');
          self.router.navigate(['user-list']);
        },
        error(error) {
          self.app.errorHadling(error);
        }
      });
  }
}
