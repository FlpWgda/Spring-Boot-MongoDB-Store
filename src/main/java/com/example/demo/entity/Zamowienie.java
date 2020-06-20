package com.example.demo.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Zamowienie {
	
	@Id
	private String idZ;
	
	private boolean sealed;
	
	private Date dataZ;
	
	private Uzytkownik uzytkownik;
	
	List<ZamowienieProdukt> zamowienieProdukt;

	public String getIdZ() {
		return idZ;
	}

	public void setIdZ(String idZ) {
		this.idZ = idZ;
	}

	public boolean isSealed() {
		return sealed;
	}

	public void setSealed(boolean sealed) {
		this.sealed = sealed;
	}

	public Date getDataZ() {
		return dataZ;
	}

	public void setDataZ(Date dataZ) {
		this.dataZ = dataZ;
	}

	public Uzytkownik getUzytkownik() {
		return uzytkownik;
	}

	public void setUzytkownik(Uzytkownik uzytkownik) {
		this.uzytkownik = uzytkownik;
	}

	public List<ZamowienieProdukt> getZamowienieProdukt() {
		return zamowienieProdukt;
	}

	public void setZamowienieProdukt(List<ZamowienieProdukt> zamowienieProdukt) {
		this.zamowienieProdukt = zamowienieProdukt;
	}
	public void addToList(ZamowienieProdukt zp) {
		if(zamowienieProdukt == null) {
			zamowienieProdukt = new ArrayList<ZamowienieProdukt>();
		}
		zamowienieProdukt.add(zp);
	}
	/**@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name= "zamowienie_produkt",
			joinColumns = { @JoinColumn(name = "id_zamowienia")},
			inverseJoinColumns = { @JoinColumn(name = "id_produktu")}
		)
	List<Produkt> produkty;**/
	

}
