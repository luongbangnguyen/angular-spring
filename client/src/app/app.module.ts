import { BrowserModule, Title } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './components/app/app.component';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { WebSocketComponent } from './components/websocket/websocket.component';

import { AppRoutingModule } from './app-routing.module';

import { AuthenticationService } from './services/authenticaiton.service';
import { StompService } from 'ng2-stomp-service';
import {AuthGuardService} from './services/auth-guard.service'


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    WebSocketComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    Title,
    AuthenticationService,
    StompService,
    AuthGuardService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
