package com.test.purchaseorder.dao;

import com.test.purchaseorder.domain.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, Integer> {

}
