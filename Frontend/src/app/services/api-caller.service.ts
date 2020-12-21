import { HttpClient } from '@angular/common/http';
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

  constructor(private httpClient: HttpClient, private router: Router) {  
    const token = localStorage.getItem('token');
    
    if(token) {
      const user = jwt_decode(token);
      this.cpf = (<any>user).cpf;
    } else {
      this.router.navigate(['login'])
    }
    
  }

  efetuarLogin(infoLogin: any) {
    return this.httpClient.post(this.baseUrl + '/login', infoLogin);
  }

  carregarListaInstituicoes(): Observable<any> {
    return this.httpClient.get(this.baseUrl + '/instituicoes');
  }

  carregarListaInstituicoesUsuario(): Observable<any> {
    return this.httpClient.get(this.baseUrl + '/instituicoes/usuario/' + this.cpf);
  }

  cadastrarUsuario(infoUsuario: any): Observable<any> {
    return this.httpClient.post(this.baseUrl + '/pessoa', infoUsuario);
  }

  editarUsuario(novaInfoUsuario: any): Observable<any> {
    return this.httpClient.patch(this.baseUrl + '/pessoa/' + this.cpf, novaInfoUsuario);
  }

  carregarUsuario(): Observable<any> {
    return this.httpClient.get(this.baseUrl + '/pessoa/' + this.cpf);
  }

  inserirInstituicao(infoInstituicao: any): Observable<any> {
    infoInstituicao.idUsuario = this.cpf;
    return this.httpClient.post(this.baseUrl + '/instituicao', infoInstituicao);
  }

  excluirInstituicao(idInstituicao: String): Observable<any> {
    return this.httpClient.delete(this.baseUrl + '/instituicao/' + idInstituicao);
  }

  atualizarInstituicao(idInstituicao: String, novaInfoInstituicao: any): Observable<any> {
    novaInfoInstituicao.idUsuario = this.cpf;
    delete novaInfoInstituicao.id;
    return this.httpClient.patch(this.baseUrl + '/instituicao/' + idInstituicao, novaInfoInstituicao);
  }

  carregarListadeCultosPorInstituicao(idInstituicao: String): Observable<any> {
    return this.httpClient.get(this.baseUrl + '/cultos/' + idInstituicao);
  }

  inserirCulto(idInstituicao: String, infoCulto: any) {
    return this.httpClient.post(this.baseUrl + '/culto', infoCulto);
  }

  editarCulto(idCulto: String, infoAtualizadaCulto: any) {
    return this.httpClient.patch(this.baseUrl + '/culto/' + idCulto, infoAtualizadaCulto);
  }

  excluirCulto(idCulto: String): Observable<any> {
    return this.httpClient.delete(this.baseUrl + '/culto/' + idCulto);
  }

  inserirAgendamento(infoAgendamento: any) {
    infoAgendamento.idPessoa = this.cpf;
    return this.httpClient.post(this.baseUrl + '/agendamento', infoAgendamento);
  }

  carregarListadeAgendamentosPorUsuario(): Observable<any> {
    return this.httpClient.get(this.baseUrl + '/agendamentos/usuario/' + this.cpf);
  }

  excluirAgendamento(idCulto: String): Observable<any> {
    return this.httpClient.delete(this.baseUrl + '/agendamento/culto/' + idCulto + '/usuario/' + this.cpf);
  }

}