package com.woods.tcc.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.woods.tcc.dto.ChatDTO;
import com.woods.tcc.model.Chat;
import com.woods.tcc.model.Client;
import com.woods.tcc.model.Message;
import com.woods.tcc.model.Provider;
import com.woods.tcc.services.ChatService;
import com.woods.tcc.services.ClientService;
import com.woods.tcc.services.ProviderService;
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
@RequestMapping(value = "/chat")
public class ChatController {

  @Autowired
  private ChatService chatService;

  @Autowired
  private ClientService clientService;

  @Autowired
  private ProviderService providerService;

  @GetMapping
  public ResponseEntity<List<ChatDTO>> findAll(){
    List<Chat> listChat = chatService.findAll();

    if(listChat.isEmpty())
      throw new EntityNotFoundException(0);

    List<ChatDTO> lChatDTOs = listChat
      .stream()
      .map( x -> new ChatDTO(x))
      .collect(Collectors.toList());

    return ResponseEntity.ok().body(lChatDTOs);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ChatDTO> findById(@PathVariable Long id){
    try {
      Chat chat = chatService.findById(id);
      ChatDTO chatDTO = new ChatDTO(chat);
      return  ResponseEntity.ok().body(chatDTO);
    }catch (NoSuchElementException e) {
      throw new EntityNotFoundException(id);
    }
  }

  @PostMapping(value = "/create/{clientId}/{providerId}")
  public ResponseEntity<ChatDTO> create(
    @RequestBody ChatDTO chatDto,
    @PathVariable (required = true) Long clientId,
    @PathVariable (required = true) Long providerId) {

      Client client = this.clientService.findById(clientId);
      Provider provider = this.providerService.findById(providerId);
      List<Message> listMessage = new ArrayList<>();
      Chat chat = Chat.builder()
      .clientChat(client)
      .provider(provider)
      .listMessages(listMessage)
      .build();

      chat = chatService.createChat(chat);

      URI uri = ServletUriComponentsBuilder
              .fromCurrentRequest()
              .path("/{id}")
              .buildAndExpand(chat.getId())
              .toUri();
    return ResponseEntity.created(uri).body(new ChatDTO(chat));
  }

  @DeleteMapping(value = "/delete/{id}")
  public ResponseEntity<String> deletebyId(@PathVariable Long id){
    this.chatService.deleteChat(id);
    return ResponseEntity.status(HttpStatus.OK).body("Successful chat deletion");

  }

  @PutMapping(value = "/update/{id}")
  public ResponseEntity<ChatDTO> updateById (@PathVariable Long id, @RequestBody ChatDTO chatDTO) {
    Chat chat = Chat.builder()
    // .street(chatDTO.getStreet())
    // .number(chatDTO.getNumber())
    // .complement(chatDTO.getComplement())
    // .district(chatDTO.getDistrict())
    // .city(chatDTO.getCity())
    // .state(chatDTO.getState())
    .build();
    chat = this.chatService.updateChat(id, chat);

    if(chat == null){
      throw new EntityNotFoundException(id);
    }
    return  ResponseEntity.ok().body(chatDTO);
  }
}
