import { Component } from '@angular/core';
import { TestRecipesService } from '../service/test-recipes.service';

@Component({
  selector: 'app-test-recipes',
  standalone: true,
  imports: [],
  templateUrl: './test-recipes.component.html',
  styleUrl: './test-recipes.component.css'
})
export class TestRecipesComponent {

  recipeData: any;
  selectedRecipe: any = null; // Agregado para almacenar la receta seleccionada

  constructor(private testRecipesService: TestRecipesService) { }

  ngOnInit(): void {
    this.fetchRecipes();
  }

  fetchRecipes(): void {
    this.testRecipesService.getRecipes().subscribe(data => {
      this.recipeData = data; // Si tu backend ya devuelve un objeto JSON, no necesitas JSON.parse
    });
  }

  onSelectRecipe(recipe: any): void {
    this.selectedRecipe = recipe;
  }
  
}



