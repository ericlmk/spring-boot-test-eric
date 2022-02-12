package com.test.purchaseorder.model;

import lombok.Data;

@Data
public class AppExceptionRes {

  public AppExceptionRes(AppException appException){
    if (appException!=null) {
      this.code = appException.getCode();
      this.message = appException.getMessage();
    }
  }

  private int code;
  private String message;

}
