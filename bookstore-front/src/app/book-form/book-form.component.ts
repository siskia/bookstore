import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'bs-book-form',
  templateUrl: './book-form.component.html',
  styles: []
})
  
export class BookFormComponent implements OnInit {
 
    book = {
        title: "dummy title",
        description: "dummy description",
        unitCost: "123",
        isbn: "123-3456-567",
        nbOfPages:"456",
        language: "English",
        imageUrl: "http://ecx.images-amazon.com/images/I/51baRLwlufL._SL160_.jpg"
    };

  constructor() { }

  ngOnInit() {
  }

}