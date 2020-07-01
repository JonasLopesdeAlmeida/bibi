package com.wyden.bibi.services;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wyden.bibi.model.Cliente;
import com.wyden.bibi.model.Emprestimo;
import com.wyden.bibi.model.ItemEmprestimo;
import com.wyden.bibi.repositories.ClienteRepository;
import com.wyden.bibi.repositories.EmprestimoRepository;
import com.wyden.bibi.repositories.ItemEmprestimoRepository;
import com.wyden.bibi.security.UserSpringSecurity;
import com.wyden.bibi.services.exceptions.AuthorizationException;
import com.wyden.bibi.services.exceptions.ObjectNotFoundException;

@Service
public class EmprestimoService {
	
	
	@Autowired
	private LivroService livrorservice;
	
	@Autowired
	private EmprestimoRepository repo;

	@Autowired
	private ItemEmprestimoRepository itememprestimorepository;
	
	@Autowired
    private ClienteService clienteservice;
	
	@Autowired
    private ClienteRepository clienterepository;
	
	@Autowired
    private EmailService emailservice;
	
	
	public Emprestimo find(Integer id) {
		
		UserSpringSecurity user = UserService.authenticated();
		Cliente cliente = clienteservice.find(user.getId());
		if (!cliente.getId_cliente().equals(repo.findById(id).get().getCliente().getId_cliente())) {
			throw new AuthorizationException("Acesso negado.");
		}
		
		Optional<Emprestimo> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Emprestimo não encontrado! Id: " + id + ", Tipo: " + Emprestimo.class.getName()));
	}

	@Transactional
	public Emprestimo insert(Emprestimo obj) {
		obj.setId_emprestimo(null);
		obj.setInstante(new Date());
        obj.setCliente( clienteservice.find(obj.getCliente().getId_cliente()));
		
		// calculando e definindo a data de entrega do livro.
		Calendar cal = Calendar.getInstance();
		cal.setTime(obj.getInstante());
		cal.add(Calendar.DAY_OF_MONTH, 7);
		obj.setDatadeEntrega(cal.getTime());
		obj = repo.save(obj);
		
		//gerando multa 
		double multa = 0.0;
		
		if(Calendar.getInstance().after(obj.getDatadeEntrega())) {
			multa += 2.0;
			obj.setValor_multa(multa);
		}
		//adicionando itens 
		for (ItemEmprestimo ie : obj.getItens()) {
            ie.setLivro(livrorservice.find(ie.getLivro().getId_livro()));
			ie.setEmprestimo(obj);
		}
        //salvando todos os itens
		itememprestimorepository.saveAll(obj.getItens());
		//enviando email de confirmação do emprestimo.
		emailservice.sendOrderConfirmationEmail(obj);
		return obj;
	}
	
	public Page<Emprestimo> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSpringSecurity user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Cliente cliente =  clienteservice.find(user.getId());
		return repo.findByCliente(cliente, pageRequest);
	}

}
