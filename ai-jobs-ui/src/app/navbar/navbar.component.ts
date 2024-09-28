import { Component, OnInit, inject } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { AsyncPipe, NgIf } from '@angular/common';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  private breakpointObserver = inject(BreakpointObserver);
 // private authService = inject(AuthService);
  private router = inject(Router);
  user: String|null = "";
  isLoggedIn: boolean = false; // Track login state
  username: string = ''; // Store username

  constructor(private authService: AuthService) {
    // Subscribe to the authentication status
    this.authService.currentUserStatus.subscribe(status => {
      this.isLoggedIn = status;
    });
  }
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );
  ngOnInit(): void {
    if(this.authService.currentUserValue != null){
      this.isLoggedIn = true;
      this.user = this.authService.currentUserValue;
      console.log("current user", this.user)
    } else {

    }
    
  }
  logout():void {
    this.isLoggedIn = false;
    this.authService.logout();
    this.router.navigateByUrl("login");
  }
}
