package com.mssale.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Sales {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDateTime dataHora;
	private Integer productId;
	private String productName;
	private Integer personId;
	private String personName;
	private Integer quantity;

}
