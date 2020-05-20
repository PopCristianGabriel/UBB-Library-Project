export class Entity {
  id: number;

  constructor(id: number) {
    this.id = id;
  }


  public get_id() {
    return this.id;
  }

  public set_id(id: number) {
    this.id = id;
  }

}
