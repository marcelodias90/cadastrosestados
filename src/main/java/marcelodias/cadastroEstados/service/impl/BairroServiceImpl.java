package marcelodias.cadastroEstados.service.impl;

import marcelodias.cadastroEstados.domain.entity.Bairro;
import marcelodias.cadastroEstados.domain.entity.repository.Bairros;
import marcelodias.cadastroEstados.exception.ExistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;



@Service
@Controller
public class BairroServiceImpl
{
    @Autowired
    private Bairros bairros;

        public Bairro converterString(Bairro bairro)
        {
            String nome = bairro.getNome().toUpperCase();
            bairro.setNome(nome);
            return bairro;
        }

        public  Bairro verificarBairro (Bairro bairro)
        {
            Bairro bairroPesquisa = bairros.findPessoaByNome(bairro.getNome());
            if(bairroPesquisa != null)
            {
                if (bairroPesquisa.getMunicipio().getCodigoMunicipio() != bairro.getMunicipio().getCodigoMunicipio()) {
                    return bairro;
                } else {
                    throw new ExistenteException("Já existe esse bairro no municipio do codigo  " + bairro.getMunicipio().getCodigoMunicipio());
                }
            }
            else
            {
                return bairro;
            }
        }

        public Bairro updateBairro(Bairro bairro)
        {
            Bairro bairroPesquisa = bairros.findPessoaByNome(bairro.getNome());

            if(bairroPesquisa.getMunicipio().getCodigoMunicipio() != bairro.getMunicipio().getCodigoMunicipio() )
            {
                return bairro;
            }
            else
            {
                throw new ExistenteException("Já existe esse bairro no municipio do codigo  "+  bairro.getMunicipio().getCodigoMunicipio());
            }
        }
}
