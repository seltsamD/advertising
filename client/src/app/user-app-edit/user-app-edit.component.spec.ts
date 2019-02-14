import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAppEditComponent } from './user-app-edit.component';

describe('UserAppEditComponent', () => {
  let component: UserAppEditComponent;
  let fixture: ComponentFixture<UserAppEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserAppEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserAppEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
