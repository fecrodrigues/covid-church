import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoggedTemplateComponent } from './logged-template.component';

describe('LoggedTemplateComponent', () => {
  let component: LoggedTemplateComponent;
  let fixture: ComponentFixture<LoggedTemplateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoggedTemplateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoggedTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
