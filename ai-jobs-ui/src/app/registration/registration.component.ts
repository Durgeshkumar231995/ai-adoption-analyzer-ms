import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { first } from 'rxjs';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {

  submitted: Boolean = false;
 // registrationForm: FormGroup;

  constructor(private formBuilder: FormBuilder, private authService: AuthService,private router: Router) {
    
  }

  registrationForm = this.formBuilder.group({
    firstName: ['', [Validators.required]],
    lastName: ['', [Validators.required]],
    username: ['', [Validators.required, Validators.minLength(3)]],
    password: ['', [Validators.required, Validators.minLength(6)]],
    email: ['', [Validators.required, Validators.email]],
    mobileNumber: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
    country: ['', [Validators.required]],
    dateOfBirth: ['', [Validators.required]],
  });


  get f() { return this.registrationForm.controls; }

  onSubmit(): void {
    this.submitted = true;
    if (this.registrationForm.invalid) {
      return;
    }
    this.submitted = false;
    this.authService.saveUser(this.registrationForm.value)
      .pipe(first())
      .subscribe(
        data => {
          console.warn('Your registration has been submitted');
          this.router.navigateByUrl('/login');
        },
        error => {
          console.error("not created");
        });
    this.registrationForm.reset();
  }


}
