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

  cpf = '123435678909';
  
  displayedColumns: string[] = ['nomeCulto', 'dataCulto', 'dataAgendamento', 'buttons'];
  dataSource: MatTableDataSource<any> = new MatTableDataSource<any>();

  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator? this.paginator: null;
  }

  constructor( 
    private apiCaller: ApiCallerService) {}

  ngOnInit(): void {
    this.apiCaller.carregarListadeAgendamentosPorUsuario(this.cpf).subscribe(response => {
      console.log(response, 'response')
      this.dataSource.data = response;
    })

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
        Swal.fire(
          'Feito!',
          'O Agendamento foi cancelado com sucesso.',
          'success'
        )
      }

    })
  
  }
  
}