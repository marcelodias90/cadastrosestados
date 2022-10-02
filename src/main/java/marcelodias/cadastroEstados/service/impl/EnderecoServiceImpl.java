package marcelodias.cadastroEstados.service.impl;

import marcelodias.cadastroEstados.domain.entity.Endereco;
import marcelodias.cadastroEstados.domain.entity.repository.Enderecos;
import marcelodias.cadastroEstados.exception.ExistenteException;
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
        String nome_Rua = endereco.getNomeRua().toUpperCase();
        String numero = endereco.getNumero().toUpperCase();
        String complemento = endereco.getComplemento().toUpperCase();
        endereco.setNomeRua(nome_Rua);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        return endereco;

    }

    public Endereco enderecoExistente(Endereco endereco)
    {
        Endereco enderecoFiltro = enderecos.findEnderecoByNomeRuaAndNumero(endereco.getNomeRua(), endereco.getNumero());
        if(enderecoFiltro == null)
        {
            return endereco;
        }
        else {
                throw new ExistenteException("Já existe um cadastro neste endereço "+endereco.getNomeRua()+" "+endereco.getNumero());
        }
    }
}
