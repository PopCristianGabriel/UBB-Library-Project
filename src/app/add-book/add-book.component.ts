import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Book} from '../../model/Book';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {

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

  private get_biggest_id() : Observable<Book>{
    return this.http.get<any>(this.url+"/getBiggestId",this.httpOptions);
  }

  save_book() {
    console.log(this.title,this.author,this.price,this.year);
    let id:number;


    // tslint:disable-next-line:label-position
    console.log(id);
    let bookToAdd : Book = new Book(Number(this.id), this.author, this.title, Number(this.year), Number(this.price));
    console.log(bookToAdd);
    return this.http.post<any>(this.url + '/saveBook2', JSON.stringify(bookToAdd), {
      headers: new HttpHeaders({'Content-Type': 'application/json'})


}).subscribe();
}
}
