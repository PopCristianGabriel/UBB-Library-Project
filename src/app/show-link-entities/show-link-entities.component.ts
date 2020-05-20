import { Component, OnInit } from '@angular/core';
import {Clients} from '../../model/Clients';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {Client} from '../../model/Client';
import {LinkEntity} from '../../model/LinkEntity';
import {LinkEntities} from '../../model/LinkEntities';

@Component({
  selector: 'app-show-link-entities',
  templateUrl: './show-link-entities.component.html',
  styleUrls: ['./show-link-entities.component.css']
})
export class ShowLinkEntitiesComponent implements OnInit {

  private url = 'http://localhost:8080/api/link/get';
  canWrite = false;
  list: LinkEntities = new LinkEntities();
  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})

  };
  constructor(private http: HttpClient, private router: Router) { }

  private get_clients() {
    return this.http.get<LinkEntities>(this.url);
  }

  show_clients() {

    this.get_clients().subscribe(data => {
      debugger
        console.log(data["entities"]);
        this.list.entities = data["entities"];

      }
    );
    this.canWrite = true;


  }


  ngOnInit(): void {
    this.show_clients();
  }

}
