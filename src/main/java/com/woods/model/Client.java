package com.woods.model;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public @Data  class Client extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String CPF;


}
