import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import { Registerload } from "../register-load";
import {AuthService} from '../auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  registerload: Registerload;

  constructor(private formBuilder: FormBuilder, private authService: AuthService, private router:Router) {
    this.registerForm = this.formBuilder.group({
      name: '',
      username: '',
      email: '',
      password: '',
      confirmPassword: ''
    });
    this.registerload = {
      name: '',
      username: '',
      email: '',
      password: '',
      confirmPassword: ''
    };
  }

  ngOnInit() {
  }

  onSubmit() {

    this.registerload.name = this.registerForm.get('name')?.value;
    this.registerload.username = this.registerForm.get('username')?.value;
    this.registerload.email = this.registerForm.get('email')?.value;
    this.registerload.password = this.registerForm.get('password')?.value;
    this.registerload.confirmPassword = this.registerForm.get('confirmPassword')?.value;
    if(this.registerload.name == ""){
      alert("Enter a valid name");
      return ;
    }
    if(this.registerload.username == ""){
      alert("Enter a valid LoginId");
      return ;
    }
    if(this.registerload.email == ""){
      alert("Enter a valid email");
      return ;
    }
    if(this.registerload.password == ""){
      alert("Enter a valid password");
      return ;
    }
    if(this.registerload.confirmPassword == ""){
      alert("Enter a valid password");
      return ;
    }
    if(this.registerload.confirmPassword != this.registerload.password){
      alert("password should match");
      return ;
    }

    this.authService.register(this.registerload).subscribe(data => {
      console.log('register succes');
      alert('registration successful')
      this.router.navigateByUrl('/login');
    }, error => {
      console.log('register failed');
    });
  }
}