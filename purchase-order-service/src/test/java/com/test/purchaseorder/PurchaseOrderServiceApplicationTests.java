package com.test.purchaseorder;

import com.test.purchaseorder.dao.PurchaseOrderRepository;
import com.test.purchaseorder.domain.ProductItem;
import com.test.purchaseorder.domain.PurchaseOrder;
import com.test.purchaseorder.model.AppException;
import com.test.purchaseorder.service.PurchaseOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class PurchaseOrderServiceApplicationTests {

	@Autowired
	private PurchaseOrderService purchaseOrderService;

	@MockBean
	private PurchaseOrderRepository purchaseOrderRepository;

	@Test
	public void testGetOrderById() {
		// Mock Data
		ProductItem pi = new ProductItem();
		pi.setPiId(1);
		pi.setPiQuantity(1);
		List<ProductItem> productItemList = new ArrayList<ProductItem>();
		productItemList.add(pi);
		PurchaseOrder mockPo = new PurchaseOrder();
		mockPo.setPurchaseOrderId(1);
		mockPo.setStatus("ACTIVE");
		mockPo.setProductItemList(productItemList);

		// Mock Case
		when(purchaseOrderRepository.findFirstByPurchaseOrderIdAndStatusNot(1, "Deleted"))
				.thenReturn(mockPo);

		// Test
		try {
			PurchaseOrder po = this.purchaseOrderService.getOrderById(1);
			assertEquals("ACTIVE", po.getStatus());
		} catch (AppException e) {
			e.printStackTrace();
		}
	}

}
