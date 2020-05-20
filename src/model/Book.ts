import {Entity} from './Entity';
export class Book extends Entity {

  autorName: string;
  title: string;
  year: number;
  price: number;


  constructor(id?: number, author?: string, title?: string, price?: number, year?: number) {
    super(id);
    this.title = title;
    this.autorName = author;
    this.price = price;
    this.year = year;
  }

  public get_id() {
    return super.get_id();
  }
  public get_title() {
    return this.title;
  }
  public get_price() {
    return this.price;
  }
  public get_year() {
    return this.year;
  }
  public get_author() {
    return this.autorName;
  }




}
