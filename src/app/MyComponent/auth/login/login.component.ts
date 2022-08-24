import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import { Loginload } from '../login-load';
import {AuthService} from '../auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  loginload : Loginload;

  constructor(private authService: AuthService, private router: Router) {
    this.loginForm = new FormGroup({
      username: new FormControl(),
      password: new FormControl()
    });
    this.loginload = {
      username: '',
      password: ''
    };

   }

  ngOnInit(): void {
  }

  onSubmit(){
    this.loginload.username = this.loginForm.get('username')?.value;
    this.loginload.password = this.loginForm.get('password')?.value;
    
    this.authService.login(this.loginload).subscribe(data =>{
      if(data){
        console.log('login successful');
        this.router.navigateByUrl('/home');
      } else{
        console.log('login failed');
      }
    } )

  }

}
