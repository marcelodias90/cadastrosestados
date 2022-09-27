package marcelodias.cadastroEstados.rest.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesMunicipioDTO
{
    private Long codigoMunicipio;
    private String nome;
    private String uf;
    private String sigla;
    private Integer status;

}
