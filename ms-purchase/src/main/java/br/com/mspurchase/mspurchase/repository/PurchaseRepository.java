package br.com.mspurchase.mspurchase.repository;

import br.com.mspurchase.mspurchase.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
}
