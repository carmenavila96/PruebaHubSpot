package com.hubspot.pruebahubspot.model;

public class Contacto {
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	
	public Contacto(String nombre, String apellido, String email, String telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
	}//constructor

	public Contacto() {
	}//constructorVacío

	public String getNombre() {
		return nombre;
	}//getNombre

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}//setNombre

	public String getApellido() {
		return apellido;
	}//getApellido

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}//setApellido

	public String getEmail() {
		return email;
	}//getEmail

	public void setEmail(String email) {
		this.email = email;
	}//setEmail

	public String getTelefono() {
		return telefono;
	}//getTelefono

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}//setTelefono

	@Override
	public String toString() {
		return "Contacto [nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", telefono=" + telefono
				+ "]";
	}//toString
	
}//class
