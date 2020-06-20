package com.example.demo.entity;

import java.beans.PropertyEditorSupport;

import com.example.demo.dao.ProducentRepository;


public class ProducentEditor extends PropertyEditorSupport{

    private final ProducentRepository groupService;

    public ProducentEditor(ProducentRepository groupService){
        this.groupService= groupService;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
      Producent producent = groupService.findByNazwaPr(text);
      setValue(producent);
    }
}
