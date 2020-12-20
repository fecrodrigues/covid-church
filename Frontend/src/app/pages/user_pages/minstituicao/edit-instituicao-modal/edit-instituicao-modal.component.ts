import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { SchedulingModalComponent } from 'src/app/components/scheduling-modal/scheduling-modal.component';
import { ApiCallerService } from 'src/app/services/api-caller.service';

@Component({
  selector: 'app-instituicao-culto-modal',
  templateUrl: './edit-instituicao-modal.component.html',
  styleUrls: ['./edit-instituicao-modal.component.sass']
})
export class EditInstituicaoModalComponent implements OnInit {

  dateMask = [/\d/, /\d/, '/', /\d/, /\d/, '/', /\d/, /\d/, /\d/, /\d/]

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
    this.form.controls['id'].setValue(this.data.id);
    this.form.controls['nome'].setValue(this.data.nome);
    this.form.controls['endereco'].setValue(this.data.endereco);
    this.form.controls['capacidade'].setValue(this.data.capacidade);
  }
  
  onNoClick(): void {
    this.dialogRef.close();
  }

  updateInstituicaoData(event: any) {
    event.preventDefault();
    
    if(this.form.valid) {
      console.log(this.form.getRawValue(), 'uahsd')
    }
  }

}
