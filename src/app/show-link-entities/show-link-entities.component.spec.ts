import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowLinkEntitiesComponent } from './show-link-entities.component';

describe('ShowLinkEntitiesComponent', () => {
  let component: ShowLinkEntitiesComponent;
  let fixture: ComponentFixture<ShowLinkEntitiesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowLinkEntitiesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowLinkEntitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
