import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-app-list',
  templateUrl: './user-app-list.component.html',
  styleUrls: ['./user-app-list.component.css']
})
export class UserAppListComponent implements OnInit {

  constructor(private router: Router, ) { }

  ngOnInit() {
  }

}
