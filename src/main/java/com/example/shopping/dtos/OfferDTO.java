package com.example.shopping.dtos;

import com.example.shopping.domain.Category;
import com.example.shopping.domain.Coupon;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OfferDTO {
    private Integer id;
    private String title;
    private String description;
    private Double price;
    private Coupon coupon;
    private List<Category> categories;
    private boolean active;
}