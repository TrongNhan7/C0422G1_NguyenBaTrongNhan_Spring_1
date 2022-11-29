import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {News} from '../model/news/news';
import {Observable} from "rxjs";
import {TokenStorageService} from "./token-storage.service";

@Injectable({
  providedIn: 'root'
})
export class NewsService {
  private URL_NEWS = 'http://localhost:8080/api/public/news';
  token = '';
  httpOptions: any;
  constructor(private http: HttpClient, private  jwtService: TokenStorageService) {
    this.token = jwtService.getJwt();
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Bearer ' + this.token
      })
    };
  }

  create(news: News) {
    return this.http.post('http://localhost:8080/api/employee/news/create', news,this.httpOptions);
  }

  getAllNews(page: number, firstDate: string, lastDate: string, searchTitle: string): Observable<News[]> {
    // @ts-ignore
    return this.http.get<News[]>(this.URL_NEWS + '/list-news?page=' + page + '&firstDate=' + firstDate + '&lastDate=' + lastDate
      + '&titleSearch=' + searchTitle,this.httpOptions);
  }

  searchDistance(searchFirstDate: string, searchLastDate: string): Observable<News[]> {
    // @ts-ignore
    return this.http.get<News[]>(this.URL_NEWS + '/list-news?firstDate=' + searchFirstDate + '&lastDate=' + searchLastDate,this.httpOptions);
  }

  searchTheLast(searchTitle: string): Observable<News[]> {
    // @ts-ignore
    return this.http.get<News[]>(this.URL_NEWS + '/list-news?title=' + searchTitle,this.httpOptions);
  }

  deleteNews(id: number): Observable<News> {
    // @ts-ignore
    return this.http.patch<News>('http://localhost:8080/api/employee/news/delete/' + id,'',this.httpOptions);
  }
}
