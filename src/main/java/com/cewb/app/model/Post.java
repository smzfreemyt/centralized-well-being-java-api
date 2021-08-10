package com.cewb.app.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
<<<<<<< HEAD
=======
import javax.validation.constraints.NotEmpty;
>>>>>>> develop
import java.sql.Date;

@Entity
@Table(name = "posts")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column(name = "title")
    @NotEmpty(message = "Title must not be empty")
    private String title;

    @Column(name = "body")
    @NotEmpty(message = "Body must not be empty")
    private String body;
    
    @CreatedDate
    private Date date_created;

}
