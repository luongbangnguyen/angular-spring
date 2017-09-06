import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { WebSocketComponent } from './components/websocket/websocket.component';

const routes: Routes = [
  { path: '', component: DashboardComponent, pathMatch: 'full', data: {title: 'Home'}},
  { path: 'login', component: LoginComponent, data: {title: 'Login'} },
  { path: 'websocket', component: WebSocketComponent, data: {title: 'WebSocket Example'} }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
