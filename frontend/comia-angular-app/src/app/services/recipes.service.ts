import { Injectable } from '@angular/core';
import { Ingredient } from '../model/Ingredient';
import { Axios } from 'axios';
import { AxiosService } from './axios.service';

@Injectable({
  providedIn: 'root'
})
export class RecipesService {

  constructor( private axiosService : AxiosService ) { }

  private ingredients : Ingredient[] = [] as Ingredient[]


  async getIngredients(): Promise<Ingredient[]> {
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
}
