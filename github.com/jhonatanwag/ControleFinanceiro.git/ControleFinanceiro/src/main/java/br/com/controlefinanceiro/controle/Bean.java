package br.com.controlefinanceiro.controle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class Bean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Map<String, Object> getAtributos() {
		Map<String, Object> atributos = new HashMap<>();
		atributos.put("type", "email");
		atributos.put("placeholder", "Entre com seu email");
		return atributos;
	}

}