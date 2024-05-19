import { Injectable } from '@angular/core';
import { Ingredient } from '../model/Ingredient';
import { AxiosService } from './axios.service';
import { Recipe } from '../model/Recipe';

@Injectable({
  providedIn: 'root'
})
export class RecipesService {
  
  

  constructor( private axiosService : AxiosService ) { }

  private ingredients : Ingredient[] = [] as Ingredient[]
  private recipe : Recipe = {} as Recipe;
  private favRecipes : Recipe[] = [] as Recipe[];


  async getUserIngredients(): Promise<Ingredient[]> {
    console.log("Getting ingredients");
    try {
      const response = await this.axiosService.request("GET", "/getIngredientsByUser");
      this.ingredients = response.data;
      return this.ingredients;
    } catch (error) {
      console.error('Failed to fetch ingredients:', error);
      throw error;
    }
  }

  async getAllIngredients(): Promise<Ingredient[]> {
    console.log("Getting ingredients");
    try {
      const response = await this.axiosService.request("GET", "/getAllIngredients");
      this.ingredients = response.data;
      return this.ingredients;
    } catch (error) {
      console.error('Failed to fetch ingredients:', error);
      throw error;
    }
  }

  async saveIngredients( ingredients : Ingredient[] ): Promise<Ingredient[]> {
    console.log("Saving ingredients");
    console.log(ingredients);
    try {
      const response = await this.axiosService.request("POST", "/updateIngredients", ingredients);
      this.ingredients = response.data;
      return this.ingredients;
    } catch (error) {
      console.error('Failed to fetch ingredients:', error);
      throw error;
    }
  }

  async addFavorite( recipe : Recipe ): Promise<Recipe> {
    console.log("Saving favorite");
    console.log(recipe);

    let favoritos = {
      id: recipe.id,
      recipeJson: JSON.stringify(recipe)
    }

    try {
      const response = await this.axiosService.request("POST", "/addFavorite", favoritos);
      this.recipe = response.data;
      return this.recipe;
    } catch (error) {
      console.error('Failed to fetch recipe:', error);
      throw error;
    }
  }

  // Metodo asincrono que envia una receta a eliminar de recetas favoritas
  async removeFavorite( recipe : Recipe ): Promise<Recipe> {
    console.log("Saving favorite");
    console.log(recipe);

    let favoritos = {
      id: recipe.id,
      recipeJson: JSON.stringify(recipe)
    }

    try {
      const response = await this.axiosService.request("POST", "/removeFavorite", favoritos);
      this.recipe = response.data;
      return this.recipe;
    } catch (error) {
      console.error('Failed to fetch recipe:', error);
      throw error;
    }
  }

  //Metodo que obtiene las recetas favoritas del usuario
  async getMyFavorites(): Promise<Recipe[]> {
    try {
      const response = await this.axiosService.request("GET", "/getMyFavorites");
      this.favRecipes = response.data;
      console.log(response.data); 
      return this.favRecipes;
    } catch (error) {
      console.error('Failed to fetch recipe:', error);
      throw error;
    }
  }
}
