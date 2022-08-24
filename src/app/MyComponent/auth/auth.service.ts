import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
//import {LocalStorageService} from 'ngx-webstorage';
import { Registerload } from "./register-load";
import {Observable} from 'rxjs';
import {Loginload} from './login-load';
import {map} from 'rxjs/operators';
import {LocalStorageService} from 'ngx-webstorage';
import {JwtResponse} from './jwt-response';
import { Reset } from './reset-password/change-password';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  loginload : Loginload ; 
  
  
  private url = 'http://localhost:8088/api/v1.0/blog/';

  constructor(private httpClient: HttpClient, private localStoraqeService: LocalStorageService) { 
   
    this.loginload = {
      username: '',
      password: ''
    };
  

  }

  resetPassword12(reset: Reset) : Observable<Reset> {
    
    return this.httpClient.put<Reset>(this.url + 'forget', reset);
    console.log(reset);
  }


  register(registerload: Registerload): Observable<any> {
    return this.httpClient.post(this.url + 'register', registerload);
  }

  login(loginload: Loginload): Observable<boolean>{
    return this.httpClient.post<JwtResponse>(this.url+ 'login', loginload).pipe(map(data => {
      this.localStoraqeService.store('authenticationToken', data.authenticationToken);
      this.localStoraqeService.store('username', data.username);

      return true;
    }));
  }

  

  isAuthenticated(): Boolean{
    return this.localStoraqeService.retrieve('username') != null;
  }

  isloggedin(): String{
    return this.localStoraqeService.retrieve('username');
  }

 
  logout() {
    this.localStoraqeService.clear('authenticationToken');
    this.localStoraqeService.clear('username');
  }

}
