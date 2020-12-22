import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ComponentsModule } from './components/components.module';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ContentLoaderModule } from '@ngneat/content-loader';
import { HttpClientModule } from '@angular/common/http';
import { NgxMaskModule } from 'ngx-mask'
import { JwtModule } from '@auth0/angular-jwt';

import { AppComponent } from './app.component';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { LoggedTemplateComponent } from './templates/logged-template/logged-template.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { InstituicaoComponent } from './pages/instituicao/instituicao.component';
import { CultoComponent } from './pages/culto/culto.component';
import { ProfileComponent } from './pages/user_pages/profile/profile.component';
import { AgendamentoComponent } from './pages/user_pages/agendamento/agendamento.component';
import { MinstituicaoComponent } from './pages/user_pages/minstituicao/minstituicao.component';
import { McultoComponent } from './pages/user_pages/mculto/mculto.component';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/moment';
import * as moment from 'moment';
import { EditCultoModalComponent } from './pages/user_pages/mculto/edit-culto-modal/edit-culto-modal.component';
import { EditInstituicaoModalComponent } from './pages/user_pages/minstituicao/edit-instituicao-modal/edit-instituicao-modal.component';
import { CadastroComponent } from './pages/cadastro/cadastro.component';

export function momentAdapterFactory() {
  return adapterFactory(moment);
};

export function tokenGetter() {
  return localStorage.getItem("token");
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    LoggedTemplateComponent,
    InstituicaoComponent,
    CultoComponent,
    ProfileComponent,
    AgendamentoComponent,
    MinstituicaoComponent,
    McultoComponent,
    EditCultoModalComponent,
    EditInstituicaoModalComponent,
    CadastroComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ComponentsModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    FlexLayoutModule,
    ContentLoaderModule,
    HttpClientModule,
    CalendarModule.forRoot({ provide: DateAdapter, useFactory: momentAdapterFactory }),
    NgxMaskModule.forRoot(),
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        allowedDomains: ["*"],
        disallowedRoutes: [],
        authScheme: "Bearer "
      },
    }),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
