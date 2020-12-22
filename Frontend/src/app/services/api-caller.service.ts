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

  httpOptions!: any;

  constructor(private httpClient: HttpClient, private router: Router) {  
    this.token = localStorage.getItem('token');
    
    if(this.token) {
      const user = jwt_decode(this.token);
      this.cpf = (<any>user).cpf;

      this.httpOptions = {
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
    return this.httpClient.get(this.baseUrl + '/instituicoes', this.httpOptions);
  }

  carregarListaInstituicoesUsuario(): Observable<any> {
    return this.httpClient.get(this.baseUrl + '/instituicoes/usuarios/' + this.cpf, this.httpOptions);
  }

  cadastrarUsuario(infoUsuario: any): Observable<any> {
    return this.httpClient.post(this.baseUrl + '/pessoas', infoUsuario);
  }

  editarUsuario(novaInfoUsuario: any): Observable<any> {
    return this.httpClient.patch(this.baseUrl + '/pessoas/' + this.cpf, novaInfoUsuario, this.httpOptions);
  }

  carregarUsuario(): Observable<any> {
    return this.httpClient.get(this.baseUrl + '/pessoas/' + this.cpf, this.httpOptions);
  }

  inserirInstituicao(infoInstituicao: any): Observable<any> {
    infoInstituicao.idUsuario = this.cpf;
    return this.httpClient.post(this.baseUrl + '/instituicoes', infoInstituicao, this.httpOptions);
  }

  excluirInstituicao(idInstituicao: String): Observable<any> {
    return this.httpClient.delete(this.baseUrl + '/instituicoes/' + idInstituicao, this.httpOptions);
  }

  atualizarInstituicao(idInstituicao: String, novaInfoInstituicao: any): Observable<any> {
    novaInfoInstituicao.idUsuario = this.cpf;
    delete novaInfoInstituicao.id;
    return this.httpClient.patch(this.baseUrl + '/instituicoes/' + idInstituicao, novaInfoInstituicao, this.httpOptions);
  }

  carregarListadeCultosPorInstituicao(idInstituicao: String): Observable<any> {
    return this.httpClient.get(this.baseUrl + '/cultos/instituicoes/' + idInstituicao, this.httpOptions);
  }

  inserirCulto(idInstituicao: String, infoCulto: any) {
    return this.httpClient.post(this.baseUrl + '/cultos', infoCulto, this.httpOptions);
  }

  editarCulto(idCulto: String, infoAtualizadaCulto: any) {
    return this.httpClient.patch(this.baseUrl + '/cultos/' + idCulto, infoAtualizadaCulto, this.httpOptions);
  }

  excluirCulto(idCulto: String): Observable<any> {
    return this.httpClient.delete(this.baseUrl + '/cultos/' + idCulto, this.httpOptions);
  }

  inserirAgendamento(infoAgendamento: any) {
    infoAgendamento.idPessoa = this.cpf;
    return this.httpClient.post(this.baseUrl + '/agendamentos', infoAgendamento, this.httpOptions);
  }

  carregarListadeAgendamentosPorUsuario(): Observable<any> {
    return this.httpClient.get(this.baseUrl + '/agendamentos/usuarios/' + this.cpf, this.httpOptions);
  }

  excluirAgendamento(idCulto: String): Observable<any> {
    return this.httpClient.delete(this.baseUrl + '/agendamentos/cultos/' + idCulto + '/usuarios/' + this.cpf, this.httpOptions);
  }

}