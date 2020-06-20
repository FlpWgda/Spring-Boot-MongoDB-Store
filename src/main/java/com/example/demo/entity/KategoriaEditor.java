package com.example.demo.entity;

import java.util.List;
import java.beans.PropertyEditorSupport;
import java.util.Arrays;

import com.example.demo.dao.KategoriaRepository;

public class KategoriaEditor extends PropertyEditorSupport{

	private final KategoriaRepository groupService;

    public KategoriaEditor(KategoriaRepository groupService){
        this.groupService= groupService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
    	List<String> lista = Arrays.asList(text.split(","));
      List<Kategoria> kategorie = groupService.findByNazwaKatIn(lista);
      
      setValue(kategorie);
    }
}
