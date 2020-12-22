import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { ApiCallerService } from './../../services/api-caller.service';
import Swal from 'sweetalert2';

import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent implements OnInit {

  @Input() error: String | null = null;
  @Output() submitEM = new EventEmitter();

  form: FormGroup = new FormGroup({
    userName: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  constructor(private router: Router, private apiCaller: ApiCallerService) { }

  ngOnInit(): void {
  }

  submit(event: any) {
    event.preventDefault();

    if (this.form.valid) {

      /*localStorage.setItem('token', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjcGYiOiIxMjM0NTY3ODkxMiJ9.Nckx1yQvv6fbNOPiZKNljKEJX5IPAEmVU_bzErHNBIw');
      this.router.navigate([''])*/

      this.apiCaller.efetuarLogin(this.form.getRawValue()).subscribe(
        response => {
          localStorage.setItem('token', (<any>response).token);
          this.router.navigate([''])
        }, 
        error => {
          Swal.fire(
            'Ops!',
            'Login e/ou senha incorretos',
            'error'
          )
        }
      )
    }
  }
  
}
