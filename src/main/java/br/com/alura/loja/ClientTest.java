package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Projeto;

public class ClientTest {
	private HttpServer server;
	
	@Before
	public void iniciaServidor(){
		this.server = Servidor.inicializaServidor();
	}
	
	@After
	public void finalizaServidor(){
		server.stop();
	}
	
	@Test
	public void testaQueAConexaoComOServidorFunciona(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://www.mocky.io");
		
		String conteudo = target.path("/v2/52aaf5deee7ba8c70329fb7d").request().get(String.class);
		
		Assert.assertTrue(conteudo.contains("<rua>Rua Vergueiro 3185"));		
	}
	
	@Test
	public void testaQueAConexaoComOServidorFuncionaNoPathDeProjetos() {		
        Client client = ClientBuilder.newClient();        
        WebTarget target = client.target("http://localhost:8080");        
        String conteudo = target.path("/projetos/1").request().get(String.class); 
        Assert.assertTrue(conteudo.contains("<nome>Minha loja"));
    }	
	
	@Test
    public void testaQueBuscarUmCarrinhoTrasUmCarrinho() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        String conteudo = target.path("/carrinhos/1").request().get(String.class);
        Carrinho fromXML = (Carrinho) new XStream().fromXML(conteudo);
        Assert.assertEquals("Rua Vergueiro 3185, 8 andar",fromXML.getRua());
    }
	
	@Test
    public void testaQueBuscarUmProjetoTrazOProjetoEsperado() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        String conteudo = target.path("/projetos/2").request().get(String.class);
        Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
        Assert.assertEquals("Alura", projeto.getNome());
    }
}
