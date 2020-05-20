import {Book} from './Book';

export class Books {
  listOfBooks: Book[] = [];

  get_books() {
    return this.listOfBooks;
  }

  add_book(book: Book) {
    this.listOfBooks.push(book);
  }

}
