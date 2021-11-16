package com.woods.tcc.services;

import java.util.List;
import java.util.Optional;

import com.woods.tcc.model.Chat;
import com.woods.tcc.repositories.ChatRepository;
import com.woods.tcc.services.exceptions.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
  @Autowired
  private ChatRepository chatRepository;

  public List<Chat> findAll() {
    return this.chatRepository.findAll();
  }

  public Chat findById(Long id) {
    Optional<Chat> service = this.chatRepository.findById(id);
    return service.orElseThrow();
  }

  public Chat createChat(Chat entity){
    return this.chatRepository.save(entity);
  }

  public void deleteChat (long id){
    try {
      this.chatRepository.deleteById(id);
    } catch(EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(id);
    }
  }

  public Chat updateChat (Long id, Chat obj){
    try {
      Chat entity = this.chatRepository.getById(id);
      // updateData(entity, obj);
      return this.chatRepository.save(entity);
    } catch (EntityNotFoundException e) {
      throw new EntityNotFoundException(id);
    }
  }

  // public void updateData(Chat entity, Chat obj) {
  //   entity.setStreet(obj.getStreet());
  //   entity.setNumber(obj.getNumber());
  //   entity.setComplement(obj.getComplement());
  //   entity.setDistrict(obj.getDistrict());
  //   entity.setCity(obj.getCity());
  //   entity.setState(obj.getState());
  // }
}
