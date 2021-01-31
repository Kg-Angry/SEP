import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UspesnoComponent } from './uspesno.component';

describe('UspesnoComponent', () => {
  let component: UspesnoComponent;
  let fixture: ComponentFixture<UspesnoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UspesnoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UspesnoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
