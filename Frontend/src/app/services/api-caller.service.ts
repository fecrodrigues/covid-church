import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subscription } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiCallerService {

  baseUrl: String = 'http://localhost:3001'

  constructor(private httpClient: HttpClient) {  }


  carregarListaInstituicoes(): Observable<any> {
    return this.httpClient.get("assets/jsonExamples.json");
  }

  carregarListadeCultosPorInstituicao(idInstituicao: String): Observable<any> {
    console.log(idInstituicao, 'id')
    return this.httpClient.get("assets/jsonExamples.json");
  }

  carregarListadeAgendamentosPorUsuario(cpf: String): Observable<any> {
    console.log(cpf, 'cpf')
    return this.httpClient.get("assets/jsonExamples.json");
  }

  cadastrarUsuario(infoUsuario: any): Observable<any> {
    return this.httpClient.post(this.baseUrl + '/pessoa', infoUsuario);
  }

}