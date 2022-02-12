package com.test.purchaseorder.service;

import com.test.purchaseorder.configuration.ApplicationConfig;
import com.test.purchaseorder.dao.ClientRepository;
import com.test.purchaseorder.domain.Client;
import com.test.purchaseorder.model.AppException;
import com.test.purchaseorder.model.ClientData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

  public static int EX_CODE_DEFAULT = 999;

  @Autowired
  private ClientRepository clientRepository;
  @Autowired
  private ApplicationConfig appConfig;


  public Client getClient(String clientName) throws AppException {
    ClientData clientData = this.appConfig.getClientDataMap().get(clientName);
    if (clientData==null) {
      // Client Not Found
      throw new AppException(EX_CODE_DEFAULT, "clientName["+clientName+"] is not found");
    }
    try {
      return this.clientRepository.findFirstByClientName(clientName);
    } catch (Exception ex) {
      // Rethrow
      throw new AppException(EX_CODE_DEFAULT, ex.getMessage());
    }
  }

  public Client createClient(Client client) throws AppException {
    try {
      return this.clientRepository.save(client);
    } catch (Exception ex) {
      // Rethrow
      throw new AppException(EX_CODE_DEFAULT, ex.getMessage());
    }
  }

  public String getUserName() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getName().toString();
  }

  public boolean isRoleAdmin() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ADMIN"));
  }

}
