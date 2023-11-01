import { AppComponent } from './app/app.component';
import { provideHttpClient } from '@angular/common/http';

import {
  bootstrapApplication,
  provideProtractorTestingSupport,
} from '@angular/platform-browser';

bootstrapApplication(AppComponent, {
  providers: [
    provideProtractorTestingSupport(),
    provideHttpClient()
  ],
}).catch((err) => console.error(err));
