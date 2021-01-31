import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NeuspesnoComponent } from './neuspesno.component';

describe('NeuspesnoComponent', () => {
  let component: NeuspesnoComponent;
  let fixture: ComponentFixture<NeuspesnoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NeuspesnoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NeuspesnoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
