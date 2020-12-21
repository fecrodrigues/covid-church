import { ChangeDetectionStrategy, Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

import { ApiCallerService } from './../../services/api-caller.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-scheduling-modal',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './scheduling-modal.component.html',
  styleUrls: ['./scheduling-modal.component.sass']
})
export class SchedulingModalComponent implements OnInit {

  selectedRowIndex: Number | undefined;

  displayedColumns: string[] = ['descricao', 'data', 'duracao', 'capacidade', 'buttons'];
  dataSourceCult: MatTableDataSource<any> = new MatTableDataSource<any>();

  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

  ngAfterViewInit() {
    this.dataSourceCult.paginator = this.paginator? this.paginator: null;
  }

  constructor(
    public dialogRef: MatDialogRef<SchedulingModalComponent>, 
    private apiCaller: ApiCallerService,
    @Inject(MAT_DIALOG_DATA) public data: any) {}

  ngOnInit(): void {
    this.apiCaller.carregarListadeCultosPorInstituicao(this.data.id).subscribe(
      response => {
        this.dataSourceCult.data = response;
      },
      error => {
        this.dataSourceCult.data = [];
      })

  }

  formatarDataParaExibicao(backendData: String) {
    let data = backendData.split('T')[0];
    return data.substring(8, 10) + '/' + data.substring(5,7) + '/' + data.substring(0,4) + ' ' + backendData.substring(11, 19);
  }

  scheduleItem(row: any) {

    Swal.fire({
      title: 'Confirmar agendamento?',
      html: `
        <p>${row.descricao}</p>
        <p>${this.formatarDataParaExibicao(row.data)}</p>
      `,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Confirmar',
      cancelButtonText: 'Cancelar',
      reverseButtons: false
    }).then((result) => {
      if (result.isConfirmed) {

        let infoAgendamento = {
          idCulto: row.id,
          dataAgendamento: new Date()
        }

        this.apiCaller.inserirAgendamento(infoAgendamento).subscribe(
          response => {
            Swal.fire(
              'Feito!',
              'Agendamento realizado.',
              'success'
            )
          }, 
          error => {
            Swal.fire(
              'Ops!',
              'Você já está cadastrado neste culto.',
              'error'
            )
          }
        )
      }
      
    })

  }

  scheduleCancel(row: any) {

    Swal.fire({
      title: 'Confirmar cancelamento?',
      html: ``,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Confirmar',
      cancelButtonText: 'Cancelar',
      reverseButtons: false
    }).then((result) => {
      
      if (result.isConfirmed) {
        Swal.fire(
          'Feito!',
          'Agendamento cancelado.',
          'success'
        )
      }

    })

  }
  
  onNoClick(): void {
    this.dialogRef.close();
  }

}
