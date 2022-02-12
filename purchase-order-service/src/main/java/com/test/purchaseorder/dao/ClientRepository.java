package com.test.purchaseorder.dao;

import com.test.purchaseorder.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

  public Client findFirstByClientName(String clientName);

}
