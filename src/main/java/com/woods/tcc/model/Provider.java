package com.woods.tcc.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class Provider extends User{
  private String cnpj;
  private Long rating;
  @OneToMany(mappedBy="provider")
  private List<Servicing> listService;
  @OneToMany(mappedBy="provider")
  private List<Chat> listChats;
}
