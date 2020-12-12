import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';

import { faUserCircle, faSignOutAlt, faCalendarDay, faChurch, faUser, faUsers, faBars } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent {

  faUserCircle = faUserCircle;
  faSignOutAlt = faSignOutAlt;
  faCalendarDay = faCalendarDay;
  faChurch = faChurch;
  faUser = faUser;
  faUsers = faUsers;
  faBars = faBars;

  isAuthenticated: Boolean = true;

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver) {}

  logout() {

  }

}
