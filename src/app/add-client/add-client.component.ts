import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Client} from '../../model/Client';

@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.css']
})
export class AddClientComponent implements OnInit {
  id: string;
  fullName: string;
  private url = 'http://localhost:8080/api/clients';
  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  add_to_id(event: any) {
    this.id = event.target.value;
  }
  add_to_name(event: any) {
    this.fullName = event.target.value;
  }

  save_client() {
    const client: Client = new Client(Number(this.id), this.fullName);
    return this.http.post<any>(this.url + '/saveClient2', JSON.stringify(client), {
      headers: new HttpHeaders({'Content-Type': 'application/json'})


    }).subscribe();
  }
}
