import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.sass']
})
export class CadastroComponent implements OnInit {

  form!: FormGroup;
  formGroup = {
    cpf: new FormControl('', [Validators.required]),
    nome: new FormControl('', [Validators.required]),
    sobrenome: new FormControl(''),
    dataNascimento: new FormControl('', [Validators.required]),
    login: new FormControl('', [Validators.required]),
    senha: new FormControl('', [Validators.required])
  }

  constructor(fb: FormBuilder) { 
    this.form = new FormGroup(this.formGroup); 
  }

  ngOnInit() {}

  saveUserData(event: any) {
    event.preventDefault;

    console.log(this.form.getRawValue());
 }

}
