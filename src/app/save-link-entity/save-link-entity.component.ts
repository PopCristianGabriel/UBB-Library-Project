import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Book} from '../../model/Book';
import {Client} from '../../model/Client';
import {LinkEntity} from '../../model/LinkEntity';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-save-link-entity',
  templateUrl: './save-link-entity.component.html',
  styleUrls: ['./save-link-entity.component.css']
})
export class SaveLinkEntityComponent implements OnInit {
  bookId:string;
  clientId:string
  entityId:string;
  private urlBooks = 'http://localhost:8080/api/books';
  private urlClients = 'http://localhost:8080/api/clients';
  private urlLink = 'http://localhost:8080/api/link'
  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  get_book_id(event: any) {
    this.bookId = event.target.value;

  }


  get_book_by_id() : Observable<Book>{
    return this.http.post<Book>(this.urlBooks+"/getById",Number(this.bookId), {
      headers: new HttpHeaders({'Content-Type': 'application/json'})


    });
  }

  public get_client_by_id() : Observable<Client>{
 return  this.http.post<Client>(this.urlClients+"/getById",Number(this.clientId),{
  headers: new HttpHeaders({'Content-Type': 'application/json'})


});
  }
  get_client_id(event: any) {
    this.clientId = event.target.value;

  }

  save_entity() {
   let book: Book = new Book();
   let client:Client = new Client();
    return this.get_book_by_id().subscribe(result=>{
    book.id = result["id"];
    book.autorName = result["autorName"];
    book.year = result["year"];
    book.price = result["price"],
    book.title = result["title"];
       return this.get_client_by_id().subscribe(result=>{
          client.id = result["id"];
          client.fullName = result["fullName"];
          client.moneySpet = result["moneySpet"];
          return this.http.post(this.urlLink+"/save",JSON.stringify(new LinkEntity(Number(this.entityId), book, client)),{
            headers: new HttpHeaders({'Content-Type': 'application/json'})
        }).subscribe();
      }
    );




    });



  }

  get_entity_id(event: any) {
    this.entityId = event.target.value;
  }
}
