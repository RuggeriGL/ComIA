import { TestBed } from '@angular/core/testing';

import { TestRecipesService } from './test-recipes.service';

describe('TestRecipesService', () => {
  let service: TestRecipesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TestRecipesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
