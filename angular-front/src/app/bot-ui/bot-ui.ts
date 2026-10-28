import {Component, inject} from '@angular/core';
import {LoadingService} from '../services/loading';
import {FormsModule} from '@angular/forms';
import {HttpClient, HttpDownloadProgressEvent, HttpEventType} from '@angular/common/http';
import {map, Observable} from 'rxjs';
import {MarkdownComponent} from 'ngx-markdown';
import {AsyncPipe} from '@angular/common';

@Component({
  standalone: true,
  imports: [
    AsyncPipe,
    FormsModule,
    MarkdownComponent
  ],
  selector: 'app-bot-ui',
  styleUrl: './bot-ui.css',
  templateUrl: './bot-ui.html',
})
export class BotUi {
  public loadingService = inject(LoadingService);
  query: any;
  http = inject(HttpClient);
  response$!: Observable<any>
  askAgent(){
    this.response$ = this.http.get('http://localhost:5555/EBANK-BOT/chat?query=' + this.query,
      {responseType: 'text'})
  }
  askAgentStream(){
    this.response$ = this.http.get('http://localhost:5555/EBANK-BOT/chatStream?query=' + this.query,
      {responseType: 'text', observe:'events', reportDownloadProgress: true})
      .pipe(map(event => {
        switch (event.type) {
          case HttpEventType.Sent:
            return {'type' : 'Sent'};
          case HttpEventType.DownloadProgress:
            return {type : "Response", content : (event as HttpDownloadProgressEvent).partialText};
          case HttpEventType.Response:
            return {type : "Response", content : event.body};
          default:
            return {type: 'Other', data: event};
        }
      }))
  }
}
