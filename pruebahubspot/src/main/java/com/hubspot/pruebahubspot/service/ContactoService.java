package com.hubspot.pruebahubspot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hubspot.pruebahubspot.util.HubspotApi;

@Service
public class ContactoService {
	private final HubspotApi hubspotApi;

	@Autowired
	public ContactoService(HubspotApi hubspotApi) {
		this.hubspotApi = hubspotApi;
	}//constructor
	
	public String getContactByEmail(String email) {
        // Llamar al método getContactByEmail de la clase HubspotApi para obtener la información del contacto
        return hubspotApi.getContactByEmail(email);
    }//getContactByEmail
	
	public String addContact(String nombre, String apellido, String email, String telefono) {
        // Llamar al método addContact del cliente de HubSpot para crear el contacto
        return hubspotApi.addContact(nombre, apellido, email, telefono);
    }//addContact
	
	public String updateContact(String email, String nuevoNombre, String nuevoApellido, String nuevoEmail, String nuevoTelefono) {
        // Llamar al método updateContact de la clase HubspotApi para actualizar el contacto
        return hubspotApi.updateContact(email, nuevoNombre, nuevoApellido, nuevoEmail, nuevoTelefono);
    }//updateContact
	
	public String deleteContact(String email) {
        // Llamar al método deleteContact de la clase HubspotApi para eliminar el contacto
        return hubspotApi.deleteContact(email);
    }//deleteContact
	
	public String getAllContacts() {
        // Llamar al método getAllContacts de la clase HubspotApi para obtener todos los contactos
        return hubspotApi.getAllContacts();
    }//getAllContacts
}//class
