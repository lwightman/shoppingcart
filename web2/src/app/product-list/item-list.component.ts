import {Component} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {CartService } from '../cart.service';

@Component({
    selector: 'app-product-list',
    templateUrl: './item-list.component.html',
    styleUrls: ['./item-list.component.css']
})

export class ItemListComponent {
    private $scope: any;
    items: Object;

    constructor(private httpClient: HttpClient,
                private cartService: CartService) {
      this.getItems().subscribe((response)=>{
        this.items = response.body;
      });
    }

    addToCart(item) {
        this.cartService.addToCart(item);
    }

    public getItems() {
        return this.httpClient.get(`/item/list`, {observe: 'response', responseType: 'json'});
    }
}


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/
