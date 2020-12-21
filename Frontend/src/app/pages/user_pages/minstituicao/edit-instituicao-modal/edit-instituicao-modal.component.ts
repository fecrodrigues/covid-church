import { Component, Inject, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { SchedulingModalComponent } from 'src/app/components/scheduling-modal/scheduling-modal.component';
import { ApiCallerService } from 'src/app/services/api-caller.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-instituicao-culto-modal',
  templateUrl: './edit-instituicao-modal.component.html',
  styleUrls: ['./edit-instituicao-modal.component.sass']
})
export class EditInstituicaoModalComponent implements OnInit {

  operation = '';

  form!: FormGroup;
  formGroup = {
    id: new FormControl(''),
    nome: new FormControl('', [Validators.required]),
    endereco: new FormControl('', [Validators.required]),
    capacidade: new FormControl('', [Validators.required])
  }

  constructor(
    public dialogRef: MatDialogRef<SchedulingModalComponent>, 
    private apiCaller: ApiCallerService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.form = new FormGroup(this.formGroup); 
    }

  ngOnInit(): void {
    if(this.data) {
      this.operation = 'Editar';
      this.form.controls['id'].setValue(this.data.id);
      this.form.controls['nome'].setValue(this.data.nome);
      this.form.controls['endereco'].setValue(this.data.endereco);
      this.form.controls['capacidade'].setValue(this.data.capacidade);
    } else {
      this.operation = 'Inserir';
    }
  }
  
  onNoClick(): void {
    this.dialogRef.close();
  }

  updateInstituicaoData(event: any) {
    event.preventDefault();
    
    if(this.form.valid) {

      if(this.data) {

      } else {
        this.apiCaller.inserirInstituicao(this.form.getRawValue()).subscribe(
          response => {
            this.dialogRef.close('success');

            Swal.fire(
              'Sucesso!',
              'Instituição criada com sucesso!',
              'success'
            )
          }, 
          error => {

            Swal.fire(
              'Ops!',
              'Não foi possivel inserir a instituição!',
              'error'
            )

          }
        )
      }
    }
  }

}
