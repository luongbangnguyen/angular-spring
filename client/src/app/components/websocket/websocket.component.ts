import {Component, OnDestroy, OnInit} from '@angular/core';
import {StompService} from "ng2-stomp-service";
import {AppConstants} from "../../app-constants";
import { UUID } from 'angular2-uuid';

@Component({
  selector: 'app-websocket',
  templateUrl: './websocket.component.html',
  styleUrls: ['./websocket.component.css']
})
export class WebSocketComponent implements OnInit, OnDestroy {

  messages : Array<string> = [];
  isProcessing = false;
  destination: string;

  private subscription : any;

  constructor(private stomp: StompService) { }

  ngOnInit() {
    this.destination = UUID.UUID();
    this.stomp.configure(AppConstants.WEB_SOCKET_CONFIG);
    this.stomp.startConnect().then(() => {
      this.stomp.done('init');
      this.subscription = this.stomp.subscribe(`/queue/greetings/${this.destination}`, this.subscribeProcessing);
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
    this.stomp.disconnect().then();
  }

  subscribeProcessing = (data) => {
    this.messages.push(data.content.toString());
    this.isProcessing = data.isProcessing;
  };

  callWebSocket() {
    this.messages = [];
    this.stomp.send(`/app/hello/${this.destination}`,null);
  }

}
