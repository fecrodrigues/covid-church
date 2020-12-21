import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import Swal from 'sweetalert2';
import { SchedulingModalComponent } from './../../components/scheduling-modal/scheduling-modal.component';

import { ApiCallerService } from './../../services/api-caller.service';

@Component({
  selector: 'app-instituicao',
  templateUrl: './instituicao.component.html',
  styleUrls: ['./instituicao.component.sass']
})
export class InstituicaoComponent implements AfterViewInit, OnInit {

  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;
  selectedRowIndex: Number | undefined;

  constructor(private schedulingModal: MatDialog, private apiCaller: ApiCallerService) { }

  displayedColumns: string[] = ['nome', 'endereco', 'capacidade'];
  dataSource: MatTableDataSource<any> = new MatTableDataSource<any>();

  ngOnInit() {
    this.apiCaller.carregarListaInstituicoes().subscribe(
      (response) => {
        this.dataSource.data = response
      },
      (error) => {
        console.log(error, 'err')
      }
    )
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator? this.paginator: null;
  }

  openSchedulingModal(row: any) {
    console.log(row, 'aushdas')
    this.selectedRowIndex = row.id;
    this.schedulingModal.open(SchedulingModalComponent, {
      data: row
    });
  }

}
