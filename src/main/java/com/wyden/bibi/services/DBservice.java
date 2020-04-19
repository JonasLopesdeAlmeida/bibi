package com.wyden.bibi.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyden.bibi.model.Categoria;
import com.wyden.bibi.model.Cliente;
import com.wyden.bibi.model.Emprestimo;
import com.wyden.bibi.model.Endereco;
import com.wyden.bibi.model.ItemEmprestimo;
import com.wyden.bibi.model.Livro;
import com.wyden.bibi.model.enums.TipoCliente;
import com.wyden.bibi.repositories.CategoriaRepository;
import com.wyden.bibi.repositories.ClienteRepository;
import com.wyden.bibi.repositories.EmprestimoRepository;
import com.wyden.bibi.repositories.EnderecoRepository;
import com.wyden.bibi.repositories.ItemEmprestimoRepository;
import com.wyden.bibi.repositories.LivroRepository;

@Service
public class DBservice {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private EmprestimoRepository emprestimoRepository;
	
	@Autowired
	private ItemEmprestimoRepository itememprestimoRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Categoria cat1 = new Categoria(null, "Análise e Desenvolvimento de Sistema");
		Categoria cat2 = new Categoria(null, "Egenharia Elétrica");
		Categoria cat3 = new Categoria(null, "Segurança da Informação");
		Categoria cat4 = new Categoria(null, "Egenharia Civil");
		Categoria cat5 = new Categoria(null, "Egenharia de Produção");
		Categoria cat6 = new Categoria(null, "Designer");
		Categoria cat7 = new Categoria(null, "Gestão de Tecnologia da Informação");
		Categoria cat8 = new Categoria(null, "Administração");
		
		
		
		Livro L1 = new Livro(null, 4, "ENGENHARIA DE SOFTWARE - 9ªED.(2011)", "Ian Sommerville", "Pearson Universidades", "9788579361081");
		Livro L2 = new Livro(null, 3, "REDES DE COMPUTADORES - 5ªED.(2011)", "Tanenbaum", "Pearson Universidades", "9788576059240");
		Livro L3 = new Livro(null, 5, "Análise estrutural ", "Kassimali, Aslam", "Cengage Learning BR", "9788522118175");
		Livro L4 = new Livro(null, 6, "ANALISE BASICA DE CIRCUITOS PARA ENGENHARIA - 10ªED.(2013)", "J. David Irwin; R. Mark Nelms ", "LTC", "9788521621805");
		Livro L5 = new Livro(null, 2, "O codificador limpo", "Bob Martin ", "Alta Books", "978-85-7608-647-5");
		Livro L6 = new Livro(null, 6, "LOGICA DE PROGRAMAÇAO: A CONSTRUÇAO DE ALGORITMOS E ESTRUTURAS DE DADOS - 3ªED.(2005)", " Andre Luiz Villar Forbellone; Henri Frederico Eberspacher ", "Pearson Universidades", "978-85-7605-024-7");
		Livro L7 = new Livro(null, 4, "CONSTRUÇAO CIVIL / TEORIA E PRATICA: TOPOGRAFIA", "Us Navy", "Hemus", "978-85-2890-551-9");
		Livro L8 = new Livro(null, 2, "Descobrindo o LINUX: ENTENDA O SISTEMA...3ªED.(2012) ", "Joao Eriberto Mota Filho", "Novatec", "978-88-7522-278-2");
		Livro L9 = new Livro(null, 2, "INTRODUÇAO A ENGENHARIA DE PRODUÇAO: CONCEITOS E CASOS PRATICOS - 1ªED.(2016)", "Orlando Roque da Silva", " LTC", "9788521627173");
		Livro L10 = new Livro(null, 2, "PESQUISA QUALITATIVA EM ENGENHARIA DE PRODUÇAO E GESTAO DE OPERAÇOES - 1ªED.(2018)", "Davi Nakano", "Atlas", "9788597018608");
		Livro L11 = new Livro(null, 2, "INTRODUÇAO A TEORIA GERAL DA ADMINISTRAÇAO 9ª EDIÇAO 2014 - 9ªED.(2014)", "Idalberto Chiavenato ", "Manole", "9788520436691");
		Livro L12 = new Livro(null, 2, "DECOR YEAR BOOK BRASIL VOL. 12: ANUARIO BRASILEIRO DOS DESIGNERS DE INTERIORES - 1ªED.(2006)", "Antonio Carlos Gouveia Junior ", "Decor", "9788599742044");
		Livro L13 = new Livro(null, 2, "TECNOLOGIA DA INFORMAÇAO: PLANEJAMENTO E GESTAO DE ESTRATEGIAS - 1ªED.(2008)", "Fernando Jose Barbin Laurindo ", "Atlas", "9788522451166");
		Livro L14 = new Livro(null, 2, "GESTAO DE TECNOLOGIA DA INFORMAÇAO: GOVERNANÇA DE TI - ARQUITETURA E ALINHAMENTO ENTRE SISTEMAS DE INFORMAÇAO E O NEGOCIO - 1ªED.(2011)", "Karoll Haussler Carneiro Ramos", "LTC", "9788521617723");
		Livro L15 = new Livro(null, 2, "INTRODUÇAO A ENGENHARIA CIVIL - 1ªED.(2016)", "Edward S. Neumann ", "Gen", "9788535271836");
		
		cat1.getLivros().addAll(Arrays.asList(L1,L2,L5,L6,L8));
		cat2.getLivros().addAll(Arrays.asList(L3,L4,L7));
		cat3.getLivros().addAll(Arrays.asList(L8));
		cat4.getLivros().addAll(Arrays.asList(L15));
		cat5.getLivros().addAll(Arrays.asList(L9,L10));
		cat6.getLivros().addAll(Arrays.asList(L12));
		cat7.getLivros().addAll(Arrays.asList(L13,L14));
		cat8.getLivros().addAll(Arrays.asList(L11));
		
		L1.getCategorias().addAll(Arrays.asList(cat1));
		L2.getCategorias().addAll(Arrays.asList(cat1));
		L5.getCategorias().addAll(Arrays.asList(cat1));
		L6.getCategorias().addAll(Arrays.asList(cat1));
		L8.getCategorias().addAll(Arrays.asList(cat1));
		
		L3.getCategorias().addAll(Arrays.asList(cat2));
		L4.getCategorias().addAll(Arrays.asList(cat2));
		L7.getCategorias().addAll(Arrays.asList(cat2));
		
		L8.getCategorias().addAll(Arrays.asList(cat3));
		L15.getCategorias().addAll(Arrays.asList(cat4));
		
		L9.getCategorias().addAll(Arrays.asList(cat5));
		L10.getCategorias().addAll(Arrays.asList(cat5));
		
		L12.getCategorias().addAll(Arrays.asList(cat6));
		
		L13.getCategorias().addAll(Arrays.asList(cat7));
		L14.getCategorias().addAll(Arrays.asList(cat7));
		
		L11.getCategorias().addAll(Arrays.asList(cat8));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8));
		livroRepository.saveAll(Arrays.asList(L1,L2,L3,L4,L5,L6,L7,L8,L9,L10,L11,L12,L13,L14,L15));
	
		
		Cliente cli1 = new Cliente(null, "Jonas Lopes de Almeida", "181016504", "11093891750", "jmdlopes.almeida@gmail.com", TipoCliente.ALUNO);
	   //ligando o cliente aos telefones dele.
		cli1.getTelefones().addAll(Arrays.asList("98984967055","98988665858"));
	    Endereco e1 = new Endereco(null, "Rua n", "9", "Quadra 10", "Planalto Anil III", "65050883", cli1);
	
	    cli1.getEnderecos().addAll(Arrays.asList(e1));
	    
	    clienteRepository.saveAll(Arrays.asList(cli1));
	    enderecoRepository.saveAll(Arrays.asList(e1));
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm" );
	    
	    Emprestimo emp1 = new Emprestimo(null, sdf.parse("10/04/2020 12:30"), cli1, e1);
	 
	    cli1.getEmprestimos().addAll(Arrays.asList(emp1));
	     
	    emprestimoRepository.saveAll(Arrays.asList(emp1));
	
	    ItemEmprestimo iep1 = new ItemEmprestimo(L5, emp1, 0.0, 1, 0.0);
	    
	    emp1.getItens().addAll(Arrays.asList(iep1));
	    L5.getItens().addAll(Arrays.asList(iep1));
	    
	    itememprestimoRepository.saveAll(Arrays.asList(iep1));
		
	}
	
}
