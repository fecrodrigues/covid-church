import { ComponentFixture, TestBed } from '@angular/core/testing';

import { McultoComponent } from './mculto.component';

describe('McultoComponent', () => {
  let component: McultoComponent;
  let fixture: ComponentFixture<McultoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ McultoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(McultoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
