package br.org.serratec.trabalhoGrupo05.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.trabalhoGrupo05.model.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long>{
	List<Produto> findByValorGreaterThan(Double valor);
	List<Produto> findByCategoriaContainingIgnoreCase(String valor);
	List<Produto> findByNomeContainingIgnoreCase(String nome);
	
	List<Produto> findByValorLessThan(Double valor);
	List<Produto> findByValorBetween(Double v1, Double v2);
	List<Produto> findAllByOrderByValorAsc();
	List<Produto> findByValorOrCategoriaContainingIgnoreCase(Double valor, String categoria);
}
