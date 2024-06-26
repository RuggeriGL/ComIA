import { Component, OnInit } from '@angular/core';
import { Ingredient } from '../../model/Ingredient';
import { RecipesService } from '../../services/recipes.service';

@Component({
  selector: 'app-virtual-fridge',
  templateUrl: './virtual-fridge.component.html',
  styleUrl: './virtual-fridge.component.css'
})
export class VirtualFridgeComponent implements OnInit {

  searchText: string = '';
  updated: boolean = false; 
  
  /*
  ingredients : Ingredient[] = [
    { name : "Chicken breasts" , image:"Chicken_breasts.webp"},
    { name : "Salmon" , image:"Salmon.webp"},
    { name : "Ground beef" , image:"Ground_beef.webp"},
    { name : "Shrimp" , image:"Shrimp.webp"},
    { name : "Tofu" , image:"Tofu.webp"},
    { name : "Eggs" , image:"Eggs.webp"},
    { name : "Rice" , image:"Rice.webp"},
    { name : "Pasta" , image:"Pasta.webp"},
    { name : "Quinoa" , image:"Quinoa.webp"},
    { name : "Potatoes" , image:"Potatoes.webp"},
    { name : "Sweet potatoes" , image:"Sweet_potatoes.webp"},
    { name : "Onions" , image:"Onions.webp"},
    { name : "Garlic" , image:"Garlic.webp"},
    { name : "Carrots" , image:"Carrots.webp"},
    { name : "Broccoli" , image:"Broccoli.webp"},
    { name : "Spinach" , image:"Spinach.webp"},
    { name : "Tomatoes" , image:"Tomatoes.webp"},
    { name : "Mushrooms" , image:"Mushrooms.webp"},
    { name : "Avocado" , image:"Avocado.webp"},
    { name : "Apples" , image:"Apples.webp"},
    { name : "Oranges" , image:"Oranges.webp"},
    { name : "Milk" , image:"Milk.webp"},
    { name : "Cheese" , image:"Cheese.webp"},
    { name : "Mozzarella" , image:"Mozzarella.webp"},
    { name : "Beans " , image:"Beans.webp"}
  ]*/

  ingredients : Ingredient[] = [];
  selectedIngredients: Ingredient[] = [];

  constructor( private recipesService : RecipesService ) {}

  ngOnInit(): void {
    this.getUserIngredients();
    this.getAllIngredients();
  }

  get filteredIngredients(): Ingredient[] {
    return this.ingredients.filter(ingredient =>
      ingredient.name.toLowerCase().includes(this.searchText.toLowerCase()));
  }

  toggleIngredientSelection(ingredient: Ingredient): void {
    const index = this.selectedIngredients.findIndex(selected => selected.id === ingredient.id);
    if (index >= 0) {
      this.selectedIngredients.splice(index, 1);
    } else {
      this.selectedIngredients.push(ingredient);
    }
  }

  isSelected(ingredient: Ingredient): boolean {
    return this.selectedIngredients.some(selected => selected.id === ingredient.id);
  }
  
  saveSelectedIngredients(): void {
    this.recipesService.saveIngredients(this.selectedIngredients).then(response =>{
      //mansaje
      console.log(response);
    });
  }

  getAllIngredients(){
    this.recipesService.getAllIngredients().then(response =>{
      this.ingredients = response;
      console.log(response);
    });
  }

  getUserIngredients(){
    this.recipesService.getUserIngredients().then(response =>{
      this.selectedIngredients = response;
      console.log(response);
  });

    
  }

}
