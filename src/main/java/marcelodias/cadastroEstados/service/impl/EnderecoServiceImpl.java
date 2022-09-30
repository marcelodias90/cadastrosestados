package marcelodias.cadastroEstados.service.impl;

import marcelodias.cadastroEstados.domain.entity.Endereco;
import marcelodias.cadastroEstados.domain.entity.repository.Enderecos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
@Controller
public class EnderecoServiceImpl
{
    @Autowired
    private Enderecos enderecos;


    public Endereco converterString(Endereco endereco)
    {
        String nome_Rua = endereco.getNome_Rua().toUpperCase();
        String numero = endereco.getNumero().toUpperCase();
        String complemento = endereco.getComplemento().toUpperCase();
        endereco.setNome_Rua(nome_Rua);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        return endereco;

    }
}
