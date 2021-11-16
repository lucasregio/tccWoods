package com.woods.tcc.services;

import java.util.List;
import java.util.Optional;

import com.woods.tcc.model.Message;
import com.woods.tcc.repositories.MessageRepository;
import com.woods.tcc.services.exceptions.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
  @Autowired
  private MessageRepository messageRepository;

  public List<Message> findAll() {
    return this.messageRepository.findAll();
  }

  public Message findById(Long id) {
    Optional<Message> service = this.messageRepository.findById(id);
    return service.orElseThrow();
  }

  public Message createMessage(Message entity){
    return this.messageRepository.save(entity);
  }

  public void deleteMessage (long id){
    try {
      this.messageRepository.deleteById(id);
    } catch(EmptyResultDataAccessException e) {
      throw new EntityNotFoundException(id);
    }
  }

  public Message updateMessage (Long id, Message obj){
    try {
      Message entity = this.messageRepository.getById(id);
      // updateData(entity, obj);
      return this.messageRepository.save(entity);
    } catch (EntityNotFoundException e) {
      throw new EntityNotFoundException(id);
    }
  }

  // public void updateData(Message entity, Message obj) {
  //   entity.setStreet(obj.getStreet());
  //   entity.setNumber(obj.getNumber());
  //   entity.setComplement(obj.getComplement());
  //   entity.setDistrict(obj.getDistrict());
  //   entity.setCity(obj.getCity());
  //   entity.setState(obj.getState());
  // }
}
