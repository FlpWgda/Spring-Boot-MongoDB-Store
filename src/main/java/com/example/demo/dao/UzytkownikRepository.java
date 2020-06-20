package com.example.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Uzytkownik;

@Repository
public interface UzytkownikRepository extends MongoRepository<Uzytkownik, String>{
	
	Uzytkownik findByLogin(String login);

}
