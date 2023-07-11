package com.apper.theblogservice.payload;

import lombok.Data;

import java.util.Date;

@Data
public class CreateBloggerResponse {
    private String id;
    private Date date_registration;
}
