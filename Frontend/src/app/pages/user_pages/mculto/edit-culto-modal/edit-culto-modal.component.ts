import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { SchedulingModalComponent } from 'src/app/components/scheduling-modal/scheduling-modal.component';
import { ApiCallerService } from 'src/app/services/api-caller.service';

@Component({
  selector: 'app-edit-culto-modal',
  templateUrl: './edit-culto-modal.component.html',
  styleUrls: ['./edit-culto-modal.component.sass']
})
export class EditCultoModalComponent implements OnInit {

  dateMask = [/\d/, /\d/, '/', /\d/, /\d/, '/', /\d/, /\d/, /\d/, /\d/]

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
    this.form.controls['id'].setValue(this.data.id);
    this.form.controls['idInstituicao'].setValue(this.data.idInstituicao);
    this.form.controls['data'].setValue(this.data.data);
    this.form.controls['descricao'].setValue(this.data.descricao);
    this.form.controls['duracao'].setValue(this.data.duracao);
    this.form.controls['capacidade'].setValue(this.data.capacidade);
  }
  
  onNoClick(): void {
    this.dialogRef.close();
  }

  updateCultoData(event: any) {
    event.preventDefault();

    if(this.form.valid) {
      console.log(this.form.getRawValue())
    }
  }

}
