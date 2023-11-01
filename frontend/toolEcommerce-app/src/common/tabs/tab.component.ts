import { CommonModule } from '@angular/common';
import { Component, ContentChild, Input } from '@angular/core';
import { SearchFormComponent } from 'src/app/search-form/search-form.component';

@Component({
  selector: 'app-tab',
  standalone: true,
  templateUrl: './tab.component.html',
  imports: [CommonModule],
})
export class TabComponent {
  @ContentChild(SearchFormComponent) searchForm: SearchFormComponent | undefined;
  @Input('tabTitle') title: string | undefined;
  @Input() active = false;
}
