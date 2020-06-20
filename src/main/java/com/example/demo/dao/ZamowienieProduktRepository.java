package com.example.demo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.entity.Produkt;
import com.example.demo.entity.ZamowienieProdukt;

public interface ZamowienieProduktRepository  extends MongoRepository<ZamowienieProdukt, String> {
	
	//List<ZamowienieProdukt> findByIdZamowieniaAndProdukt(String idZamowienia, Produkt produkt);

}
