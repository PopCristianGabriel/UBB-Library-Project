import {LinkEntity} from './LinkEntity';

export class LinkEntities{
  entities: LinkEntity[] = [];
  constructor(list?:LinkEntity[]){
    this.entities = list;
  }
}
