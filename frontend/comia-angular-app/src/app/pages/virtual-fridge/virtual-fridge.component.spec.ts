import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VirtualFridgeComponent } from './virtual-fridge.component';

describe('VirtualFridgeComponent', () => {
  let component: VirtualFridgeComponent;
  let fixture: ComponentFixture<VirtualFridgeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [VirtualFridgeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(VirtualFridgeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
