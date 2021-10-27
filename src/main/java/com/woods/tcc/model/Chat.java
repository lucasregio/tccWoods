package com.woods.tcc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Chat implements Serializable{

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false, updatable = false)
  private Long id;
  @ManyToOne
  @JoinColumn(name = "client_id", nullable=false)
  private Client clientChat;
  @ManyToOne
  @JoinColumn(name = "provider_id", nullable=false)
  private Provider provider;
  @OneToMany(mappedBy="chat")
  private List<Message> listMessages;
  @OneToOne
  @JoinColumn(name = "budget_id", referencedColumnName = "id")
  private Budget budget;
}
