package marcelodias.cadastroEstados.rest.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacaoEnderecoDTO
{
    Long codigoEndereco;
    String nome_Rua;
    String numero;
    String complemento;
    Integer cep;
    String  bairro;
    String municipio;
    String uf;
    String nome;
    String sobrenome;

}
