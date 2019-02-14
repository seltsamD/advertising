import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAppListComponent } from './user-app-list.component';

describe('UserAppListComponent', () => {
  let component: UserAppListComponent;
  let fixture: ComponentFixture<UserAppListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserAppListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserAppListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
