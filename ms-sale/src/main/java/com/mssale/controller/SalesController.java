package com.mssale.controller;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.mssale.DeleteSalesDetailByIdRequest;
import com.mssale.DeleteSalesDetailByIdResponse;
import com.mssale.GetAllSalesRequest;
import com.mssale.GetAllSalesResponse;
import com.mssale.GetSalesDetailByIdRequest;
import com.mssale.GetSalesDetailByIdResponse;
import com.mssale.InsertSalesRequest;
import com.mssale.InsertSalesResponse;
import com.mssale.SalesDetail;
import com.mssale.UpdateSalesRequest;
import com.mssale.UpdateSalesResponse;
import com.mssale.model.Sales;
import com.mssale.service.SalesServices;

@Endpoint
public class SalesController {
	
	@Autowired
	private SalesServices salesServices;
	
	@PayloadRoot(namespace = "http://mssale.com", localPart = "GetSalesDetailByIdRequest")
	@ResponsePayload
	public GetSalesDetailByIdResponse process(@RequestPayload GetSalesDetailByIdRequest req) throws DatatypeConfigurationException {
		GetSalesDetailByIdResponse gcr = new GetSalesDetailByIdResponse();
		
		Sales sale = salesServices.listSaleById(req.getId().intValue());
		
		SalesDetail customedetail = new SalesDetail();
		customedetail.setId(BigInteger.valueOf(sale.getId()));
		
		// Obtém uma instância do DatatypeFactory
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();

        // Cria um objeto GregorianCalendar com a data e hora desejadas
        GregorianCalendar calendar = new GregorianCalendar(sale.getDataHora().getYear(),
														   sale.getDataHora().getMonthValue()-1,
													       sale.getDataHora().getDayOfMonth(), 
													       sale.getDataHora().getHour(),
													       sale.getDataHora().getMinute(), 
													       sale.getDataHora().getMinute());

        // Cria um objeto XMLGregorianCalendar e define seu valor para o valor do objeto GregorianCalendar
        XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(calendar);

		customedetail.setDataHora(xmlGregorianCalendar);
		customedetail.setPersonId(BigInteger.valueOf(sale.getPersonId()));
		//customedetail.setPersonName("Tiago");
		customedetail.setProductId(BigInteger.valueOf(sale.getProductId()));
		//customedetail.setProductName("bola");
		customedetail.setQuantity(BigInteger.valueOf(sale.getQuantity()));
		gcr.setSalesDetail(customedetail);
		return gcr;
	}
	
	@PayloadRoot(namespace = "http://mssale.com", localPart = "GetAllSalesRequest")
	@ResponsePayload
	public GetAllSalesResponse process2(@RequestPayload GetAllSalesRequest req) throws DatatypeConfigurationException {
		
		GetAllSalesResponse gcr = new GetAllSalesResponse();
		
		List<Sales> listSales = new ArrayList<>();
		listSales = salesServices.listSales();
		for(Sales sale : listSales) {
			
			SalesDetail customedetail = new SalesDetail();
			customedetail.setId(BigInteger.valueOf(sale.getId()));
		
			// Obtém uma instância do DatatypeFactory
	        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();

	        // Cria um objeto GregorianCalendar com a data e hora desejadas
	        GregorianCalendar calendar = new GregorianCalendar(sale.getDataHora().getYear(),
	        												   sale.getDataHora().getMonthValue()-1,
	        											       sale.getDataHora().getDayOfMonth(), 
	        											       sale.getDataHora().getHour(),
	        											       sale.getDataHora().getMinute(), 
	        											       sale.getDataHora().getMinute());

	        // Cria um objeto XMLGregorianCalendar e define seu valor para o valor do objeto GregorianCalendar
	        XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(calendar);

	        customedetail.setDataHora(xmlGregorianCalendar);
			customedetail.setPersonId(BigInteger.valueOf(sale.getId()));
			//customedetail.setPersonName("Tiago");
			customedetail.setProductId(BigInteger.valueOf(sale.getProductId()));
			customedetail.setPersonId(BigInteger.valueOf(sale.getPersonId()));
			//customedetail.setProductName("bola");
			customedetail.setQuantity(BigInteger.valueOf(sale.getQuantity()));
			
			gcr.getSalesDetail().add(customedetail);
		}
				
		return gcr;
	}
	
	@PayloadRoot(namespace = "http://mssale.com", localPart = "InsertSalesRequest")
	@ResponsePayload
	public InsertSalesResponse process2(@RequestPayload InsertSalesRequest req) throws DatatypeConfigurationException {
		InsertSalesResponse isr = new InsertSalesResponse();
		Sales s = new Sales();
		s.setDataHora(LocalDateTime.parse(req.getDataHora().toString()));
		s.setPersonId(req.getPersonId().intValue());
		s.setProductId(req.getProductId().intValue());
		s.setQuantity(req.getQuantity().intValue());
		salesServices.insertSale(s);
		isr.setDefaultMessage("Registro gravado com sucesso!");
		return isr;
	}
	
	@PayloadRoot(namespace = "http://mssale.com", localPart = "UpdateSalesRequest")
	@ResponsePayload
	public UpdateSalesResponse process2(@RequestPayload UpdateSalesRequest req) throws DatatypeConfigurationException {
		UpdateSalesResponse isr = new UpdateSalesResponse();
		
		Sales s = new Sales();
		s.setId(req.getSalesDetail().getId().intValue());
		s.setDataHora(LocalDateTime.parse(req.getSalesDetail().getDataHora().toString()));
		s.setPersonId(req.getSalesDetail().getPersonId().intValue());
		s.setProductId(req.getSalesDetail().getProductId().intValue());
		s.setQuantity(req.getSalesDetail().getQuantity().intValue());
		salesServices.updateSale(s);
		isr.setDefaultMessage("Registro atualizado com sucesso!");
		return isr;
	}
	
	@PayloadRoot(namespace = "http://mssale.com", localPart = "DeleteSalesDetailByIdRequest")
	@ResponsePayload
	public DeleteSalesDetailByIdResponse process2(@RequestPayload DeleteSalesDetailByIdRequest req) throws DatatypeConfigurationException {
		DeleteSalesDetailByIdResponse isr = new DeleteSalesDetailByIdResponse();
		salesServices.deleteSale(req.getId().intValue());
		isr.setDefaultMessage("Registro excluido com sucesso!");
		return isr;
	}
	
	

}
