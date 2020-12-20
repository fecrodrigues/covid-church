import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { ApiCallerService } from './../../services/api-caller.service';
import Swal from 'sweetalert2';

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
    userName: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  }

  constructor(fb: FormBuilder, private apiCaller: ApiCallerService, private router: Router) { 
    this.form = new FormGroup(this.formGroup); 
  }

  ngOnInit() {}

  saveUserData(event: any) {
    event.preventDefault;

    let formValues = this.form.getRawValue();
    formValues.dataNascimento  = this.formatDateToSend(formValues.dataNascimento);
    this.apiCaller.cadastrarUsuario(formValues).subscribe(
      (response) => {
        Swal.fire(
          'Sucesso',
          'Sua conta foi criada com sucesso, agora é só fazer login',
          'success'
        ).then(() => {
          this.router.navigate(['login'])
        })
      },
      (error) => {
        if(error.status === 400) {
          Swal.fire(
            'Ops',
            'CPF já cadastrado',
            'error'
          )
        } else {
          Swal.fire(
            'Ops',
            'Ocorreu um problema ao cadastrar',
            'error'
          )
        }
      }
    )
    //console.log();
 }

 formatDateToSend(date: String) {
  return date.substring(4, 8) + '-' + date.substring(2,4) + '-' + date.substring(0,2);
 }

}
