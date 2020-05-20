import {Book} from './Book';
import {Client} from './Client';
import {Entity} from './Entity';

export class LinkEntity extends Entity{
  book: Book;
  client: Client;

  constructor(id?:number,book?:Book, client?:Client){
    super(id);
    this.book = book;
    this.client = client;
  }
}
