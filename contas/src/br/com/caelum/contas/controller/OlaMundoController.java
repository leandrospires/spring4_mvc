package br.com.caelum.contas.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OlaMundoController {
	private String data;
	
	
	@RequestMapping("/olaMundoSpring")
	public String execute() {

		Date date = new Date();
		
		data = date.toString();

		System.out.println("Teste Controller OK Spring MVC OK: " + data);

		return "ok";
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}

}
