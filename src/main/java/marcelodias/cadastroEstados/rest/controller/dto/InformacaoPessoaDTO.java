package marcelodias.cadastroEstados.rest.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import marcelodias.cadastroEstados.domain.entity.Pessoa;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacaoPessoaDTO
{
    private Long codigoPessoa;
    private String nome;
    private String sobrenome;
    private Integer idade;
    private Integer status;


}
