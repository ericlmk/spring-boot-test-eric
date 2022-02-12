package com.test.purchaseorder.service;

import com.test.purchaseorder.dao.ProductItemRepository;
import com.test.purchaseorder.domain.ProductItem;
import com.test.purchaseorder.model.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductItemService {

  public static int EX_CODE_DEFAULT = 999;

  @Autowired
  private ProductItemRepository productItemRepository;

  public ProductItem getProductItemById(int id) throws AppException {
    try {
      return this.productItemRepository.findById(id).get();
    } catch (Exception ex) {
      // Rethrow
      throw new AppException(EX_CODE_DEFAULT, ex.getMessage());
    }
  }

}
