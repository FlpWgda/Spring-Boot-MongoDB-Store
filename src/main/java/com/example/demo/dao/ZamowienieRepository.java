package com.example.demo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entity.Uzytkownik;
import com.example.demo.entity.Zamowienie;

public interface ZamowienieRepository extends MongoRepository<Zamowienie, String>{
	
	Zamowienie findByUzytkownikAndSealed(Uzytkownik uzytkownik, boolean sealed);
	List<Zamowienie> findByUzytkownik(Uzytkownik uzytkownik);
	Zamowienie findByIdZ(String id);

}
