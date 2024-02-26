package com.hubspot.pruebahubspot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hubspot.pruebahubspot.model.Contacto;
import com.hubspot.pruebahubspot.service.ContactoService;

@RestController
@RequestMapping(path="/api/contactos/")
public class ContactoController {
	private final ContactoService contactoService;

	@Autowired
	public ContactoController(ContactoService contactoService) {
		this.contactoService = contactoService;
	}//constructor
	
	@GetMapping(path="{email}")
	public String getContactByEmail(@PathVariable("email") String email) {
		return contactoService.getContactByEmail(email);
	}//getContactByEmail
	
	@PostMapping						
	public String addContact(@RequestBody Contacto contacto){
		return contactoService.addContact(contacto.getNombre(), contacto.getApellido(), contacto.getEmail(), contacto.getTelefono());
	}//addContacto
	
	@PutMapping(path="{email}")		
	public String updateContact(@PathVariable("email") String email,
			@RequestParam(required = false) String nombre,
			@RequestParam(required = false) String apellido,
			@RequestParam(required = false) String nuevoEmail,
			@RequestParam(required = false) String telefono){
		return contactoService.updateContact(email, nombre, apellido, nuevoEmail, telefono);
	}// updateContacto
	
	@DeleteMapping(path="{email}")		
	public String deleteContact(@PathVariable("email") String email){
		return contactoService.deleteContact(email);
	}// deleteProducto
	
	@GetMapping("/contactos")
    public String getAllContacts() {
        return contactoService.getAllContacts();
    }//getAllContacts
	
}//class
