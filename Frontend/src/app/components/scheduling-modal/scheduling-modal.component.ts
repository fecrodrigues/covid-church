import { ChangeDetectionStrategy, Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

import { ApiCallerService } from './../../services/api-caller.service';

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
    this.apiCaller.carregarListadeCultosPorInstituicao(this.data.id).subscribe(response => {
      console.log(response, 'response')
      this.dataSourceCult.data = response.cultos;
    })

  }

  scheduleItem(row: any) {
    console.log(row, 'row')
  }

  scheduleCancel() {

  }
  
  onNoClick(): void {
    this.dialogRef.close();
  }

}
