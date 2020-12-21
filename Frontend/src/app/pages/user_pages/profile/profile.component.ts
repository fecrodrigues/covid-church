import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';

import { ApiCallerService } from './../../../services/api-caller.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.sass']
})
export class ProfileComponent implements OnInit {

  form!: FormGroup;
  formGroup = {
    cpf: new FormControl({value: '', disabled: true }, [Validators.required]),
    nome: new FormControl('', [Validators.required]),
    sobrenome: new FormControl(''),
    dataNascimento: new FormControl('', [Validators.required]),
    login: new FormControl(''),
    senha: new FormControl('')
  }

  constructor(private apiCaller: ApiCallerService) { 
    this.form = new FormGroup(this.formGroup); 
  }

  ngOnInit(): void {
    this.carregarUsuario();
  }

  carregarUsuario() {
    this.apiCaller.carregarUsuario().subscribe(
      response => {
        this.form.controls['cpf'].setValue(response.cpf);
        this.form.controls['nome'].setValue(response.nome);
        this.form.controls['sobrenome'].setValue(response.sobrenome);
        this.form.controls['dataNascimento'].setValue(this.formatarDataParaExibicao(response.dataNascimento));
      },
      error => {
        Swal.fire(
          'Ops!', 
          'Não foi possível carregar os dados pessoais', 
          'error'
        );
      }
    )
  }

  formatarDataParaExibicao(backendData: String) {
    let data = backendData.split('T')[0];
    return data.substring(8, 10) + '' + data.substring(5,7) + '' + data.substring(0,4)
  }

  formatDateToSend(date: String) {
    return date.substring(4, 8) + '-' + date.substring(2,4) + '-' + date.substring(0,2);
   }

  updateUserData(event: any) {
     event.preventDefault;

     if(this.form.valid) {
      
      let formValues = this.form.getRawValue();
      formValues.dataNascimento  = this.formatDateToSend(formValues.dataNascimento);

       this.apiCaller.editarUsuario(formValues).subscribe(
        (response) => {
          Swal.fire(
            'Tudo Certo!', 
            'Informações atualizadas com sucesso', 
            'success'
          ).then(() => {
            this.carregarUsuario();
          })
        },
        (error) => {
          Swal.fire(
            'Ops!', 
            'Não foi possível atualizar os dados pessoais', 
            'error'
          );
        }
      )

     }
  }

}
