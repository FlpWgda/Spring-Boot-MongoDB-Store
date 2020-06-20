package com.example.demo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.Produkt;
import com.example.demo.entity.Uzytkownik;

public interface ProduktRepository extends MongoRepository<Produkt, String>{
	
	public Produkt findByIdP(String id);
	public List<Produkt> findByAvailable(boolean available);

}
