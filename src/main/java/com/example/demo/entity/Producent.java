package com.example.demo.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Producent {
	
	
	private List<Produkt> produkt;
	
	@Id
	private String idPr;
	
	private String nazwaPr;
	
	
	public String getIdPr() {
		return idPr;
	}



	public void setIdPr(String idPr) {
		this.idPr = idPr;
	}



	public String getNazwaPr() {
		return nazwaPr;
	}



	public void setNazwaPr(String nazwaPr) {
		this.nazwaPr = nazwaPr;
	}
	

	public List<Produkt> getProdukt() {
		return produkt;
	}


	public void setProdukt(List<Produkt> produkt) {
		this.produkt = produkt;
	}

	@Override
	public String toString() {
		return getNazwaPr();
	}
	
	
}
