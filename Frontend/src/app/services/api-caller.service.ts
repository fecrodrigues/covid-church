import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subscription } from 'rxjs';

import jwt_decode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class ApiCallerService {

  baseUrl: String = 'http://localhost:3001';
  cpf: String = '';

  constructor(private httpClient: HttpClient) {  
    const token = localStorage.getItem('token'); 
    const user = jwt_decode(token!);
    
    this.cpf = (<any>user).cpf;
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

  carregarListadeCultosPorInstituicao(idInstituicao: String): Observable<any> {
    console.log(idInstituicao, 'id')
    return this.httpClient.get("assets/jsonExamples.json");
  }

  carregarListadeAgendamentosPorUsuario(cpf: String): Observable<any> {
    console.log(cpf, 'cpf')
    return this.httpClient.get("assets/jsonExamples.json");
  }

}