package br.com.mspurchase.mspurchase.service;

import br.com.mspurchase.mspurchase.model.Purchase;
import br.com.mspurchase.mspurchase.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public Purchase createPurchase(Purchase purchase){
        return purchaseRepository.save(purchase);
    }

    public Purchase updatePurchase(Purchase purchase){
        return purchaseRepository.save(purchase);
    }

    public void deletePurchase(Integer id){
        purchaseRepository.deleteById(id);
    }

    public List<Purchase> listAllPurchase(){
        return purchaseRepository.findAll();
    }

    public Purchase listPurchaseById(Integer id){
        return purchaseRepository.findById(id).get();
    }
}
