import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, catchError, map, throwError } from 'rxjs';
import { User } from './model/User';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

 

  private userBaseUrl = 'http://localhost:8081/api/ups';

  private currentUser!: BehaviorSubject<String | null>;

 

  private loggedIn = new BehaviorSubject<boolean>(false);
  currentUserStatus = this.loggedIn.asObservable();

  constructor(private httpClient: HttpClient,private router: Router) {
    this.currentUser = new BehaviorSubject<String|null>(sessionStorage.getItem("user"));
  }
 
  removeBookmark(userId: number): Observable<String> {

    const url = `${this.userBaseUrl}/deleteUserProfile/${userId}`;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'text/plain',
        'Authorization': "Bearer " + this.getToken()
      })
    };
    return this.httpClient.delete<String>(url, httpOptions);
  }

  saveToken(data: any) {
    sessionStorage.setItem("token", data.token);
  }

  public get currentUserValue(): String|null {
    this.currentUser = new BehaviorSubject<String|null>(sessionStorage.getItem("user"));
    if(this.currentUser) {
      return this.currentUser.value;
    } else {
      return null;
    }
  }


  login(username: string, password: string): Observable<any> {
    return this.httpClient.post("http://localhost:8082/api/auth/login", {"username":username,"password":password }
   ).pipe(map(resp => {
    this.currentUser.next(username);
    sessionStorage.setItem("user", username);
    this.loggedIn.next(true);
    return resp;
  }), catchError(val => this.handleError(val)));
}
  saveUser(user: any): Observable<any> {
    return this.httpClient.post(this.userBaseUrl+"/register", user);
  }

  getToken() {
    return sessionStorage.getItem("token");
  }

  handleError(error: any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // server-side error
      errorMessage = `Message: ${error.error.reason}`;
    }
    return throwError(errorMessage);
  }

  fetchAllAIJOBData(): Observable<any>{
    return this.httpClient.get<any>("http://localhost:9090/v1/AIJob/getAllRoles");
  }

  saveToBookmark(user: User): Observable<User> {
    console.log('save User');
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': "Bearer " + this.getToken()
      })
    };
    return this.httpClient.post<User>(this.userBaseUrl, user, httpOptions)
      .pipe(map(user => {
        return user;
      }), catchError(val => this.handleError(val)));
  }

  private username: string = '';
getUsername(): string {
  return this.username = sessionStorage.getItem('user') || '';
}
  bookmarkItem(username: String, body: any): Observable<any> {
    return this.httpClient.post(`http://localhost:9090/v1/AIJob/saveBookmark/${username}`, body);
  }


  logout() {
    // remove user from local storage and set current user to null
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('user');
    this.loggedIn.next(false);
    this.currentUser.next(null);
}

isLoggedIn(): boolean {
  return this.loggedIn.value; // Returns the current login status
}



}
