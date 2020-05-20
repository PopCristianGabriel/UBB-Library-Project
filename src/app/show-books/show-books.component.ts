import { Component, OnInit } from '@angular/core';
import {Books} from '../../model/Books';
import {Book} from '../../model/Book';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-show-books',
  templateUrl: './show-books.component.html',
  styleUrls: ['./show-books.component.css']
})
export class ShowBooksComponent implements OnInit {

  list: Books = new Books();
  lmao: Array<Book>;
  book: Book = new Book(1, 'angular', 'angular', 2, 2);
  canWrite = false;
  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})

  };


  constructor(private http: HttpClient, private router: Router) {

  }

  private url = 'http://localhost:8080/api/books/get';


  private get_books() {
    return this.http.get<Books>(this.url);
  }

  show_books() {

    this.get_books().subscribe(data => {
        console.log(data["books"]);
        this.list.listOfBooks = <Array<Book>> data["books"];
      }
    );
    this.canWrite = true;
    this.lmao = this.list.listOfBooks;

  }




  ngOnInit(): void {
    this.show_books();
  }


}
