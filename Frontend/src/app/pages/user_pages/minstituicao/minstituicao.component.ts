import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ApiCallerService } from 'src/app/services/api-caller.service';

@Component({
  selector: 'app-minstituicao',
  templateUrl: './minstituicao.component.html',
  styleUrls: ['./minstituicao.component.sass']
})
export class MinstituicaoComponent implements AfterViewInit {
  
  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;
  selectedRowIndex: Number | undefined;

  constructor(private modalControl: MatDialog, private apiCaller: ApiCallerService) { }

  displayedColumns: string[] = ['nome', 'endereco', 'capacidade', 'buttons'];
  dataSource: MatTableDataSource<any> = new MatTableDataSource<any>();

  ngOnInit() {
    this.apiCaller.carregarListaInstituicoes().subscribe(response => {
      this.dataSource.data = response.instituicoes
    })
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator? this.paginator: null;
  }

  openSchedulingModal(row: any) {
    console.log(row, 'aushdas')
    /*this.selectedRowIndex = row.id;
    this.schedulingModal.open(SchedulingModalComponent, {
      data: row
    });*/
  }
  
}
