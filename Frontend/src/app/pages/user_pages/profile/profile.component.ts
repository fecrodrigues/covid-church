import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.sass']
})
export class ProfileComponent implements OnInit {

  form!: FormGroup;
  formGroup = {
    cpf: new FormControl({value: '43256789090', disabled: true }, [Validators.required]),
    nome: new FormControl('', [Validators.required]),
    sobrenome: new FormControl(''),
    dataNascimento: new FormControl('', [Validators.required]),
    login: new FormControl(''),
    senha: new FormControl('')
  }

  constructor(fb: FormBuilder) { 
    this.form = new FormGroup(this.formGroup); 
  }

  ngOnInit(): void {
  }

  updateUserData(event: any) {
     event.preventDefault;

     console.log(this.form.getRawValue());
  }

}
