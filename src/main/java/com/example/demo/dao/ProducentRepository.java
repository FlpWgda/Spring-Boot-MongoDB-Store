package com.example.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.Producent;
import com.example.demo.entity.Uzytkownik;

public interface ProducentRepository extends MongoRepository<Producent, String>{

	Producent findByNazwaPr(String text);

}
