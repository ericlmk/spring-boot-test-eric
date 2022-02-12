package com.test.purchaseorder.feign;

import com.test.purchaseorder.api.PurchaseOrderApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(name = "provider", url = "${feign.client.url.serviceUrl}", fallback = PurchaseOrderServiceFallbackClient.class)
public interface PurchaseOrderServiceClient extends PurchaseOrderApi {
}