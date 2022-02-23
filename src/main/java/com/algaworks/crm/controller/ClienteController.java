package com.algaworks.crm.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mercadopago.MercadoPago;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.payment.Address;
import com.mercadopago.resources.datastructures.payment.Identification;
import com.mercadopago.resources.datastructures.payment.PayerPhone;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.resources.datastructures.payment.Payer;


import com.mercadopago.resources.datastructures.preference.Item;
import  com.mercadopago.resources.datastructures.preference.*;



@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/transacao_pix")
public class ClienteController {


	@GetMapping
	public String geraChavePix()throws MPException, MPConfException{
		
	
        MercadoPago.SDK.setAccessToken("cole seu Access Token do Mercado pago aqui");

        
        Address address = new Address();
        address.setCity("Rio de Janeiro");
        address.setNeighborhood("Palmares");
        address.setStreetName("Rua Aloysio Fialho Gomes");
        address.setStreetNumber(400);
        address.setZipCode("23065615");
        address.setFederalUnit("RJ");
        
        Identification identification = new Identification();
        identification.setType("CPF");
        identification.setNumber("07272608633");
        
        
        
		 Payment payment = new Payment()
                 .setTransactionAmount(1100f)
                 .setDescription("description")
                 .setInstallments(1)
                 .setPaymentMethodId("pix")
                 .setPayer(new Payer()
                         .setEmail("luizcox@bol.com.br")
                         .setIdentification(null)
                         .setFirstName("Luiz")
                         .setLastName("Pinheiro de Jesus")
                         .setAddress(address)
                         .setIdentification(identification)
                         
                   );

         payment.save();
         
         
      
   
		    
		    return payment.getPointOfInteraction().getTransactionData().getQrCode();
	}
	
	
	
	
	
	@PostMapping(path = "api/consulta_pix")
	public String obterChavePix(@RequestParam String idPix) throws MPException, MPConfException{
	    MercadoPago.SDK.setAccessToken("TEST-966336605334634-020212-9f7ea6b529c661afd457d0b14b13edfb-1058148422");

		Payment payment = new Payment();
		
	    String status =	payment.findById(idPix).getStatus().toString();
	    
	
	
		return status; 
	}
	
	
	
	@PostMapping(path = "api/gerar_pix")
	public HashMap<String, Object>  gerarPix(
			@RequestParam String nome,
			@RequestParam String sobrenome,
			@RequestParam String email,
			@RequestParam String cidade,
			@RequestParam String bairro,
			@RequestParam String numero,
			@RequestParam String rua,
			@RequestParam String cep,
			@RequestParam String estado,
			@RequestParam String ddd,
			@RequestParam String celular,
			@RequestParam String cpf,
			@RequestParam String valor) throws MPException, MPConfException{
	        
        MercadoPago.SDK.setAccessToken("cole seu Access Token do Mercado pago aqui");

			int numeroR = 0;
			float valorDouble = Float.parseFloat(valor);
			
		if(!numero.isEmpty()) {
			numeroR = Integer.parseInt(numero);
		}
	    
	    Address address = new Address();
	    address.setZipCode(cep);
	    address.setStreetName(rua);
	    address.setStreetNumber(788);
        address.setCity(cidade);
        address.setNeighborhood(bairro);
        address.setFederalUnit(estado);
        
        
     
        
        PayerPhone payerPhone = new PayerPhone();
        payerPhone.setAreaCode(ddd);
        payerPhone.setNumber(celular);
        
        
         
        Identification identification = new Identification();
        identification.setType("CPF");
        identification.setNumber(cpf);
        
        
         

      
     
        Payer payer = new Payer();
       
        payer.setAddress(address);
        payer.setEmail(email);
        payer.setFirstName(nome);
        payer.setLastName(nome);
      //  payer.setPhone(payerPhone);

         
		 Payment payment = new Payment()
                 .setTransactionAmount(valorDouble)
                 .setDescription("aplicativo mobifesta")
                 .setInstallments(1)
                 .setPaymentMethodId("pix")
                 .setPayer(payer);

         payment.save();
         
         
        
	    HashMap<String, Object> map = new HashMap<>();
		 
		 
		 
	     map.put("idPIX", payment.getId()); //
		 map.put("status", payment.getStatus()); //
		 map.put("qr_code",payment.getPointOfInteraction().getTransactionData().getQrCode()); //
		 map.put("total_a_pagar", payment.getTransactionDetails().getTotalPaidAmount());
		 map.put("data_expiracao", payment.getDateOfExpiration());
		 	
	
		return map; 
	}
	
	
	
	
	@PostMapping(path = "api/pagar_cartao_de_credito")
	public HashMap<String, Object>  pagar_cartao_de_credito(
			@RequestParam String nome,
			@RequestParam String sobrenome,
			@RequestParam String email,
			@RequestParam String cidade,
			@RequestParam String bairro,
			@RequestParam String numero,
			@RequestParam String rua,
			@RequestParam String cep,
			@RequestParam String estado,
			@RequestParam String ddd,
			@RequestParam String celular,
			@RequestParam String cpf,
			@RequestParam String valor) throws MPException, MPConfException{
	        
        MercadoPago.SDK.setAccessToken("cole seu Access Token do Mercado pago aqui");

			int numeroR = 0;
			float valorDouble = Float.parseFloat(valor);
			
		if(!numero.isEmpty()) {
			numeroR = Integer.parseInt(numero);
		}
	    
	    Address address = new Address();
	    address.setZipCode(cep);
	    address.setStreetName(rua);
	    address.setStreetNumber(788);
        address.setCity(cidade);
        address.setNeighborhood(bairro);
        address.setFederalUnit(estado);
        
        
     
        
        PayerPhone payerPhone = new PayerPhone();
        payerPhone.setAreaCode(ddd);
        payerPhone.setNumber(celular);
        
        
         
        Identification identification = new Identification();
        identification.setType("CPF");
        identification.setNumber(cpf);
        
        
         
        Payer payer = new Payer();
        
        payer.setAddress(address);
        payer.setEmail(email);
        payer.setFirstName(nome);
        payer.setLastName(nome);
        
       
         
		 Payment payment = new Payment()
                 .setTransactionAmount(valorDouble)
                 .setDescription("aplicativo mobifesta")
                 .setInstallments(1)
                 .setPaymentMethodId("pix")
                 .setPayer(payer);

         payment.save();
         
         
        
	    HashMap<String, Object> map = new HashMap<>();
		 
		 
		 
	     map.put("idPIX", payment.getId()); //
		 map.put("status", payment.getStatus()); //
		 map.put("qr_code",payment.getPointOfInteraction().getTransactionData().getQrCode()); //
		 map.put("total_a_pagar", payment.getTransactionDetails().getTotalPaidAmount());
		 map.put("data_expiracao", payment.getDateOfExpiration());
		 
		

	
	
		return map; 
	}
	
	

	
	/*
	
	@GetMapping("/{id}")
	public String obterChave(@PathVariable String id) {
	
			
		return "fedegozo: " + id; 
	}
	*/
	
	
	
	@PostMapping(path = "api/gerar_preferencia")
	public HashMap<String, Object>  gerar_preferencia(
			
			@RequestParam String nome,
			@RequestParam String sobrenome,
			@RequestParam String email,
			@RequestParam String cidade,
			@RequestParam String bairro,
			@RequestParam String numero,
			@RequestParam String rua,
			@RequestParam String cep,
			@RequestParam String estado,
			@RequestParam String ddd,
			@RequestParam String celular,
			@RequestParam String cpf,
			@RequestParam String valor
			
			
			) throws MPException, MPConfException{
	        
		
		
        MercadoPago.SDK.setAccessToken("cole seu Access Token do Mercado pago aqui");

			int numeroR = 0;
			float valorDouble = Float.parseFloat(valor);
			
			
		if(!numero.isEmpty()) {
			numeroR = Integer.parseInt(numero);
		}
	    
		
		 com.mercadopago.resources.datastructures.preference.Address  address = 
				        new com.mercadopago.resources.datastructures.preference.Address();
		 
		
	    address.setZipCode(cep);
	    address.setStreetName(rua);
	    address.setStreetNumber(788);
       
	   // address.setCity("Rio de Janeiro");
       // address.setNeighborhood("Campo Grande");
       // address.setFederalUnit("RJ");
        
        
	    com.mercadopago.resources.datastructures.preference.Phone payerPhone =
	    		         new  com.mercadopago.resources.datastructures.preference.Phone();
        payerPhone.setAreaCode(ddd);
        payerPhone.setNumber(celular);
        
        
         
        com.mercadopago.resources.datastructures.preference.Identification identification = 
        		            new  com.mercadopago.resources.datastructures.preference.Identification();
        
        identification.setType("CPF");
        identification.setNumber(cpf);
        
        
        
        
        com.mercadopago.resources.datastructures.preference.Payer 
        payer = new com.mercadopago.resources.datastructures.preference.Payer();
        
        
        
        Item item = new Item();
        
         item.setTitle("livro");
         item.setId(celular);
         item.setQuantity(1);
         item.setUnitPrice(valorDouble);
        
         payer.setAddress(address);
         payer.setIdentification(identification);
         payer.setEmail(email);
         payer.setName(nome);
         payer.setPhone(payerPhone);
         
         
      //  List<> 
        Preference preference = new Preference();
        
        
        ArrayList<com.mercadopago.resources.datastructures.preference.Item> 
                 listaItems = new ArrayList<com.mercadopago.resources.datastructures.preference.Item>() ;
        
     
        listaItems.add(item);
        preference.setItems(listaItems);
        preference.setPayer(payer);
        
        
      
      
      //  preference.getPaymentMethods().setExcludedPaymentMethods();
        	
         
		preference.save();
         
        
	    HashMap<String, Object> map = new HashMap<>();
		 
		 
		 
	     map.put("id", preference.getId()); //
		// map.put("payment methods",preference.getPaymentMethods() ); //
	//	 map.put("qr_code",payment.getPointOfInteraction().getTransactionData().getQrCode()); //
	//	 map.put("total_a_pagar", payment.getTransactionDetails().getTotalPaidAmount());
	//	 map.put("data_expiracao", payment.getDateOfExpiration());
		 
		

	
		return map; 
	}
	
	

	
	
}
