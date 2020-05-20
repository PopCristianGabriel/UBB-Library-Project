import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Component({
  selector: 'app-delete-book',
  templateUrl: './delete-book.component.html',
  styleUrls: ['./delete-book.component.css']
})
export class DeleteBookComponent implements OnInit {
  id:string;
  private url = 'http://localhost:8080/api/books';
  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  add_to_id(event: any) {
    this.id = event.target.value;
  }


  delete_client() {
    return this.http.post<any>(this.url+'/deleteBook',JSON.stringify(Number(this.id)),{
      headers: new HttpHeaders({'Content-Type': 'application/json'})


    }).subscribe();
  }
}
