import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { Router } from '@angular/router';

import jwt_decode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class ApiCallerService {

  baseUrl: String = 'http://localhost:3001';
  cpf: String = '';
  token: any;

  constructor(private httpClient: HttpClient, private router: Router) {  
    this.token = localStorage.getItem('token');
    
    if(this.token) {
      const user = jwt_decode(this.token);
      this.cpf = (<any>user).cpf;

      const httpOptions = {
        headers: new HttpHeaders({
          Authorization: 'Bearer ' + this.token
        })
      };
    } else {
      this.router.navigate(['login'])
    }
    
  }

  efetuarLogin(infoLogin: any) {
    return this.httpClient.post(this.baseUrl + '/usuarios/login', infoLogin);
  }

  carregarListaInstituicoes(): Observable<any> {
    return this.httpClient.get(this.baseUrl + '/instituicoes', {  headers: new HttpHeaders({ "Authorization": 'Bearer ' + this.token}) });
  }

  carregarListaInstituicoesUsuario(): Observable<any> {
    return this.httpClient.get(this.baseUrl + '/instituicoes/usuarios/' + this.cpf, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  cadastrarUsuario(infoUsuario: any): Observable<any> {
    return this.httpClient.post(this.baseUrl + '/pessoas', infoUsuario);
  }

  editarUsuario(novaInfoUsuario: any): Observable<any> {
    return this.httpClient.patch(this.baseUrl + '/pessoas/' + this.cpf, novaInfoUsuario, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  carregarUsuario(): Observable<any> {
    return this.httpClient.get(this.baseUrl + '/pessoas/' + this.cpf, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  inserirInstituicao(infoInstituicao: any): Observable<any> {
    infoInstituicao.idUsuario = this.cpf;
    return this.httpClient.post(this.baseUrl + '/instituicoes', infoInstituicao, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  excluirInstituicao(idInstituicao: String): Observable<any> {
    return this.httpClient.delete(this.baseUrl + '/instituicoes/' + idInstituicao, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  atualizarInstituicao(idInstituicao: String, novaInfoInstituicao: any): Observable<any> {
    novaInfoInstituicao.idUsuario = this.cpf;
    delete novaInfoInstituicao.id;
    return this.httpClient.patch(this.baseUrl + '/instituicoes/' + idInstituicao, novaInfoInstituicao, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  carregarListadeCultosPorInstituicao(idInstituicao: String): Observable<any> {
    return this.httpClient.get(this.baseUrl + '/cultos/' + idInstituicao, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  inserirCulto(idInstituicao: String, infoCulto: any) {
    return this.httpClient.post(this.baseUrl + '/cultos', infoCulto, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  editarCulto(idCulto: String, infoAtualizadaCulto: any) {
    return this.httpClient.patch(this.baseUrl + '/cultos/' + idCulto, infoAtualizadaCulto, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  excluirCulto(idCulto: String): Observable<any> {
    return this.httpClient.delete(this.baseUrl + '/cultos/' + idCulto, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  inserirAgendamento(infoAgendamento: any) {
    infoAgendamento.idPessoa = this.cpf;
    return this.httpClient.post(this.baseUrl + '/agendamentos', infoAgendamento, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  carregarListadeAgendamentosPorUsuario(): Observable<any> {
    return this.httpClient.get(this.baseUrl + '/agendamentos/usuarios/' + this.cpf, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  excluirAgendamento(idCulto: String): Observable<any> {
    return this.httpClient.delete(this.baseUrl + '/agendamentos/cultos/' + idCulto + '/usuarios/' + this.cpf, { headers: { Authorization: 'Bearer ' + this.token } });
  }

}