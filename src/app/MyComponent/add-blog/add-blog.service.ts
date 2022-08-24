import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {Postload} from './post-load';
import {Comments} from '../blog/comment';

@Injectable({
  providedIn: 'root'
})
export class AddBlogService {

  constructor(private httpClient: HttpClient) { }

  
  API = "http://localhost:8088/api/v1.0/blog/";
  
  addPost(postload: Postload){
    return this.httpClient.post('http://localhost:8088/api/v1.0/blog/add', postload);
  }

  getAllPosts() : Observable<Array<Postload>>{
    return this.httpClient.get<Array<Postload>>('http://localhost:8088/api/v1.0/blog/domain/all');
  }
  getPost(permaLink: String):Observable<Postload>{
    return this.httpClient.get<Postload>('http://localhost:8088/api/v1.0/blog/get/' + permaLink);
  }
  public deleteBlog(post : String) : Observable<Postload>{
    console.log(post);
    return this.httpClient.delete<Postload>(this.API + 'delete/'+ post);
  }
  
  public likeblog(title : String) : Observable<String>{
    console.log(title);
    return this.httpClient.put<String>(this.API + 'like/'+ title,title);
  }
  public updateblog(post: Postload): Observable<Postload>{
    console.log(post);
    return this.httpClient.put<Postload>(this.API + 'update', post);
  }
  public addComment(reply: Comments){
    console.log(reply);
    return this.httpClient.post(this.API + 'reply/'+ reply.title,reply);
  }

  public getAllComments(permaLink : String): Observable<Array<Comments>> {
    return this.httpClient.get<Array<Comments>>(this.API + 'reply/get/' + permaLink); 
  }
}
