import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditInstituicaoModalComponent } from './edit-instituicao-modal.component';

describe('EditCultoModalComponent', () => {
  let component: EditInstituicaoModalComponent;
  let fixture: ComponentFixture<EditInstituicaoModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditInstituicaoModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditInstituicaoModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
