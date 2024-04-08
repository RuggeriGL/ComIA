import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestRecipesComponent } from './test-recipes.component';

describe('TestRecipesComponent', () => {
  let component: TestRecipesComponent;
  let fixture: ComponentFixture<TestRecipesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TestRecipesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TestRecipesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
