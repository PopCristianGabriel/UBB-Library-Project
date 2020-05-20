import {Book} from './Book';
import {Entity} from './Entity';


export class Client extends Entity{

  fullName:string
  booksBought: Book[] = [];
  moneySpet:number = 0;

  constructor(id?:number,fullName? :string,booksBought?:Book[],moneySpent?:number){
    super(id);
    this.fullName = fullName;
    this.booksBought = booksBought;
    this.moneySpet = moneySpent;
    this.booksBought = [];

  }

}
