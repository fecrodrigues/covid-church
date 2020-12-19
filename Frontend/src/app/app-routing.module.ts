import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoggedTemplateComponent } from './templates/logged-template/logged-template.component'; 

import { LoginComponent } from './pages/login/login.component';
import { HomeComponent }  from './pages/home/home.component';
import { InstituicaoComponent } from './pages/instituicao/instituicao.component';
import { CultoComponent } from './pages/culto/culto.component';

import { ProfileComponent } from './pages/user_pages/profile/profile.component';
import { MinstituicaoComponent } from './pages/user_pages/minstituicao/minstituicao.component';
import { McultoComponent } from './pages/user_pages/mculto/mculto.component';
import { AgendamentoComponent } from './pages/user_pages/agendamento/agendamento.component';

import { AuthGuardService as AuthGuard } from './services/auth-guard.service';


const routes: Routes = [
  { path: '', component: LoggedTemplateComponent, canActivate: [AuthGuard], children: [
    { path: '', component: HomeComponent },
    { path: 'instituicoes', component: InstituicaoComponent },
    { path: 'cultos', component: CultoComponent },
    { path: 'perfil', component: ProfileComponent },
    { path: 'minhas-instituicoes', component: MinstituicaoComponent },
    { path: 'meus-cultos', component: McultoComponent },
    { path: 'agendamentos', component: AgendamentoComponent }
  ]},
  { path: 'login', component: LoginComponent }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
