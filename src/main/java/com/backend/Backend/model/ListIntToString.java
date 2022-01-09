package com.backend.Backend.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

@Converter
public class ListIntToString implements AttributeConverter<List<Integer>, String> {

    private static final String SEPARATOR = "-";
    @Override
    public String convertToDatabaseColumn(List<Integer> integers) {
        String result = "";
        for (int hora: integers){
            result = Integer.toString(hora) + SEPARATOR;
        }
        return result;
    }

    @Override
    public List<Integer> convertToEntityAttribute(String s) {
        List<Integer> list = new ArrayList<Integer>();
        String[] parts = s.split(SEPARATOR);
        for (int i = 0; i < parts.length; i++) {
            if(parts[i] != ""){
                list.add(Integer.parseInt(parts[i]));
            }
        }
        return list;
    }

}
