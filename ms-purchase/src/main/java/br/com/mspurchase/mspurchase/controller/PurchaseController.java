package br.com.mspurchase.mspurchase.controller;

import br.com.mspurchase.*;
import br.com.mspurchase.mspurchase.model.Purchase;
import br.com.mspurchase.mspurchase.service.PurchaseService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@GrpcService
public class PurchaseController extends PurchaseServiceGrpc.PurchaseServiceImplBase {
    @Autowired
    private PurchaseService purchaseService;

    @Override
    public void createPurchase(PurchaseReq request, StreamObserver<PurchaseResp> responseObserver) {

        Purchase purchase = new Purchase();
        purchase.setDateTime(LocalDateTime.parse(request.getDateTime()));
        purchase.setPersonId(request.getPersonId());
        purchase.setProductId(request.getProductId());
        purchase.setQuantity(request.getQuantity());

        Purchase purchaseOk = purchaseService.createPurchase(purchase);

        var response = PurchaseResp
                       .newBuilder()
                       .setId(purchaseOk.getId())
                       .setQuantity(request.getQuantity())
                       .setPersonId(request.getPersonId())
                       .setProductId(request.getProductId())
                       .setDateTime(request.getDateTime())
                       .setValorUnitario(request.getValorUnitario())
                       .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void listPurchaseFindById(RequestById request, StreamObserver<PurchaseResp> responseObserver) {

        Purchase purchase = purchaseService.listPurchaseById(request.getId());

        var response = PurchaseResp
                .newBuilder()
                .setId(purchase.getId())
                .setQuantity(purchase.getQuantity())
                .setPersonId(purchase.getPersonId())
                .setProductId(purchase.getProductId())
                .setDateTime(purchase.getDateTime().toString())
                .setValorUnitario(purchase.getValorUnitario())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updatePurchase(PurchaseReqUpdate request, StreamObserver<PurchaseResp> responseObserver) {
        Purchase purchase = new Purchase();
        purchase.setId(request.getId());
        purchase.setDateTime(LocalDateTime.parse(request.getDateTime()));
        purchase.setPersonId(request.getPersonId());
        purchase.setProductId(request.getProductId());
        purchase.setQuantity(request.getQuantity());

        Purchase purchaseOk = purchaseService.updatePurchase(purchase);

        var response = PurchaseResp
                .newBuilder()
                .setId(purchaseOk.getId())
                .setQuantity(request.getQuantity())
                .setPersonId(request.getPersonId())
                .setProductId(request.getProductId())
                .setDateTime(request.getDateTime())
                .setValorUnitario(request.getValorUnitario())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void deletePurchase(RequestById request, StreamObserver<EmptyPurchase> responseObserver) {
        purchaseService.deletePurchase(request.getId());
        var emptyResp = EmptyPurchase.newBuilder().build();
        responseObserver.onNext(emptyResp);
        responseObserver.onCompleted();
    }

    @Override
    public void findAllPurchase(EmptyPurchase request, StreamObserver<PurchaseList> responseObserver) {

        List<Purchase> purchaseList = purchaseService.listAllPurchase();

        List<PurchaseResp> listPurchaseResp = new ArrayList<>();

        for(Purchase purchase : purchaseList){
           var pr = PurchaseResp
                   .newBuilder()
                   .setDateTime(purchase.getDateTime().toString())
                   .setId(purchase.getId())
                   .setPersonId(purchase.getPersonId())
                   .setProductId(purchase.getProductId())
                   .setQuantity(purchase.getQuantity())
                   .build();

           listPurchaseResp.add(pr);
        }

        PurchaseList purchaseList2 = PurchaseList.newBuilder().addAllPurchases(listPurchaseResp).build();

        responseObserver.onNext(purchaseList2);
        responseObserver.onCompleted();
    }
}
