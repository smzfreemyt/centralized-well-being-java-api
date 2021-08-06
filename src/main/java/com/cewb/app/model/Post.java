package com.cewb.app.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "posts")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String title;

    private String body;
    
    @CreatedDate
    private Date date_created;

}
