import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAppAddComponent } from './user-app-add.component';

describe('UserAppAddComponent', () => {
  let component: UserAppAddComponent;
  let fixture: ComponentFixture<UserAppAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserAppAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserAppAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
