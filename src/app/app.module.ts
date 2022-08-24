import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './MyComponent/auth/register/register.component';
import { RegisterSuccessComponent } from './MyComponent/auth/register-success/register-success.component';
import { LoginComponent } from './MyComponent/auth/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {NgxWebstorageModule} from 'ngx-webstorage';
import { HomeComponent } from './MyComponent/home/home.component';
import { NavbarComponent } from './MyComponent/navbar/navbar.component';
import { AddBlogComponent } from './MyComponent/add-blog/add-blog.component';
import { EditorModule } from '@tinymce/tinymce-angular';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {HttpClientInterceptor} from './http-client-interceptor';
import { ResetPasswordComponent } from './MyComponent/auth/reset-password/reset-password.component';
import { BlogComponent } from './MyComponent/blog/blog.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    RegisterSuccessComponent,
    LoginComponent,
    HomeComponent,
    NavbarComponent,
    AddBlogComponent,
    ResetPasswordComponent,
    BlogComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxWebstorageModule.forRoot(),
    EditorModule

  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: HttpClientInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
