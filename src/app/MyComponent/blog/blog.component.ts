import { Component, OnInit } from '@angular/core';
import {Postload} from '../add-blog/post-load';
import {ActivatedRoute, Router} from '@angular/router';
import {AddBlogService} from '../add-blog/add-blog.service';
import { AuthService } from '../auth/auth.service';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { Comments } from './comment';
import { Observable } from 'rxjs';

import {LocalStorageService} from 'ngx-webstorage';
@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.css']
})
export class BlogComponent implements OnInit {

  

  post!: Postload;
  PtnDetail !: FormGroup;
  permaLink!: String;
  blogOnj : Postload = new Postload();
  addCommentForm : FormGroup;

  reply: Comments;

  comment = new FormControl('');

  commentsOnPost !: Observable<Array<Comments>>

  constructor( private formBuilder : FormBuilder ,private router: ActivatedRoute, private postService: AddBlogService, public authService: AuthService, private localStoraqeService: LocalStorageService, private rout:Router) {
    this.addCommentForm = new FormGroup({
      comment : this.comment,
    })
    this.reply = {
      title : '',
      comment: '',
      username: ''
    }
  }

  ngOnInit() {
    this.router.params.subscribe(params => {
      this.permaLink = params['title'];
      
    });

    this.postService.getPost(this.permaLink).subscribe((data:Postload) => {
    
      this.post = data;
      
     // console.log(this.post.createdOn);
     // this.post.createdOn =  Date(data.createdOn);
    },(err: any) => {
      console.log('Failure Response');
    })

    this.commentsOnPost= this.postService.getAllComments(this.permaLink);
    
 
    this. PtnDetail = this.formBuilder.group({
     title : [''],
     content : [''],
     username : ['']
      
    });
  }
  deleteBlog(post: Postload){
    //console.log(pos);
    this.postService.deleteBlog(post.title).subscribe(res =>{
      this.rout.navigateByUrl('/home');
      alert('Blog is deleted');
      
    },err =>{
      console.log(err);
    }
    )
  }
  updateBlog(post:Postload){
    this.blogOnj.title = this.PtnDetail.value.title;
    this.blogOnj.content = this.PtnDetail.value.content;
    this.blogOnj.username = this.PtnDetail.value.username;
    this.postService.updateblog(this.blogOnj).subscribe(res =>{
      console.log(res);
      this.rout.navigateByUrl('/home');
    },err=>{
      
      console.log(err);
    }
    )
  }
  editBlog(post: Postload){
    console.log(post);
    this.PtnDetail.controls['title'].setValue(post.title);
    this.PtnDetail.controls['content'].setValue(post.content);
    this.PtnDetail.controls['username'].setValue(post.username);

  }
  
  uploadComment(){
    
  }
  addComment(){
    this.reply.comment = this.addCommentForm.get('comment')?.value;
    this.reply.title = this.post.title;
    this.postService.addComment(this.reply).subscribe(data =>{
    console.log(this.reply);
    alert('comment is added');
  }, error =>{
      console.log('Failure to Post the Blog');
  }
  
    )
  }
    
  iscurrentuser(){
    if(this.localStoraqeService.retrieve('username') == this.post.username ){
      return true;
    }else{
      return false;
    }
    
  }

  likeBlog(post:Postload){
    this.postService.likeblog(post.title).subscribe(res =>{
      this.rout.navigateByUrl('/blog/' + post.title);
     // alert('Blog is deleted');
      
    },err =>{
      console.log(err);
    }
    )
  }

}
