import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import {provideMarkdown} from 'ngx-markdown';
import {provideHttpClient, withInterceptors, withInterceptorsFromDi} from '@angular/common/http';
import {loadingInterceptorInterceptor} from './interceptors/loading-interceptor-interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),
    provideMarkdown(),
    provideHttpClient(
      withInterceptors([
        loadingInterceptorInterceptor
      ])
    )
  ]
};
