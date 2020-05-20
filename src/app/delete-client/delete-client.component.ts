import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Client} from '../../model/Client';
import {Book} from '../../model/Book';

@Component({
  selector: 'app-delete-client',
  templateUrl: './delete-client.component.html',
  styleUrls: ['./delete-client.component.css']
})
export class DeleteClientComponent implements OnInit {
  id:string
  client:Client;
  private urlClients = 'http://localhost:8080/api/clients';
  private urlBooks = 'http://localhost:8080/api/books';
  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  delete_client() {
    return this.http.post<Client>(this.urlClients+'/deleteClient',JSON.stringify(Number(this.id)),{
      headers: new HttpHeaders({'Content-Type': 'application/json'})


    }).subscribe(result=>{

      let books :Book[] = result["booksBought"];
      return this.http.post<Book>(this.urlBooks+'/saveManyBooks',books,{
        headers: new HttpHeaders({'Content-Type': 'application/json'})


      }).subscribe();
    });
  }

  add_to_id(event: any) {
    this.id = event.target.value;

  }
}
