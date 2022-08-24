import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddBlogComponent } from './MyComponent/add-blog/add-blog.component';
import { LoginComponent } from './MyComponent/auth/login/login.component';
import { RegisterSuccessComponent } from './MyComponent/auth/register-success/register-success.component';
import { RegisterComponent } from './MyComponent/auth/register/register.component';
import { ResetPasswordComponent } from './MyComponent/auth/reset-password/reset-password.component';
import { BlogComponent } from './MyComponent/blog/blog.component';
import { HomeComponent } from './MyComponent/home/home.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'register-success', component: RegisterSuccessComponent},
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {path: 'add-blog', component: AddBlogComponent},
  {path: 'reset-password', component: ResetPasswordComponent},
  {path: 'blog/:title',component:BlogComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
