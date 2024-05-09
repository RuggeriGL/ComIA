import { Component, OnInit } from '@angular/core';
import { AxiosService } from '../../services/axios.service';
import { Recipe } from '../../model/Recipe';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrl: './recipes.component.css'
})
export class RecipesComponent implements OnInit {

  query: string | undefined;
  recipes : Recipe[] = [];

  selectedMealTypes : string[] = [];
  selectedIntolerances : string[] = [];
  selectedDiets : string[] = [];
  selectedCruisines : string[] = [];
  sortSelected: string | undefined;
  sortDirection: string | undefined;
  classSortAsc: string | undefined;
  classSortDesc: string | undefined;


  mealTypes: any[] = [
    //{ id: '1' , name:'All'},
    { id: 'main course' , name:'main course'},
    { id: 'side dish' , name:'side dish'},
    { id: 'dessert' , name:'dessert'},
    { id: 'appetizer' , name:'appetizer'},
    { id: 'salad' , name:'salad'},
    { id: 'bread' , name:'bread'},
    { id: 'breakfast' , name:'breakfast'},
    { id: 'soup' , name:'soup'},
    { id: 'beverage' , name:'beverage'},
    { id: 'sauce' , name:'sauce'},
    { id: 'marinade' , name:'marinade'},
    { id: 'fingerfood' , name:'fingerfood'},
    { id: 'snack' , name:'snack'},
    { id: 'drink' , name:'drink'}
  ]
  intolerances: any[] = [
    //{ id: '1', name: 'None'},
    { id: 'Dairy', name: 'Dairy'},
    { id: 'Egg', name: 'Egg'},
    { id: 'Gluten', name: 'Gluten'},
    { id: 'Grain', name: 'Grain'},
    { id: 'Peanut', name: 'Peanut'},
    { id: 'Seafood', name: 'Seafood'},
    { id: 'Sesame', name: 'Sesame'},
    { id: 'Shellfish', name: 'Shellfish'},
    { id: 'Soy', name: 'Soy'},
    { id: 'Sulfite', name: 'Sulfite'},
    { id: 'Tree Nut', name: 'Tree Nut'},
    { id: 'Wheat', name: 'Wheat'}
  ];
  diets: any[]=[
    //{ id: '1', name : 'All'},
    { id: 'Gluten Free', name : 'Gluten Free'},
    { id: 'Ketogenic', name : 'Ketogenic'},
    { id: 'Vegetarian', name : 'Vegetarian'},
    { id: 'Lacto-Vegetarian', name : 'Lacto-Vegetarian'},
    { id: 'Ovo-Vegetarian', name : 'Ovo-Vegetarian'},
    { id: 'Vegan', name : 'Vegan'},
    { id: 'Pescetarian', name : 'Pescetarian'},
    { id: 'Paleo', name : 'Paleo'},
    { id: 'Primal', name : 'Primal'},
    { id: 'Low FODMAP', name : 'Low FODMAP'},
    { id: 'Whole30', name : 'Whole30'}
  ];
  cruisines: any[] = [
    //{ id: '1', name: 'All'},
    { id: 'African', name: 'African'},
    { id: 'Asian', name: 'Asian'},
    { id: 'American', name: 'American'},
    { id: 'British', name: 'British'},
    { id: 'Cajun', name: 'Cajun'},
    { id: 'Caribbean', name: 'Caribbean'},
    { id: 'Chinese', name: 'Chinese'},
    { id: 'Eastern European', name: 'Eastern European'},
    { id: 'European', name: 'European'},
    { id: 'French', name: 'French'},
    { id: 'German', name: 'German'},
    { id: 'Greek', name: 'Greek'},
    { id: 'Indian', name: 'Indian'},
    { id: 'Irish', name: 'Irish'},
    { id: 'Italian', name: 'Italian'},
    { id: 'Japanese', name: 'Japanese'},
    { id: 'Jewish', name: 'Jewish'},
    { id: 'Korean', name: 'Korean'},
    { id: 'Latin American', name: 'Latin American'},
    { id: 'Mediterranean', name: 'Mediterranean'},
    { id: 'Mexican', name: 'Mexican'},
    { id: 'Middle Eastern', name: 'Middle Eastern'},
    { id: 'Nordic', name: 'Nordic'},
    { id: 'Southern', name: 'Southern'},
    { id: 'Spanish', name: 'Spanish'},
    { id: 'Thai', name: 'Thai'},
    { id: 'Vietnamese', name: 'Vietnamese'}
  ];
  sort: any[] = [
    {id:'meta-score', name:'meta-score'},
    {id:'popularity', name:'popularity'},
    {id:'healthiness', name:'healthiness'},
    {id:'price', name:'price'},
    {id:'time', name:'time'},
    {id:'random', name:'random'},
    {id:'max-used-ingredients', name:'max-used-ingredients'},
    {id:'min-missing-ingredients', name:'min-missing-ingredients'},
    {id:'alcohol', name:'alcohol'},
    {id:'caffeine', name:'caffeine'},
    {id:'copper', name:'copper'},
    {id:'energy', name:'energy'},
    {id:'calories', name:'calories'},
    {id:'calcium', name:'calcium'},
    {id:'carbohydrates', name:'carbohydrates'},
    {id:'carbs', name:'carbs'},
    {id:'choline', name:'choline'},
    {id:'cholesterol', name:'cholesterol'},
    {id:'total-fat', name:'total-fat'},
    {id:'fluoride', name:'fluoride'},
    {id:'trans-fat', name:'trans-fat'},
    {id:'saturated-fat', name:'saturated-fat'},
    {id:'mono-unsaturated-fat', name:'mono-unsaturated-fat'},
    {id:'poly-unsaturated-fat', name:'poly-unsaturated-fat'},
    {id:'fiber', name:'fiber'},
    {id:'folate', name:'folate'},
    {id:'folic-acid', name:'folic-acid'},
    {id:'iodine', name:'iodine'},
    {id:'iron', name:'iron'},
    {id:'magnesium', name:'magnesium'},
    {id:'manganese', name:'manganese'},
    {id:'vitamin-b3', name:'vitamin-b3'},
    {id:'niacin', name:'niacin'},
    {id:'vitamin-b5', name:'vitamin-b5'},
    {id:'pantothenic-acid', name:'pantothenic-acid'},
    {id:'phosphorus', name:'phosphorus'},
    {id:'potassium', name:'potassium'},
    {id:'protein', name:'protein'},
    {id:'vitamin-b2', name:'vitamin-b2'},
    {id:'riboflavin', name:'riboflavin'},
    {id:'selenium', name:'selenium'},
    {id:'sodium', name:'sodium'},
    {id:'vitamin-b1', name:'vitamin-b1'},
    {id:'thiamin', name:'thiamin'},
    {id:'vitamin-a', name:'vitamin-a'},
    {id:'vitamin-b6', name:'vitamin-b6'},
    {id:'vitamin-b12', name:'vitamin-b12'},
    {id:'vitamin-c', name:'vitamin-c'},
    {id:'vitamin-d', name:'vitamin-d'},
    {id:'vitamin-e', name:'vitamin-e'},
    {id:'vitamin-k', name:'vitamin-k'},
    {id:'sugar', name:'sugar'},
    {id:'zinc', name:'zinc'}
  ];

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

  selectAllMealTypes() {
    this.selectedMealTypes = this.mealTypes.map(x => x.id);
  }

  unselectAllMealTypes() {
    this.selectedMealTypes = [];
  }

  selectAllIntolerances() {
    this.selectedIntolerances = this.intolerances.map(x => x.id);
  }

  unselectAllIntolerances() {
    this.selectedIntolerances = [];
  }

  selectAllDiets() {
    this.selectedDiets = this.diets.map(x => x.id);
  }

  unselectAllDiets() {
    this.selectedDiets = [];
  }

  selectAllCruisines() {
    this.selectedCruisines = this.cruisines.map(x => x.id);
  }

  unselectAllCruisines() {
    this.selectedCruisines = [];
  }



  clearFilters() {
    this.unselectAllCruisines();
    this.unselectAllMealTypes();
    this.unselectAllIntolerances();
    this.unselectAllDiets();
  }

  applyFilters() {
    throw new Error('Method not implemented.');
  }


  setSortDesc() {
    if(this.sortSelected? true : false){
      this.sortDirection = "desc";
      this.classSortDesc = "sort-pressed"
      this.classSortAsc = "";
    }
  }
  setSortAsc() {
    if(this.sortSelected? true : false){
      this.sortDirection = "asc";
      this.classSortAsc = "sort-pressed";
      this.classSortDesc = "";
    }
  }

  updateSort() {
    if(this.sortSelected? true : false){
      this.sortDirection = "desc";
      this.classSortDesc = "sort-pressed"
    } else {
      this.sortDirection = ""
      this.classSortAsc = "";
      this.classSortDesc = "";
    }
  }


}
