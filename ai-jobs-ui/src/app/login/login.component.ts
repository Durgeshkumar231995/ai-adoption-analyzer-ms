import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import {  Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor( private router: Router,private authService: AuthService) {
    // if (this.authService.currentUserValue) {
    //   this.router.navigate(['/']);
    // }
  }
  
  ngOnInit() {
    
  }
  onLogin() {
    // this.authService.login(this.username, this.password)
   
    // .subscribe(
    //   data => {
    //     console.log(data)
    //     this.authService.saveToken(data);
    //     this.router.navigateByUrl("/bookmark");
    //   },
    //   error => {
    //     console.error("not authenticated");
    //   });


    this.authService.login(this.username, this.password).subscribe({
      next:(response) =>{
        //console.log("************",localStorage.setItem('token', response.token))
        this.authService.saveToken(response);
        console.log('Token stored:', response.token);
        this.router.navigate(['bookmark']); 
      },
      error:(error)=>{

        console.log('Invalid credentials',error.message);
      }
      
    }
      // (response) => {
      //   console.log("************",response)
      //   localStorage.setItem('token', response.token); 
      //   console.log("*****1111*******",response)
      //   this.router.navigate(['bookmark']); 
      //   //this.router.navigateByUrl('/bookmark');
      // },
      // (error) => {
      //   this.errorMessage = 'Invalid credentials';
      // }
    );
  }
  
  onGoToPage2() {
    this.router.navigate(['/register']);
  }

}



