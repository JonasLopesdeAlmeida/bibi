package com.wyden.bibi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wyden.bibi.dto.LivroDTO;
import com.wyden.bibi.model.Categoria;
import com.wyden.bibi.model.ItemEmprestimo;
import com.wyden.bibi.model.Livro;
import com.wyden.bibi.model.enums.StatusLivro;
import com.wyden.bibi.repositories.CategoriaRepository;
import com.wyden.bibi.repositories.LivroRepository;
import com.wyden.bibi.services.exceptions.ObjectNotFoundException;

@Service
public class LivroService {
	@Autowired
	private LivroRepository repo;

	@Autowired
	private CategoriaRepository categoriarepository;

	
	
//	public Optional<Livro> obterPorId(Integer id) {
//
//		return repo.findById(id);
//	}
	
	
	public Livro find(Integer id) {

		Optional<Livro> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Livro não encontrado! Id: " + id + ", Tipo: " + Livro.class.getName()));
	}

	public Livro insert(Livro obj) {
		// o id precisa estar null para que esse metod entenda que e uma insercao e nao
		// uma atualizacao.
		obj.setId_livro(null);

		obj.setStatus(StatusLivro.DISPONIVEL);

		if (obj.getQuantidade() <= 0) {
			obj.setStatus(StatusLivro.INDISPONIVEL);
			update(obj);
		}

		obj = repo.save(obj);
		return obj;
	}

	public Livro update(Livro obj) {
		Livro newObj = find(obj.getId_livro());
		// chamando o metodo find caso nao exista o id ele me retorna a excecao.
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public Livro updatebookquantity(Livro obj) {
		//Livro newObj = find(obj.getId_livro());
		int obj2;
		ItemEmprestimo emp = new ItemEmprestimo();
		obj2 = obj.getQuantidade() - emp.getQuantidade();
		obj.setQuantidade(obj2);
		obj = update(obj);

		return repo.save(obj);
		
		
	}

	public Page<Livro> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {

		// PAGEREQUEST PREPARA AS REQUISICOES PARA FAZER A CONSULTA NO BANCO DE DADOS.
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		List<Categoria> categorias = categoriarepository.findAllById(ids);
		return repo.search(nome, categorias, pageRequest);

	}

	public Livro fromDTO(LivroDTO objDTO) {

		return new Livro(objDTO.getId_livro(), objDTO.getQuantidade(), objDTO.getNome(), objDTO.getAutor(),
				objDTO.getEditora(), objDTO.getIsbn(), objDTO.getStatus());
	}

	private void updateData(Livro newObj, Livro obj) {

		newObj.setQuantidade(obj.getQuantidade());

	}

}
