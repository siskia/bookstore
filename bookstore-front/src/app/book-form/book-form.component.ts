import { Component, OnInit, NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { AppComponent } from '../app.component';
import { Book, BookService } from '../service';
import { Router } from '@angular/router';
import { finalize, map, switchMap } from 'rxjs/operators';
//import  'rxjs';

@Component({
  selector: 'bs-book-form',
  templateUrl: './book-form.component.html',
  styles: []
})
@NgModule({
  imports: [ BrowserModule, FormsModule ],
  declarations: [ AppComponent ],
  bootstrap: [ AppComponent ]
  })
export class BookFormComponent implements OnInit {
  private book: Book = {title: 'title', description: 'description', isbn: 'isbn', imageURL: 'imageURL'}
  constructor(private router: Router, private bookService: BookService) { }

  ngOnInit() {
  }

  create() {
    this.bookService.createBook(this.book)
    .pipe(finalize(() => this.router.navigate(['/book-list'])))
    .subscribe();
  }

}
