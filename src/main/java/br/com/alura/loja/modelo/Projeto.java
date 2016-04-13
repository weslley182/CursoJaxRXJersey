package br.com.alura.loja.modelo;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

public class Projeto {
	
	private String nome;
	public String getNome() {
		return nome;
	}

	private long id;
	private long anoDeInicio;
	
	public void setId(long id) {
		this.id = id;
	}

	public Projeto(long id, String nome, long anoDeInicio) {
		this.nome = nome;
		this.id = id;
		this.anoDeInicio = anoDeInicio;
	}
	
	public Projeto() {
	}
	
	public String toXML(){
		return new XStream().toXML(this);
	}

	public String toJson() {
		return new Gson().toJson(this);
	}
	
	

}
