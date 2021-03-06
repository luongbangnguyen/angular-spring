import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WebSocketComponent } from './websocket.component';

describe('WebSocketComponent', () => {
  let component: WebSocketComponent;
  let fixture: ComponentFixture<WebSocketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WebSocketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WebSocketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
