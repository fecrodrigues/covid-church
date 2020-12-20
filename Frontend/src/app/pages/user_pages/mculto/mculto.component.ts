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
  
  instituicao: FormControl = new FormControl('');

  form: FormGroup = new FormGroup({
    instituicao: new FormControl('')  
  })

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
    this.apiCaller.carregarListadeCultosPorInstituicao(this.form.controls.instituicao.value).subscribe(response => {
      console.log(response, 'response')
      this.dataSourceCult.data = response.cultos;
    })

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

  openEditModal(row: any) {
    this.modal.open(EditCultoModalComponent, {
      data: row
    });
  }
  
}
