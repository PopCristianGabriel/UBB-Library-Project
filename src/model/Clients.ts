import {Client} from './Client';

export class Clients {
  clients: Client[] = [];

  get_clients() {
    return this.clients;
  }
  add_client(client: Client) {
    this.clients.push(client);
  }
}
