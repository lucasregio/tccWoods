package com.woods.tcc.controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.woods.tcc.dto.MessageDTO;
import com.woods.tcc.model.Chat;
import com.woods.tcc.model.Message;
import com.woods.tcc.services.MessageService;
import com.woods.tcc.services.exceptions.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/message")
public class MessageController {

  @Autowired
  private MessageService messageService;

  @GetMapping
  public ResponseEntity<List<MessageDTO>> findAll(){
    List<Message> listMessage = messageService.findAll();

    if(listMessage.isEmpty())
      throw new EntityNotFoundException(0);

    List<MessageDTO> lMessageDTOs = listMessage
      .stream()
      .map( x -> new MessageDTO(x))
      .collect(Collectors.toList());

    return ResponseEntity.ok().body(lMessageDTOs);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<MessageDTO> findById(@PathVariable Long id){
    try {
      Message message = messageService.findById(id);
      MessageDTO messageDTO = new MessageDTO(message);
      return  ResponseEntity.ok().body(messageDTO);
    }catch (NoSuchElementException e) {
      throw new EntityNotFoundException(id);
    }
  }

  @PostMapping(value = "/create")
  public ResponseEntity<MessageDTO> create(@RequestBody MessageDTO messageDto) {
    Chat chat = Chat.builder()
    .id(messageDto.getChatId())
    .build();

    Message message = Message.builder()
    .description(messageDto.getDescription())
    .build();

    message.setChat(chat);
    message = messageService.createMessage(message);

    URI uri = ServletUriComponentsBuilder
              .fromCurrentRequest()
              .path("/{id}")
              .buildAndExpand(message.getId())
              .toUri();
    return ResponseEntity.created(uri).body(new MessageDTO(message));
  }

  @DeleteMapping(value = "/delete/{id}")
  public ResponseEntity<String> deletebyId(@PathVariable Long id){
    this.messageService.deleteMessage(id);
    return ResponseEntity.status(HttpStatus.OK).body("Successful message deletion");

  }

  @PutMapping(value = "/update/{id}")
  public ResponseEntity<MessageDTO> updateById (@PathVariable Long id, @RequestBody MessageDTO messageDTO) {
    Message message = Message.builder()
    .build();
    message = this.messageService.updateMessage(id, message);

    if(message == null){
      throw new EntityNotFoundException(id);
    }
    return  ResponseEntity.ok().body(messageDTO);
  }
}
