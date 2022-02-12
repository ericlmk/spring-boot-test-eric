package com.test.purchaseorder.service;

import com.test.purchaseorder.dao.PurchaseOrderRepository;
import com.test.purchaseorder.domain.PurchaseOrder;
import com.test.purchaseorder.model.AppException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {

  public static int EX_CODE_DEFAULT = 999;
  private static String STATUS_DELETED = "Deleted";

  @Autowired
  private PurchaseOrderRepository purchaseOrderRepository;


  public List<PurchaseOrder> getOrderList(Date date, String clientName) throws AppException {
    try {
      if (date==null) {
        if (StringUtils.isNotBlank(clientName)) {
          return this.purchaseOrderRepository.getAllByClientName(clientName);
        } else {
          return this.purchaseOrderRepository.findAllByStatusNot(STATUS_DELETED);
        }
      } else {
        return this.purchaseOrderRepository.getAllByCreateDate(date);
      }
    } catch (Exception ex) {
      // Rethrow
      throw new AppException(EX_CODE_DEFAULT, ex.getMessage());
    }
  }

  public PurchaseOrder getOrderById(int id) throws AppException {
    try {
      return this.purchaseOrderRepository.findFirstByPurchaseOrderIdAndStatusNot(id, STATUS_DELETED);
    } catch (Exception ex) {
      // Rethrow
      throw new AppException(EX_CODE_DEFAULT, ex.getMessage());
    }
  }

  public PurchaseOrder createOrder(PurchaseOrder po) throws AppException {
    try {
      return this.purchaseOrderRepository.save(po);
    } catch (Exception ex) {
      // Rethrow
      throw new AppException(EX_CODE_DEFAULT, ex.getMessage());
    }
  }

  public PurchaseOrder updateOrder(PurchaseOrder po) throws AppException {
    try {
      return this.purchaseOrderRepository.save(po);
    } catch (Exception ex) {
      // Rethrow
      throw new AppException(EX_CODE_DEFAULT, ex.getMessage());
    }
  }

  public boolean deleteOrder(int poId) throws AppException {
    Optional<PurchaseOrder> existingPo = this.purchaseOrderRepository.findById(poId);
    if (existingPo.isPresent()) {
      PurchaseOrder po = existingPo.get();
      if (STATUS_DELETED.equals(po.getStatus())) {
        // Already Deleted
        return false;
      }
      try {
        po.setStatus(STATUS_DELETED);
        this.purchaseOrderRepository.save(po);
      } catch (Exception ex) {
        // Rethrow
        throw new AppException(EX_CODE_DEFAULT, ex.getMessage());
      }
      // Success
      return true;
    } else {
      return false;
    }
  }

}
