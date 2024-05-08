import { Component, OnInit } from '@angular/core';
import { AxiosService } from '../../services/axios.service';
import { Root } from '../../model/Root';
import { Recipe } from '../../model/Recipe';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrl: './recipes.component.css'
})
export class RecipesComponent implements OnInit {

  recipes : Recipe[] = [];


  constructor ( private axiosService : AxiosService ) {}

  ngOnInit(): void {
    //this.getRandomRecipes();
    this.getTestRecipes()
  }

  getRandomRecipes():void{
    this.axiosService.request("GET" , "/random" , null).then(response =>{
      this.recipes = response.data.recipes;
      console.log(response);
    });
  }
  
  getTestRecipes():void{
    this.axiosService.request("GET" , "/test" , null).then(response =>{
      this.recipes = response.data.recipes;
      console.log(response);
    });
  }

}
