package com.javier.testecadastro.model;

import java.io.Serializable;

public class User implements Serializable {

    private Long id;
    private String name;
    private String dataNasc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nomeTarefa) {
        this.name = name;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }
}
