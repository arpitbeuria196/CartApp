package com.example.Cart.Service;

import com.example.Cart.Entity.Cart;
import com.example.Cart.EntityCreateRequest.CartRequest;
import com.example.Cart.Exception.ItemNotFoundException;
import com.example.Cart.Repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartService {

    @Autowired
    private  CartRepository cartRepository;
    //Logic For Add Item
    public Object addItem(CartRequest cartRequest)
    {
        Cart c = cartRequest.to(cartRequest);

       return cartRepository.save(c);

    }
    //Logic for update Quantity
    public ResponseEntity<Cart> upDateQuantity(long id, Integer quantity)
    {
        log.debug("Fetching Item",id);
       Optional<Cart> cart = cartRepository.findById( id);

       if(cart.isPresent())
       {
           log.info("Item Found",cart);
           Cart cart1 = cart.get();
           cart1.setQuantity(quantity);
           return  new ResponseEntity<>(cart1, HttpStatus.OK);
       }
       else
       {
           log.warn("Item not found",id);
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    //Logic for delete Item
    public void deleteItem(long id) throws ItemNotFoundException {
        Optional<Cart> cart = cartRepository.findById(id);

        if(cart.isPresent())
        {
            log.info("Item found for Delete Operation",id);
            cartRepository.deleteById(id);
        }
        else
        {
            log.warn("Item not found",id);
            throw new ItemNotFoundException("Item Not Found");
        }

    }

    //Logic for get All Items
    public List<Cart> getAllItems() throws ItemNotFoundException {
        List<Cart> cartList = cartRepository.findAll();
        if(cartList.size()!=0)
        {
            return cartList;
        }
        else
        {
            log.warn("Items are not found");
            throw new ItemNotFoundException("Items are not present");
        }

    }
}
