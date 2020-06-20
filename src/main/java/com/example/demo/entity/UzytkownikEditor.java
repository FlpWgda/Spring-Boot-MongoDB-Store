package com.example.demo.entity;

import java.beans.PropertyEditorSupport;

import com.example.demo.dao.UzytkownikRepository;

public class UzytkownikEditor extends PropertyEditorSupport{

    private final UzytkownikRepository groupService;

    public UzytkownikEditor(UzytkownikRepository groupService){
        this.groupService= groupService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
      Uzytkownik uzytkownik = groupService.findByLogin(text);
      setValue(uzytkownik);
    }

}
