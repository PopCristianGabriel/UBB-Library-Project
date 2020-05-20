import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {Books} from '../../model/Books';
import {Book} from '../../model/Book';
import {Clients} from '../../model/Clients';
import {Client} from '../../model/Client';

@Component({
  selector: 'app-show-clients',
  templateUrl: './show-clients.component.html',
  styleUrls: ['./show-clients.component.css']
})
export class ShowClientsComponent implements OnInit {
  private url = 'http://localhost:8080/api/clients/get';
  canWrite = false;
  list: Clients = new Clients();
  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})

  };
  constructor(private http: HttpClient, private router: Router) { }

  private get_clients() {
    return this.http.get<Clients>(this.url);
  }

  show_clients() {

    this.get_clients().subscribe(data => {
        console.log(data["clients"]);
        this.list.clients = <Array<Client>> data["clients"];
      }
    );
    this.canWrite = true;


  }


  ngOnInit(): void {
    this.show_clients();
  }

}
