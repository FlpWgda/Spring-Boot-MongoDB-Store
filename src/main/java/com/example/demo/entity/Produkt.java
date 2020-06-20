package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Produkt {
	
	@Id
	private String idP;
	
	@NotNull(message="pole wymagane")
	private String nazwaP;

	@Min(value=0, message="liczba sztuk musi być większa lub równa 0")
	private Integer liczbaSztuk;
	
	@NotNull(message="pole wymagane")
	private Producent producent;
	
	List<Kategoria> kategorie;
		
	List<ZamowienieProdukt> zamowienieProdukt;
	
	@NotNull
	@DecimalMin(value = "0.0")
	private BigDecimal cena;
	
	private String opis;
	
	private boolean available;
	
	public void setProducent(Producent producent) {
		this.producent = producent;
	}

	public Producent getProducent() {
		return producent;
	}


	@Override
	public String toString() {
		return "Produkt [nazwa=" + nazwaP + ", liczbaSztuk=" + liczbaSztuk + ", producent=" + producent + "]";
	}

	

	public String getIdP() {
		return idP;
	}

	public void setIdP(String idP) {
		this.idP = idP;
	}

	public String getNazwaP() {
		return nazwaP;
	}

	public void setNazwaP(String nazwaP) {
		this.nazwaP = nazwaP;
	}

	public Integer getLiczbaSztuk() {
		return liczbaSztuk;
	}

	public void setLiczbaSztuk(Integer liczbaSztuk) {
		this.liczbaSztuk = liczbaSztuk;
	}
	
	public List<Kategoria> getKategorie() {
				
		return kategorie;
	}

	/**public String getKategorie() {
		String tmp = "";
		for(Kategoria k: kategorie){
			tmp += k.toString() + "\n";
		}
		return tmp;
	}**/

	public void setKategorie(List<Kategoria> kategorie) {
		this.kategorie = kategorie;
	}

	

	public BigDecimal getCena() {
		return cena;
	}

	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	


}
