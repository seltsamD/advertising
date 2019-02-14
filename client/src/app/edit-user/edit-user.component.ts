import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {UserApiService} from "../service/user.service";
import {User} from "../model/user.model";

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  editForm: FormGroup;
  user: User;

  constructor(private route: ActivatedRoute, private formBuilder: FormBuilder, private router: Router, private apiService: UserApiService) {
  }

  ngOnInit() {
    this.editForm = this.formBuilder.group({
      id: [],
      name: ['', Validators.required],
      email: ['', Validators.compose([Validators.required, Validators.email])],
      password: ['', Validators.required],
      userRole: ['', Validators.required]
    });
    let id = this.route.snapshot.paramMap.get('id');
    this.apiService.getUser(id)
      .subscribe(data => {
        this.editForm.setValue(data);
      });
  }

  onSubmit() {
    this.apiService.updateUser(this.editForm.value)
      .subscribe(
        data => {
          alert('User updated successfully.');
          this.router.navigate(['user-list']);
        },
        error => {
          alert(error);
        });
  }

}
