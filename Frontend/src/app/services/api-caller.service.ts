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
    return this.httpClient.get(this.baseUrl + '/instituicoes', {  headers: new HttpHeaders({ "Authorization": `Bearer ${this.token}`}) });
  }

  carregarListaInstituicoesUsuario(): Observable<any> {
    return this.httpClient.get(this.baseUrl + '/instituicoes/usuario/' + this.cpf, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  cadastrarUsuario(infoUsuario: any): Observable<any> {
    return this.httpClient.post(this.baseUrl + '/pessoa', infoUsuario);
  }

  editarUsuario(novaInfoUsuario: any): Observable<any> {
    return this.httpClient.patch(this.baseUrl + '/pessoa/' + this.cpf, novaInfoUsuario, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  carregarUsuario(): Observable<any> {
    return this.httpClient.get(this.baseUrl + '/pessoa/' + this.cpf, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  inserirInstituicao(infoInstituicao: any): Observable<any> {
    infoInstituicao.idUsuario = this.cpf;
    return this.httpClient.post(this.baseUrl + '/instituicao', infoInstituicao, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  excluirInstituicao(idInstituicao: String): Observable<any> {
    return this.httpClient.delete(this.baseUrl + '/instituicao/' + idInstituicao, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  atualizarInstituicao(idInstituicao: String, novaInfoInstituicao: any): Observable<any> {
    novaInfoInstituicao.idUsuario = this.cpf;
    delete novaInfoInstituicao.id;
    return this.httpClient.patch(this.baseUrl + '/instituicao/' + idInstituicao, novaInfoInstituicao, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  carregarListadeCultosPorInstituicao(idInstituicao: String): Observable<any> {
    return this.httpClient.get(this.baseUrl + '/cultos/' + idInstituicao, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  inserirCulto(idInstituicao: String, infoCulto: any) {
    return this.httpClient.post(this.baseUrl + '/culto', infoCulto, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  editarCulto(idCulto: String, infoAtualizadaCulto: any) {
    return this.httpClient.patch(this.baseUrl + '/culto/' + idCulto, infoAtualizadaCulto, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  excluirCulto(idCulto: String): Observable<any> {
    return this.httpClient.delete(this.baseUrl + '/culto/' + idCulto, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  inserirAgendamento(infoAgendamento: any) {
    infoAgendamento.idPessoa = this.cpf;
    return this.httpClient.post(this.baseUrl + '/agendamento', infoAgendamento, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  carregarListadeAgendamentosPorUsuario(): Observable<any> {
    return this.httpClient.get(this.baseUrl + '/agendamentos/usuario/' + this.cpf, { headers: { Authorization: 'Bearer ' + this.token } });
  }

  excluirAgendamento(idCulto: String): Observable<any> {
    return this.httpClient.delete(this.baseUrl + '/agendamento/culto/' + idCulto + '/usuario/' + this.cpf, { headers: { Authorization: 'Bearer ' + this.token } });
  }

}