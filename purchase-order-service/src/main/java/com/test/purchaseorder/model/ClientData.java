package com.test.purchaseorder.model;

import lombok.Data;

@Data
public class ClientData {

  public ClientData(String clientName, String clientPassword, String clientRole) {
    this.clientName = clientName;
    this.clientPassword = clientPassword;
    this.clientRole = clientRole;
  }

  private String clientName;
  private String clientPassword;
  private String clientRole;

}
