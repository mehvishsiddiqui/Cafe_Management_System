package com.inn.cafe.POJO;

import java.io.Serializable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;


@NamedQuery(
	    name = "Category.getAllCategory",
	    query = "select c from Category c where c.id in (select p.category.id from Product p where p.status='true')"
	)

@Data
@Entity
@Component
@DynamicUpdate
@DynamicInsert
@Table(name = "category")
public class Category implements Serializable {
	
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column (name = "id")
	private Integer id;
	
	@Column (name = "name")
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Category() {
		super();
	}
	

}
