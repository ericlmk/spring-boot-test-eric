package com.test.purchaseorder.model;

import lombok.Data;

@Data
public class AppException extends Exception {

  public AppException(int code, String message){
    this.code = code;
    this.message = message;
  }

  private int code;
  private String message;

}
