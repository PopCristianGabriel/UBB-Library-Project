import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHandler, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {Book} from '../model/Book';
import {Books} from '../model/Books';
import {Router} from '@angular/router';
import {Client} from '../model/Client';
import {LinkEntity} from '../model/LinkEntity';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  list: Books = new Books();
  lmao: Array<Book>;
  book: Book = new Book(1, 'angular', 'angular', 2, 2);
  canWrite = false;
  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})

  };
  title: string = 'angular';

  constructor(private http: HttpClient, private router: Router) {

  }
  private url2 = 'http://localhost:8080/api/books/dawd';
  private url = 'http://localhost:8080/api/books/get';
  private urlBooks = 'http://localhost:8080/api/books';
  private urlClients = 'http://localhost:8080/api/clients';
  private urlEntities = 'http://localhost:8080/api/link';
  private get_clients() {
    return this.http.get<Book>(this.url2);
  }

  private get_books() {
    return this.http.get<Books>(this.url);
  }

  show_books() {
    this.router.navigate(['show-books']);
    return;
    this.get_books().subscribe(data => {
        console.log(data["books"]);
        this.list.listOfBooks = <Array<Book>> data["books"];
      }
    );
    this.canWrite = true;
    this.lmao = this.list.listOfBooks;

  }
  get_books2() {
    return this.list.get_books();
  }

  show_clients() {
    this.router.navigate(['show-clients']);
    return;



  }

  ngOnInit(): void {

  }


  add_book() {
    this.router.navigate(['add-book']);
  }

  add_client() {
    this.router.navigate(['add-client']);
  }

  delete_book() {
    this.router.navigate(['delete-book']);
  }

  delete_client() {
    this.router.navigate(['delete-client']);
  }

  save_changes() {
    return this.http.post<any>(this.urlBooks+'/writeToDataBase2',JSON.stringify(new Book(1,"dawd","dwad",2,2)),{
      headers: new HttpHeaders({'Content-Type': 'application/json'})


    }).subscribe(result=>{
      return this.http.post<any>(this.urlClients+'/writeToDataBase2',JSON.stringify(new Client(1,"dawd")),{
        headers: new HttpHeaders({'Content-Type': 'application/json'})


      }).subscribe(result=>{
        return this.http.post<any>(this.urlEntities+'/writeToDataBase',JSON.stringify(new LinkEntity()),{
          headers: new HttpHeaders({'Content-Type': 'application/json'})


        }).subscribe();
        }
      );
    });
  }

  update_client() {
    this.router.navigate(['update-client']);
  }

  update_book() {
    this.router.navigate(['update-book']);
  }

  show_linkEntities() {
    this.router.navigate(['show-LinkEntities']);
  }

  add_linkEntity() {
    this.router.navigate(['add-linkEntity']);
  }
}




