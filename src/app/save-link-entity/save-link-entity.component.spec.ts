import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveLinkEntityComponent } from './save-link-entity.component';

describe('SaveLinkEntityComponent', () => {
  let component: SaveLinkEntityComponent;
  let fixture: ComponentFixture<SaveLinkEntityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SaveLinkEntityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SaveLinkEntityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
