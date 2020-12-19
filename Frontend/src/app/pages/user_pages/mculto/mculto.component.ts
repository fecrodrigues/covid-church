import { AfterViewInit, Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ApiCallerService } from 'src/app/services/api-caller.service';

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
    private apiCaller: ApiCallerService) {

    }

  ngOnInit(): void {
    this.apiCaller.carregarListadeCultosPorInstituicao(this.form.controls.instituicao.value).subscribe(response => {
      console.log(response, 'response')
      this.dataSourceCult.data = response.cultos;
    })

  }
  
}
