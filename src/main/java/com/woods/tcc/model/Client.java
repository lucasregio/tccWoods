package com.woods.tcc.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Client extends User {
    private String cpf;
    @OneToMany(mappedBy="client")
    private List<Budget> listBudgets;
}
