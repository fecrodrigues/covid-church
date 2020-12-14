import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MinstituicaoComponent } from './minstituicao.component';

describe('MinstituicaoComponent', () => {
  let component: MinstituicaoComponent;
  let fixture: ComponentFixture<MinstituicaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MinstituicaoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MinstituicaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
