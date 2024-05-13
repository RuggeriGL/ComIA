import { Injectable } from '@angular/core';
import { AxiosService } from './axios.service';
import { Profile } from '../model/Profile';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private profile: Profile = {} as Profile;

  constructor(private axiosService: AxiosService) { }

  async getProfile(): Promise<Profile> {
    console.log("Getting profile");
    try {
      const response = await this.axiosService.request("GET", "/getProfile");
      this.profile = response.data;
      return this.profile;
    } catch (error) {
      console.error('Failed to fetch profile:', error);
      throw error;
    }
  }
}
