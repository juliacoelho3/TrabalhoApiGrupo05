package br.org.serratec.trabalhoGrupo05.dto;

import br.org.serratec.trabalhoGrupo05.model.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProdutoDto(
		Long id,
		@NotBlank(message = "Este campo não pode ser vazio...")
		String nome,
		@NotBlank(message = "Este campo não pode ser vazio...")
		String categoria,
		@NotNull(message = "Digite um valor valido...")
		@Positive
		Double valor,
		String descricao) {
	
	public Produto toEntity() {
		return new Produto (this.id, this.nome, this.categoria, this.valor, this.descricao);
	}

}
