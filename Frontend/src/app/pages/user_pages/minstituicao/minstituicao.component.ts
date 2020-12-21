import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ApiCallerService } from 'src/app/services/api-caller.service';
import { EditInstituicaoModalComponent } from './edit-instituicao-modal/edit-instituicao-modal.component';

import Swal from 'sweetalert2';

@Component({
  selector: 'app-minstituicao',
  templateUrl: './minstituicao.component.html',
  styleUrls: ['./minstituicao.component.sass']
})
export class MinstituicaoComponent implements AfterViewInit {
  
  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;
  selectedRowIndex: Number | undefined;

  constructor(private modal: MatDialog, private apiCaller: ApiCallerService) { }

  displayedColumns: string[] = ['nome', 'endereco', 'capacidade', 'buttons'];
  dataSource: MatTableDataSource<any> = new MatTableDataSource<any>();

  ngOnInit() {
    this.carregarListaInstituicoes();
  }

  carregarListaInstituicoes() {
    this.apiCaller.carregarListaInstituicoesUsuario().subscribe(
      response => {
        this.dataSource.data = response;
      },
      error => {
        this.dataSource.data = [];
      }
    )
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator? this.paginator: null;
  }

  deleteInstituicao(row: any) {
    
    Swal.fire({
      title: 'Você tem certeza?',
      text: "Você não podera voltar atrás",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sim, excluir!',
      cancelButtonText: 'Não, cancelar!',
      reverseButtons: false
    }).then((result) => {
      
      if (result.isConfirmed) {

        this.apiCaller.excluirInstituicao(row.id).subscribe(
          (response) => {
            Swal.fire(
              'Feito!',
              'A instituição foi excluida com sucesso.',
              'success'
            ).then(() => {
              this.carregarListaInstituicoes();
            })
          },
          (error) => {
            Swal.fire(
              'Ops!',
              'Não foi possível excluir a instituição.',
              'error'
            )
          }  
        )

       
      }

    })
  }

  openEditModal(row?: any) {
    let dialogModal = this.modal.open(EditInstituicaoModalComponent, {
      data: row
    });

    dialogModal.afterClosed().subscribe(result => {
      if(result === 'success') {
        this.carregarListaInstituicoes();
      }
    });

  }
  
}
