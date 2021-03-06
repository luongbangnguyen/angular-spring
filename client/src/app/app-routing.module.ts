import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { WebSocketComponent } from './components/websocket/websocket.component';
import {AuthGuardService} from './services/auth-guard.service';
import { CalendarDemoComponent } from './components/calendar-demo/calendar-demo.component';

const routes: Routes = [
  { path: '', component: DashboardComponent,canActivate: [AuthGuardService], data: {title: 'Home'}},
  { path: 'login', component: LoginComponent, data: {title: 'Login'} },
  { path: 'websocket', component: WebSocketComponent,canActivate: [AuthGuardService], data: {title: 'WebSocket Example'} },
  { path: 'calendar', component: CalendarDemoComponent,canActivate: [AuthGuardService], data: {title: 'Calendar Example'} }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
