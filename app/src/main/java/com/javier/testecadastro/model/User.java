package com.javier.testecadastro.model;

import java.io.Serializable;

public class User implements Serializable {

    private Long id;
    private String name;
    private String dataNasc;
    private String code;
    private String map;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMap() {
        return map;
    }

    public void setmap(String map) {
        this.map = map;
    }

}
