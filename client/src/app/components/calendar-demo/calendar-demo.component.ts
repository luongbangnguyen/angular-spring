import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-calendar-demo',
  templateUrl: './calendar-demo.component.html',
  styleUrls: ['./calendar-demo.component.css']
})
export class CalendarDemoComponent implements OnInit {

  date: string;

  constructor() { }

  ngOnInit() {
  }

  onDateChange(date) {
    this.date = date;
  }
}
