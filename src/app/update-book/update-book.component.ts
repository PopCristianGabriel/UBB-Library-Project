import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Book} from '../../model/Book';

@Component({
  selector: 'app-update-book',
  templateUrl: './update-book.component.html',
  styleUrls: ['./update-book.component.css']
})
export class UpdateBookComponent implements OnInit {

  id:string;
  title:string;
  author:string;
  price:string;
  year:string;
  private url = 'http://localhost:8080/api/books';
  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})

  };
  constructor(private http: HttpClient) { }

  add_to_id(event:any){
    this.id = event.target.value;
  }
  add_to_title(event:any){
    this.title = event.target.value;
  }
  add_to_author(event:any){
    this.author = event.target.value;
  }
  add_to_price(event:any){
    this.price = event.target.value;
  }
  add_to_year(event:any){
    this.year = event.target.value;
  }


  ngOnInit(): void {
  }

  save_book() {
    let book : Book = new Book(Number(this.id), this.author, this.title, Number(this.year), Number(this.price));
    console.log(book);
    return this.http.post<any>(this.url + '/updateBook', JSON.stringify(book), {
      headers: new HttpHeaders({'Content-Type': 'application/json'})


    }).subscribe();
  }
}
