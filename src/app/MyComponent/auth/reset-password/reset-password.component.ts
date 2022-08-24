import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import { Reset } from './change-password';
import {AuthService} from '../auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  registerForm: FormGroup;
  resetPassword: Reset;

  constructor(private formBuilder: FormBuilder, private authService: AuthService,private router:Router) { 
    this.registerForm = this.formBuilder.group({
      username: '',
      password: '',
    });
    this.resetPassword = {
      username: '',
      password: ''
    }
  }

  ngOnInit(): void {
  }

  onSubmit(){
    this.resetPassword.username=this.registerForm.get('username')?.value;
    this.resetPassword.password=this.registerForm.get('password')?.value;

    this.authService.resetPassword12(this.resetPassword).subscribe(data => {
      console.log('register succes');
      this.router.navigateByUrl('/register-success');
    }, error => {
      console.log('register failed');
    });
  }

}
