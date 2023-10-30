package com.example.Cart.EntityCreateRequest;


import com.example.Cart.Entity.Cart;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest
{
    private String description;

    @NotBlank
    private String title;

    @NotBlank
    private int quantity;

    public Cart to(CartRequest cartRequest)
    {
        return  Cart.builder().description(this.getDescription()).title(this.getTitle()).quantity(this.getQuantity()).build();
    }
}
