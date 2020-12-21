import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { subscribeOn } from 'rxjs/operators';
import { SchedulingModalComponent } from 'src/app/components/scheduling-modal/scheduling-modal.component';
import { ApiCallerService } from 'src/app/services/api-caller.service';

import Swal from 'sweetalert2';

@Component({
  selector: 'app-edit-culto-modal',
  templateUrl: './edit-culto-modal.component.html',
  styleUrls: ['./edit-culto-modal.component.sass']
})
export class EditCultoModalComponent implements OnInit {

  operation = "";

  form!: FormGroup;
  formGroup = {
    id: new FormControl(''),
    idInstituicao: new FormControl(''),
    descricao: new FormControl('', [Validators.required]),
    data: new FormControl('', [Validators.required]),
    duracao: new FormControl('',  [Validators.required]),
    capacidade: new FormControl('', [Validators.required])
  }

  constructor(
    public dialogRef: MatDialogRef<SchedulingModalComponent>, 
    private apiCaller: ApiCallerService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.form = new FormGroup(this.formGroup); 
    }

  ngOnInit(): void {
    if(this.data.id) {
      this.operation = 'Atualizar';
      this.form.controls['id'].setValue(this.data.id);
      this.form.controls['idInstituicao'].setValue(this.data.idInstituicao);
      this.form.controls['data'].setValue(this.data.data);
      this.form.controls['descricao'].setValue(this.data.descricao);
      this.form.controls['duracao'].setValue(this.data.duracao);
      this.form.controls['capacidade'].setValue(this.data.capacidade);
    } else {
      this.form.controls['idInstituicao'].setValue(this.data.idInstituicao);
      this.operation = 'Inserir';
    }
  }
  
  onNoClick(): void {
    this.dialogRef.close();
  }

  formatarDataParaExibicao(backendData: String) {
    let data = backendData.split('T')[0];
    return data.substring(8, 10) + '' + data.substring(5,7) + '' + data.substring(0,4) + ' ' + backendData.substr(11, 19);
  }

  formatDateToSend(date: String) {
    return date.substring(4, 8) + '-' + date.substring(2,4) + '-' + date.substring(0,2) + 'T' + date.substring(8,10) + ":" + date.substring(10,12) + ":" + date.substring(12,14);
  }

  updateCultoData(event: any) {
    event.preventDefault();

    if(this.form.valid) {

      let formValues = this.form.getRawValue();
      formValues.data = this.formatDateToSend(formValues.data);

      if(this.data.id) {
        //edita
        console.log(formValues, 'edita')
      } else {
        //criar
        this.apiCaller.inserirCulto(formValues.idInstituicao, formValues).subscribe(
          response => {
            this.dialogRef.close('success');

            Swal.fire(
              'Tudo Certo!',
              'Culto cadastrado com sucesso!',
              'success'
            )
          },
          error => {
            Swal.fire(
              'Ops!',
              'Não foi possivel cadastrar o culto!',
              'error'
            )
          }
        )
      }
    }
  }

}
