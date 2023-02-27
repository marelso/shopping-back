package com.example.shopping.domain.requests;

import com.example.shopping.domain.Category;
import com.example.shopping.domain.Coupon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OfferRequest {
    private Integer id;
    private String title;
    private Double price;
    private String description;
    private Date deletedAt;
    private Coupon coupon;
    private List<Category> categories;
}