import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ApiCallerService } from 'src/app/services/api-caller.service';

import Swal from 'sweetalert2';

@Component({
  selector: 'app-agendamento',
  templateUrl: './agendamento.component.html',
  styleUrls: ['./agendamento.component.sass']
})

export class AgendamentoComponent implements AfterViewInit {
  
  displayedColumns: string[] = ['descricao', 'data', 'dataAgendamento', 'buttons'];
  dataSource: MatTableDataSource<any> = new MatTableDataSource<any>();

  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator? this.paginator: null;
  }

  constructor( 
    private apiCaller: ApiCallerService) {}

  ngOnInit(): void {
    this.carregarListaAgendamentos();

  }

  carregarListaAgendamentos() {
    this.apiCaller.carregarListadeAgendamentosPorUsuario().subscribe(
      response => {
        this.dataSource.data = response;
      },
      error => {
        Swal.fire(
          'Ops!',
          'Não foi possível carregar a listagem de agendamentos',
          'error'
        )
      }
    )
  }

  formatarDataParaExibicao(backendData: String) {
    if(backendData) {
      let data = backendData.split('T')[0];
      return data.substring(8, 10) + '/' + data.substring(5,7) + '/' + data.substring(0,4) + ' ' + backendData.substring(11, 19);
    } else {
      return '-';
    }
  }

  cancelSchedule(schedule: any) {
    
    Swal.fire({
      title: 'Deseja cancelar o agendamento?',
      text: "Você não podera voltar atrás",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sim!',
      cancelButtonText: 'Não!',
      reverseButtons: false
    }).then((result) => {
      
      if (result.isConfirmed) {
        this.apiCaller.excluirAgendamento(schedule.idCulto).subscribe(
          response => {
            Swal.fire(
              'Feito!',
              'O Agendamento foi cancelado com sucesso.',
              'success'
            ).then(()=> {
              this.carregarListaAgendamentos()
            })
          },
          error => {
            Swal.fire(
              'Ops!',
              'Não foi possível cancelar o agendamento.',
              'error'
            )
          }
        ) 
      }

    })
  
  }
  
}