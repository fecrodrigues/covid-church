import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCultoModalComponent } from './edit-culto-modal.component';

describe('EditCultoModalComponent', () => {
  let component: EditCultoModalComponent;
  let fixture: ComponentFixture<EditCultoModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditCultoModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditCultoModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
