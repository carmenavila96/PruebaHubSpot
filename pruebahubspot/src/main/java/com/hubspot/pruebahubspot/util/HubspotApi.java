package com.hubspot.pruebahubspot.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class HubspotApi {
	private final RestTemplate restTemplate;
    private final String apiKey;
    
    public HubspotApi(@Value("${hubspot.api.key}") String apiKey) {
        this.restTemplate = new RestTemplate();
        this.apiKey = apiKey;
    }//constructor

    //Método para encontrar un contacto por medio de su email
    public String getContactByEmail(String email) {
        String endpoint = "/contacts/v1/contact/email/" + email + "/profile";
        String url = "https://api.hubapi.com" + endpoint + "?hapikey=" + apiKey;
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class);
        return response.getBody();
    }//getContactByEmail
    
    //Método para agregar un contacto
    public String addContact(String nombre, String apellido, String email, String telefono) {
        String endpoint = "/contacts/v1/contact";
        String url = "https://api.hubapi.com" + endpoint + "?hapikey=" + apiKey;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Accept", "application/json");

        // Cuerpo de la solicitud con los datos del nuevo contacto
        String requestBody = "{\"properties\": [{\"property\": \"nombre\",\"value\": \"" + nombre + "\"}," +
				"{\"property\": \"apellido\",\"value\": \"" + apellido + "\"}," +
				"{\"property\": \"email\",\"value\": \"" + email + "\"}," +
           				"{\"property\": \"telefono\", \"value\": \"" + telefono + "\"}]}";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    String.class);
            if (response.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            	System.out.println("El contacto se agregó correctamente");
            }//if
        return response.getBody();
        } catch (HttpClientErrorException e) {
            // Manejo de errores (por ejemplo, cuando el contacto ya existe)
        	System.out.println("Ocurrió un error al agregar el contacto " + e.getMessage());
        }//try cath
		return requestBody;
    }//addContact
    
    //Método para actualizar un contacto por medio de su email
    public String updateContact(String email, String nuevoNombre, String nuevoApellido, String nuevoEmail,
			String nuevoTelefono) {
        String endpoint = "/contacts/v1/contact/email/" + email + "/profile";
        String url = "https://api.hubapi.com" + endpoint + "?hapikey=" + apiKey;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Accept", "application/json");

        // Cuerpo de la solicitud para actualizar los datos del contacto
        String requestBody = "{" +
                "\"properties\": [" +
                    "{\"property\": \"firstname\", \"value\": \"" + nuevoNombre + "\"}," +
                    "{\"property\": \"lastname\", \"value\": \"" + nuevoApellido + "\"}," +
                    "{\"property\": \"email\", \"value\": \"" + nuevoEmail + "\"}," +
                    "{\"property\": \"phone\", \"value\": \"" + nuevoTelefono + "\"}" +
                "]" +
              "}";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    entity,
                    String.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            // Manejar errores de cliente (por ejemplo si el contacto no existe)
            System.out.println("Ocurrió un error al actualizar el contacto " + e.getMessage());
        }//try catch
		return requestBody;
    }//updateContact

  //Método para eliminar un contacto por medio de su email
    public String deleteContact(String email) {
        String endpoint = "/contacts/v1/contact/email/" + email;
        String url = "https://api.hubapi.com" + endpoint + "?hapikey=" + apiKey;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.DELETE,
                    entity,
                    String.class);
            if (response.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            	System.out.println("El contacto se eliminó correctamente");
            }//if
        } catch (HttpClientErrorException.NotFound e) {
            // El contacto no fue encontrado
        	System.out.println("Ocurrió un error al eliminar el contacto " + e.getMessage());
        }//try catch
		return url;
    }//deleteContact
    
  //Método para obtener todos los contactos (falta implementarlo)
    public String getAllContacts() {
        String endpoint = "/contacts/v1/lists/all/contacts/all";
        String url = "https://api.hubapi.com" + endpoint + "?hapikey=" + apiKey + "&property=email&property=firstname&property=lastname";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String.class);
            return response.getBody();
        } catch (Exception e) {
            // Manejo de errores
            return "Error al obtener los contactos: " + e.getMessage();
        }//try catch
    }//getAllContacts

}//class
