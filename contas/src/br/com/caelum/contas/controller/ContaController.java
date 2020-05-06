package br.com.caelum.contas.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.modelo.Conta;

@Controller
public class ContaController {
	
	private ContaDAO dao;

	@Autowired
	public ContaController(ContaDAO dao) {
		this.dao = dao;
	}
	
	
	@RequestMapping("/form")
	public String formulario() {

		return "formulario";
	}
	
	@RequestMapping("/adicionaConta")
	public String adiciona(@Valid Conta conta, BindingResult result) {
		
		//if (conta.getDescricao()==null || conta.getDescricao().equals("")) {
		if (result.hasErrors()) {
			return "formulario";
		}
		
		System.out.println("Conta add:" + conta.getDescricao());

		dao.adiciona(conta);
		//return "conta-adicionada";
		
		return "redirect:listaContas"; //melhor opção
	}

	@RequestMapping("/removeConta")
	public String remove(Conta conta) {
		dao.remove(conta);

		//return "forward:listaContas";
		return "redirect:listaContas"; //melhor opção

	}

	@RequestMapping("/listaContas")
	public ModelAndView lista() {
		List<Conta> contas = dao.lista();

		ModelAndView mv = new ModelAndView("conta/lista");
		mv.addObject("contas", contas);

		return mv;
	}

	@RequestMapping("/pagaConta")
	public String paga(Conta conta, HttpServletResponse response) {
		
		dao.paga(conta.getId());
		
		//response.setStatus(200);
		
		return "redirect:listaContas";
	}
	
	@RequestMapping("/mostraConta")
	public String mostra(Long id, Model model) {
		
		model.addAttribute("conta", dao.buscaPorId(id));
		
		return "conta/mostra";
	}
	
	@RequestMapping("/alteraConta")
	public String altera(Conta conta) {
		
	  dao.altera(conta);
	  
	  return "redirect:listaContas";
	}

}
