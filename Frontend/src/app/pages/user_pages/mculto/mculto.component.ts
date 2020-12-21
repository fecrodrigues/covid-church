import { AfterViewInit, Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ApiCallerService } from 'src/app/services/api-caller.service';

import { EditCultoModalComponent } from './edit-culto-modal/edit-culto-modal.component';

import Swal from 'sweetalert2';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-mculto',
  templateUrl: './mculto.component.html',
  styleUrls: ['./mculto.component.sass']
})

export class McultoComponent implements AfterViewInit {
  
  instituicoes: any[] = [];
  instituicaoSelecionada: String | undefined;

  displayedColumns: string[] = ['descricao', 'data', 'duracao', 'capacidade', 'buttons'];
  dataSourceCult: MatTableDataSource<any> = new MatTableDataSource<any>();

  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

  ngAfterViewInit() {
    this.dataSourceCult.paginator = this.paginator? this.paginator: null;
  }

  constructor( 
    private apiCaller: ApiCallerService,
    private modal: MatDialog) { }

  ngOnInit(): void {
    this.apiCaller.carregarListaInstituicoesUsuario().subscribe(
      response => {
        this.instituicoes = response;
        
        if(response && response.length > 0) {
          this.carregarListaDeCultos({ target: { value: response[0].id } })
        }
      },
      error => {
        Swal.fire(
          'Ops!',
          'Não foi possível carregar a lista de instituições',
          'error'
        )
      }
    )
  }

  carregarListaDeCultos(event: any) {
    const instituicao = event.target.value;
    this.instituicaoSelecionada = instituicao;

    this.apiCaller.carregarListadeCultosPorInstituicao(instituicao).subscribe(
      response => {
        this.dataSourceCult.data = response.cultos;
      }, 
      error => {
        this.dataSourceCult.data = [];
      }
    )
  }

  deleteCulto(row: any) {
    
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
        Swal.fire(
          'Feito!',
          'O Culto foi excluido com sucesso.',
          'success'
        )
      }

    })
  }

  openEditModal(row?: any) {
    !row? row = { idInstituicao: this.instituicaoSelecionada }: row;

    console.log(this.instituicaoSelecionada, 'hue')
    this.modal.open(EditCultoModalComponent, {
      data: row
    });
  }
  
}
