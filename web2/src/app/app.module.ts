import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {TopBarComponent} from './top-bar/top-bar.component';
import {ItemListComponent} from './product-list/item-list.component';
import {HttpClientModule} from '@angular/common/http';
import {CartComponent} from "./cart/cart.component";

@NgModule({
    imports: [
        BrowserModule,
        HttpClientModule,
        ReactiveFormsModule,
        RouterModule.forRoot([
            {path: '', component: ItemListComponent},
            {path: 'cart', component: CartComponent},
        ]),
        FormsModule
    ],
    declarations: [
        AppComponent,
        TopBarComponent,
        ItemListComponent,
        CartComponent
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/
