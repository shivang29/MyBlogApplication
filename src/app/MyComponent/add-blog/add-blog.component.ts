import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Postload } from './post-load';
import { AddBlogService } from './add-blog.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-blog',
  templateUrl: './add-blog.component.html',
  styleUrls: ['./add-blog.component.css']
})
export class AddBlogComponent implements OnInit {

  addPostForm: FormGroup;
  postload: Postload;
  title = new FormControl('');
  body = new FormControl('');

  constructor(private addpostService: AddBlogService, private router: Router) { 
    this.addPostForm = new FormGroup({
      title: this.title,
      body: this.body
    });
    this.postload = {
      
      content: '',
      title: '',
      username: '',
      createdOn: '',
      updatedOn: '',
      like: 0
    }
  }

  ngOnInit(): void {
  }

  addPost(){
    this.postload.content = this.addPostForm.get('body')?.value;
    this.postload.title = this.addPostForm.get('title')?.value;
    if(this.postload.title == ""){
      alert('Put Proper Title');
      return;
    }
    if(this.postload.content==""){
      alert("content can't be empty")
    }
    this.addpostService.addPost(this.postload).subscribe(data =>{
        this.router.navigateByUrl('/home');
    }, error =>{
        console.log('Failure to Post the Blog');
    }
    
      )
  }
}
