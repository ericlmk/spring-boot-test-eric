package com.test.purchaseorder.configuration;

import com.test.purchaseorder.model.ClientData;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class ApplicationConfig {

  private Map<String, ClientData> clientDataMap = new HashMap<String, ClientData>();

  public ApplicationConfig() {
    // Test Users
    clientDataMap.put("peter", new ClientData("peter", "123456", "USER"));
    clientDataMap.put("paul", new ClientData("paul", "123456", "ADMIN"));
  }

}
