package com.woods.model;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public @Data class Provider extends User implements Serializable {
    private String CNPJ;

}
