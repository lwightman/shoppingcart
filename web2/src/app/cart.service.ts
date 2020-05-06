import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams, HttpResponse} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class CartService {
    cartItems: Object = [];

    constructor(private httpClient: HttpClient) {

    }

    addToCart(item) {
        const params = new HttpParams().set('jsonString', JSON.stringify(item));
        return this.httpClient.get(`/item/add`,
            {params},
        )
            .subscribe(
                val => {
                    console.log("addToCart call successful value returned in body",  val);
                },
                response => {
                    console.log("addToCart call in error", response);
                },
                () => {
                    console.log("The addToCart observable is now completed.");
                }
            );
    }

    getCartItems() {
        return this.httpClient.get(`/cart/getItems`)
    }

    clear() {
        this.httpClient.get(`/cart/clear`)
            .subscribe(
                val => {
                    console.log("clear call successful value returned in body",  val);
                },
                response => {
                    console.log("clear call in error", response);
                },
                () => {
                    console.log("The clear observable is now completed.");
                }
            );
        this.cartItems = null;

        return this.cartItems;
    }

    update(cartItems: any) {
        const params = new HttpParams().set('jsonString', JSON.stringify(cartItems));
        this.cartItems = this.httpClient.get(`/cart/update`,
            {params},
        )
            .subscribe(
            val => {
                console.log("update call successful value returned in body",  val);
            },
            response => {
                console.log("update call in error", response);
            },
            () => {
                console.log("The update observable is now completed.");
            }
        );
    }
}
