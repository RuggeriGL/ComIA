import { Component, OnInit } from '@angular/core';
import { AxiosService } from '../../services/axios.service';
import { Profile } from '../../model/Profile';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit{
  
  profile : Profile = {
    id: 0,
    userId: 0,
    intolerances: [],
    diets: [],
    lastName: '',
    firstName: ''
  };

  updated: boolean = false;

  constructor( private axiosService : AxiosService ){}

  ngOnInit(): void {
    this.getProfile();
  }


  intolerances = [
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

  diets = [
    { id: 'Gluten Free', name: 'Gluten Free'},
    { id: 'Ketogenic', name: 'Ketogenic'},
    { id: 'Vegetarian', name: 'Vegetarian'},
    { id: 'Lacto-Vegetarian', name: 'Lacto-Vegetarian'},
    { id: 'Ovo-Vegetarian', name: 'Ovo-Vegetarian'},
    { id: 'Vegan', name: 'Vegan'},
    { id: 'Pescetarian', name: 'Pescetarian'},
    { id: 'Paleo', name: 'Paleo'},
    { id: 'Primal', name: 'Primal'},
    { id: 'Low FODMAP', name: 'Low FODMAP'},
    { id: 'Whole30', name: 'Whole30'}
  ];

  getProfile():void{
    console.log("Getting profile")
    this.axiosService.request("GET" , "/getProfile" , null).then(response =>{
      this.profile = response.data;
      console.log(response);
    });
  }

  updateProfile(): void {
    console.log("Updating profile")
    this.axiosService.request("POST" , "/saveProfile" , this.profile).then(response =>{
      if (response.status==200){
        this.updated=true;
      }
      this.getProfile();
      console.log(response);
    });
  }
}
