package com.mssale.controller;

import java.math.BigInteger;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

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

@Endpoint
public class SalesController {
	
	@PayloadRoot(namespace = "http://mssale.com", localPart = "GetSalesDetailByIdRequest")
	@ResponsePayload
	public GetSalesDetailByIdResponse process(@RequestPayload GetSalesDetailByIdRequest req) throws DatatypeConfigurationException {
		GetSalesDetailByIdResponse gcr = new GetSalesDetailByIdResponse();
		SalesDetail customedetail = new SalesDetail();
		customedetail.setId(BigInteger.valueOf(123));
		
		// Obtém uma instância do DatatypeFactory
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();

        // Cria um objeto GregorianCalendar com a data e hora desejadas
        GregorianCalendar calendar = new GregorianCalendar(2023, 3, 29, 14, 30, 0); // 29/04/2023 14:30:00

        // Cria um objeto XMLGregorianCalendar e define seu valor para o valor do objeto GregorianCalendar
        XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(calendar);

		customedetail.setDataHora(xmlGregorianCalendar);
		customedetail.setPersonId(BigInteger.valueOf(1));
		customedetail.setPersonName("Tiago");
		customedetail.setProductId(BigInteger.valueOf(1));
		customedetail.setProductName("bola");
		customedetail.setQuantity(BigInteger.valueOf(5));
		gcr.setSalesDetail(customedetail);
		return gcr;
	}
	
	@PayloadRoot(namespace = "http://mssale.com", localPart = "GetAllSalesRequest")
	@ResponsePayload
	public GetAllSalesResponse process2(@RequestPayload GetAllSalesRequest req) throws DatatypeConfigurationException {
		
		GetAllSalesResponse gcr = new GetAllSalesResponse();
		SalesDetail customedetail = new SalesDetail();
		customedetail.setId(BigInteger.valueOf(123));
		
		// Obtém uma instância do DatatypeFactory
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();

        // Cria um objeto GregorianCalendar com a data e hora desejadas
        GregorianCalendar calendar = new GregorianCalendar(2023, 3, 29, 14, 30, 0); // 29/04/2023 14:30:00

        // Cria um objeto XMLGregorianCalendar e define seu valor para o valor do objeto GregorianCalendar
        XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(calendar);

		customedetail.setDataHora(xmlGregorianCalendar);
		customedetail.setPersonId(BigInteger.valueOf(1));
		customedetail.setPersonName("Tiago");
		customedetail.setProductId(BigInteger.valueOf(1));
		customedetail.setProductName("bola");
		customedetail.setQuantity(BigInteger.valueOf(5));
		
		gcr.getSalesDetail().add(customedetail);
		
		
		SalesDetail customedetail2 = new SalesDetail();
		customedetail.setId(BigInteger.valueOf(13));
		
		// Obtém uma instância do DatatypeFactory
        DatatypeFactory datatypeFactory2 = DatatypeFactory.newInstance();

        // Cria um objeto GregorianCalendar com a data e hora desejadas
        GregorianCalendar calendar2 = new GregorianCalendar(2023, 3, 29, 14, 30, 0); // 29/04/2023 14:30:00

        // Cria um objeto XMLGregorianCalendar e define seu valor para o valor do objeto GregorianCalendar
        XMLGregorianCalendar xmlGregorianCalendar2 = datatypeFactory2.newXMLGregorianCalendar(calendar2);

		customedetail2.setDataHora(xmlGregorianCalendar2);
		customedetail2.setPersonId(BigInteger.valueOf(1));
		customedetail2.setPersonName("Marcelos");
		customedetail2.setProductId(BigInteger.valueOf(1));
		customedetail2.setProductName("Lapis");
		customedetail2.setQuantity(BigInteger.valueOf(2));
		
		gcr.getSalesDetail().add(customedetail2);
		
		return gcr;
	}
	
	@PayloadRoot(namespace = "http://mssale.com", localPart = "InsertSalesRequest")
	@ResponsePayload
	public InsertSalesResponse process2(@RequestPayload InsertSalesRequest req) throws DatatypeConfigurationException {
		InsertSalesResponse isr = new InsertSalesResponse();
		isr.setDefaultMessage("Registro gravado com sucesso!");
		return isr;
	}
	
	@PayloadRoot(namespace = "http://mssale.com", localPart = "UpdateSalesRequest")
	@ResponsePayload
	public UpdateSalesResponse process2(@RequestPayload UpdateSalesRequest req) throws DatatypeConfigurationException {
		UpdateSalesResponse isr = new UpdateSalesResponse();
		isr.setDefaultMessage("Registro atualizado com sucesso!");
		return isr;
	}
	
	@PayloadRoot(namespace = "http://mssale.com", localPart = "DeleteSalesDetailByIdRequest")
	@ResponsePayload
	public DeleteSalesDetailByIdResponse process2(@RequestPayload DeleteSalesDetailByIdRequest req) throws DatatypeConfigurationException {
		DeleteSalesDetailByIdResponse isr = new DeleteSalesDetailByIdResponse();
		isr.setDefaultMessage("Registro excluido com sucesso!");
		return isr;
	}
	
	

}
