import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';

import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { ShowBooksComponent } from './show-books/show-books.component';
import {RouterModule} from '@angular/router';

import {Routes} from '@angular/router';
import { ShowClientsComponent } from './show-clients/show-clients.component';
import { AddBookComponent } from './add-book/add-book.component';
import { AddClientComponent } from './add-client/add-client.component';
import { DeleteBookComponent } from './delete-book/delete-book.component';
import { DeleteClientComponent } from './delete-client/delete-client.component';
import { SaveChangesComponent } from './save-changes/save-changes.component';
import { UpdateClientComponent } from './update-client/update-client.component';
import { UpdateBookComponent } from './update-book/update-book.component';
import { ShowLinkEntitiesComponent } from './show-link-entities/show-link-entities.component';
import { SaveLinkEntityComponent } from './save-link-entity/save-link-entity.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ShowBooksComponent,
    ShowClientsComponent,
    AddBookComponent,
    AddClientComponent,
    DeleteBookComponent,
    DeleteClientComponent,
    SaveChangesComponent,
    UpdateClientComponent,
    UpdateBookComponent,
    ShowLinkEntitiesComponent,
    SaveLinkEntityComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path : 'show-books', component: ShowBooksComponent},
      {path : '', component: AppComponent},
      {path: 'show-clients', component: ShowClientsComponent},
      {path: 'add-book', component: AddBookComponent},
      {path: 'add-client', component: AddClientComponent},
      {path: 'delete-book', component: DeleteBookComponent},
      {path: 'delete-client', component: DeleteClientComponent},
      {path: 'update-client', component: UpdateClientComponent},
      {path: 'update-book', component: UpdateBookComponent},
      {path: 'show-LinkEntities', component: ShowLinkEntitiesComponent},
      {path: 'add-linkEntity', component: SaveLinkEntityComponent},
      {path: '**', redirectTo: ''}

    ]),

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
