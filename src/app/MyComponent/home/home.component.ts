import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AddBlogService } from '../add-blog/add-blog.service';
import { Postload } from '../add-blog/post-load';
import { AuthService } from '../auth/auth.service';
import {LocalStorageService} from 'ngx-webstorage';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  posts!: Observable<Array<Postload>>;
  loggedin!: String
  constructor(private postService: AddBlogService, public authService: AuthService, private localStoraqeService: LocalStorageService) { }

  ngOnInit(): void {
    this.posts= this.postService.getAllPosts();
    this.loggedin = this.localStoraqeService.retrieve('username');
  }

}
