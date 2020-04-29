import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'front';
  persons: any = [];

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.http.get('/api/persons').toPromise()
      .then(response => this.persons = response);
  }
}
