package com.example.demo.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Kategoria {
		
	/**@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idKat")
	private int idKat;**/
	
	@Id
	private String nazwaKat;
	
	private List<Produkt> produkty;
	
	/**public int getIdKat() {
		return idKat;
	}

	public void setIdKat(int idKat) {
		this.idKat = idKat;
	}**/

	public String getNazwaKat() {
		return nazwaKat;
	}

	public void setNazwaKat(String nazwaKat) {
		this.nazwaKat = nazwaKat;
	}

	public List<Produkt> getProdukty() {
		return produkty;
	}

	public void setProdukty(List<Produkt> produkty) {
		this.produkty = produkty;
	}
	@Override
	public String toString() {
		return getNazwaKat();
	}
	

}
