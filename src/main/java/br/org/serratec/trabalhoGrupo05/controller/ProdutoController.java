package br.org.serratec.trabalhoGrupo05.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.trabalhoGrupo05.dto.DtoReferencia;
import br.org.serratec.trabalhoGrupo05.dto.ProdutoDto;
import br.org.serratec.trabalhoGrupo05.service.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService servico;
	
	@GetMapping
	public ResponseEntity<List<ProdutoDto>> listar() {
		return ResponseEntity.ok(servico.listarTodos());	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> listarPorId(@PathVariable Long id){
		Optional<ProdutoDto> produto = servico.obterPorId(id);
		if(produto.isPresent()) {
			return ResponseEntity.ok(produto.get());
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/valor/acima")
	public ResponseEntity<List<ProdutoDto>> obterPorValorAcima(@RequestBody String valor){
		return ResponseEntity.ok(servico.obterValor(Double.valueOf(valor)));
	}
	
	@GetMapping("/valor/abaixo")
	public ResponseEntity<List<ProdutoDto>> obterPorValorAbaixo(@RequestBody String valor){
		return ResponseEntity.ok(servico.obterValorAbaixo(Double.valueOf(valor)));
	}
	
	@GetMapping("/valor/entre")
	public ResponseEntity <List<ProdutoDto>> obterPorValorEntre(@RequestBody DtoReferencia produto){
			return ResponseEntity.ok(servico.obterPorValorEntre(produto.v1(), produto.v2())); 
	}
	
	@GetMapping("/valor/crescente")
	public ResponseEntity <List<ProdutoDto>> obterPorValorCrescente(){
			return ResponseEntity.ok(servico.obterPorValorCrescente()); 
	}

	@GetMapping("valor_categoria")
    public ResponseEntity <List<ProdutoDto>> obterPorValorOuCategoria(@RequestBody ProdutoDto produto){
        return ResponseEntity.ok(servico.obterValorOuCategoria(produto.valor(), produto.categoria()));
    }
	
	@GetMapping("/categoria")
	public ResponseEntity<List<ProdutoDto>> obterPorCategoria(@RequestBody String categoria){
		return ResponseEntity.ok(servico.obterCategoria(categoria));
	}
	
	
	@GetMapping("/nome")
	public ResponseEntity<List<ProdutoDto>> obterPorNome(@RequestBody String nome){
		return ResponseEntity.ok(servico.obterNome(nome));
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoDto cadastrarProdutos(@Valid @RequestBody ProdutoDto produto) {
		return servico.inserirProduto(produto);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDto> alterarProdutos(@PathVariable Long id, @RequestBody ProdutoDto produtoAlterado){
		Optional<ProdutoDto> produto = servico.atualizarProduto(id, produtoAlterado);
		if(produto.isPresent()) {
		return ResponseEntity.ok(produto.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirProduto(@PathVariable Long id){
		if(!servico.deletarProduto(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
