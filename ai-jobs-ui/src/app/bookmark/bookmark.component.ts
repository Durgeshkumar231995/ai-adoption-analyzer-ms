import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { BehaviorSubject } from 'rxjs';
import { User } from '../model/User';
import { AIJob } from '../model/AIJob';

@Component({
  selector: 'app-bookmark',
  templateUrl: './bookmark.component.html',
  styleUrls: ['./bookmark.component.css']
})
export class BookmarkComponent implements OnInit{
  aIJobData: AIJob[]=[]; // Variable to store the fetched data
  //items : AIJob[]=[];
  items: any[] = []; 
  paginatedItems :AIJob[]=[]; // Array for items to display on the current page
  currentPage = 1;
  itemsPerPage = 55;
  error: string | null = null; // Variable to store error messages
  constructor(private authService: AuthService) {
   
  }
  ngOnInit() {
    this.authService.fetchAllAIJOBData().subscribe(
      (response) => {
        this.aIJobData = response; 
        this.items=response;// Store the fetched data
        this.updatePaginatedItems()
      },
      (error) => {
        this.error = 'Error fetching data'; // Handle error
        console.error(error);
      }
    );
  }
  
  //uuuuuuu

  bookmark(itemId: string)  {
    const username= this.authService.getUsername();
    console.log("username",username)
    const itemToBookmark = this.aIJobData.find(item => item.id === itemId);
    console.log("itemToBookmark",itemToBookmark)
    if (itemToBookmark) {
      // Prepare the object to send, including the ID
      console.log("inside if")
      const bookmarkData = {
        id: itemToBookmark.id,
        AI_Adoption_Level: itemToBookmark.AI_Adoption_Level,
        Automation_Risk: itemToBookmark.Automation_Risk,
        Company_Size: itemToBookmark.Company_Size,
        Industry: itemToBookmark.Industry,
        Job_Growth_Projection: itemToBookmark.Job_Growth_Projection,
        Job_Title: itemToBookmark.Job_Title,
        Location: itemToBookmark.Location,
        Remote_Friendly: itemToBookmark.Remote_Friendly,
        Required_Skills: itemToBookmark.Required_Skills,
        Salary_USD: itemToBookmark.Salary_USD
      };
      console.log("bookmark-Data",bookmarkData)
      this.authService.bookmarkItem(username,bookmarkData).subscribe(response => {
        console.log('Item bookmarked', response);
        // Handle success, e.g., show a message or update UI
      }, error => {
        console.error('Error bookmarking item', error);
      });
    }
  }
  //////


  updatePaginatedItems() {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    this.paginatedItems = this.aIJobData.slice(startIndex, startIndex + this.itemsPerPage);
  }

  nextPage() {
    if (this.currentPage * this.itemsPerPage < this.items.length) {
      this.currentPage++;
      this.updatePaginatedItems();
    }
  }

  previousPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.updatePaginatedItems();
    }
  }

  goToPage(page: number) {
    this.currentPage = page;
    this.updatePaginatedItems();
  }

  get totalPages(): number {
    return Math.ceil(this.items.length / this.itemsPerPage);
  }

  ///
  
}
