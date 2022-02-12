package com.test.purchaseorder.controller;

import com.test.purchaseorder.domain.Client;
import com.test.purchaseorder.model.AppException;
import com.test.purchaseorder.model.AppExceptionRes;
import com.test.purchaseorder.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

  @Autowired
  private ClientService clientService;

  @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
  @PostMapping(path = "/clients", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<?> createClientJson(@RequestBody Client requestBody) {
    try {
      // Create Client
      Client client = this.clientService.createClient(requestBody);
      return new ResponseEntity<>(client, HttpStatus.CREATED);
    } catch (AppException ex) {
      return new ResponseEntity<>(new AppExceptionRes(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
