package com.woods.tcc.controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.woods.tcc.dto.ServicingDTO;
import com.woods.tcc.model.Servicing;
import com.woods.tcc.services.ServicingService;
import com.woods.tcc.services.exceptions.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
@RequestMapping(value = "/servicings")
public class ServicingController {
  @Autowired
  private ServicingService servicingService;

  @GetMapping
  public ResponseEntity<List<ServicingDTO>> findAll() {
    List<Servicing> lServicings = this.servicingService.findAll();

    if(lServicings.isEmpty())
      throw new EntityNotFoundException("There is no servicings found");

    List<ServicingDTO> lServicingDTOs = lServicings
    .stream()
    .map(x -> new ServicingDTO(x))
    .collect(Collectors.toList());
    return ResponseEntity.ok().body(lServicingDTOs);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<ServicingDTO> findById(@PathVariable Long id) {
    try {
      Servicing servicing = this.servicingService.findById(id);
      ServicingDTO servicingDTO = new ServicingDTO(servicing);
      return ResponseEntity.ok().body(servicingDTO);
    } catch (NoSuchElementException e) {
      throw new EntityNotFoundException(id);
    }
  }

  @PostMapping(value = "/create")
  public ResponseEntity<ServicingDTO> create(@RequestBody ServicingDTO servicingDTO) {
    Servicing servicing = Servicing.builder()
    .name(servicingDTO.getName())
    .description(servicingDTO.getDescription())
    .type(servicingDTO.getType())
    .imageUrl(servicingDTO.getImageUrl())
    .build();
    try {
      servicing = this.servicingService.createServicing(servicing);
    } catch (DataIntegrityViolationException e) {
      throw new EntityNotFoundException("Error: a valid provider id is required or this email is already registered");
    }

    URI uri = ServletUriComponentsBuilder
              .fromCurrentRequest()
              .path("/{id}")
              .buildAndExpand(servicing.getId())
              .toUri();
    return ResponseEntity.created(uri).body(new ServicingDTO(servicing));
  }

  @DeleteMapping(value ="/delete/{id}")
  public ResponseEntity<String> deleteById (@PathVariable Long id, @RequestBody ServicingDTO servicingDTO){
    this.servicingService.deleteServicing(id);
    return ResponseEntity.status(HttpStatus.OK).body("Successful servicing deletion");
  }

  @PutMapping(value = "/update/{id}")
  public ResponseEntity<ServicingDTO> updateById (@PathVariable Long id, @RequestBody ServicingDTO servicingDTO) {
    Servicing servicing = Servicing.builder()
    .name(servicingDTO.getName())
    .description(servicingDTO.getDescription())
    .type(servicingDTO.getType())
    .imageUrl(servicingDTO.getImageUrl())
    .build();
    servicing = this.servicingService.updateServicing(id, servicing);

    if(servicing == null){
      throw new EntityNotFoundException(id);
    }
    return  ResponseEntity.ok().body(servicingDTO);
  }
}
