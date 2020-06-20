package com.example.demo.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ZamowienieProdukt implements Serializable {
	
	@Id
	private String id_produkt_zamowienie;
	 
	private Produkt id_produktu;
	
	private int ilosc;
	public Produkt getId_produktu() {
		return id_produktu;
	}
	public void setId_produktu(Produkt id_produktu) {
		this.id_produktu = id_produktu;
	}
	public int getIlosc() {
		return ilosc;
	}
	public void setIlosc(int ilosc) {
		this.ilosc = ilosc;
	}
	public String getId_produkt_zamowienie() {
		return id_produkt_zamowienie;
	}
	public void setId_produkt_zamowienie(String id_produkt_zamowienie) {
		this.id_produkt_zamowienie = id_produkt_zamowienie;
	}
	
}
