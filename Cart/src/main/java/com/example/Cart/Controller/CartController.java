package com.example.Cart.Controller;


import com.example.Cart.Entity.Cart;
import com.example.Cart.EntityCreateRequest.CartRequest;
import com.example.Cart.Exception.ItemNotFoundException;
import com.example.Cart.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController
{
    @Autowired
    private CartService cartService;

    @PostMapping("/api/cart")
    public Cart addItem(@RequestBody CartRequest cartRequest)
    {
        return (Cart) cartService.addItem(cartRequest);
    }

    @PutMapping("/api/cart/{id}/quantity/{quantity}")
    public ResponseEntity<Cart> updateQuantity(@PathVariable long id, @PathVariable int quantity)
    {
        return cartService.upDateQuantity(id,quantity);
    }

    @DeleteMapping("/api/cart/{id}")
    public void deleteItem(@PathVariable long id) throws ItemNotFoundException {
         cartService.deleteItem(id);
    }

    @GetMapping("/api/cart")
    public List<Cart> getAllItems() throws ItemNotFoundException {
        return cartService.getAllItems();
    }



}
