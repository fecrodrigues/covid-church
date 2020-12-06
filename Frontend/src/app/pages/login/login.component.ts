import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.sass']
})
export class LoginComponent implements OnInit {

  @Input() error: String | null = null;
  @Output() submitEM = new EventEmitter();

  form: FormGroup = new FormGroup({
    login: new FormControl('', [Validators.required]),
    senha: new FormControl('', [Validators.required]),
  });

  constructor() { }

  ngOnInit(): void {
  }

  submit() {
    if (this.form.valid) {
      this.submitEM.emit(this.form.value);
    }
  }
  
}
