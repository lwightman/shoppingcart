import { Component } from '@angular/core';
import { CartService } from '../cart.service';

@Component({
    selector: 'app-cart',
    templateUrl: './cart.component.html',
    styleUrls: ['./cart.component.css']
})
export class CartComponent {
    cartItems;
    counter: any;

    constructor(private cartService: CartService) {
        this.cartService.getCartItems()
            .subscribe((response)=>{
            this.cartItems = response;
        });
    }

    update(cartItems: any){
        this.cartService.update(cartItems);
    }

    clear(){
        this.cartItems = this.cartService.clear();
    }
}
