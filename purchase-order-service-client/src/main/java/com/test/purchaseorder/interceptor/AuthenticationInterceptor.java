package com.test.purchaseorder.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthenticationInterceptor implements RequestInterceptor {

  @Autowired
  HttpServletRequest request;

  @Override
  public void apply(RequestTemplate template) {
    if (StringUtils.isNotBlank(request.getHeader("Authorization"))) {
      // Pass through for Auth header
      template.header("Authorization", request.getHeader("Authorization") );
    }
  }

}
