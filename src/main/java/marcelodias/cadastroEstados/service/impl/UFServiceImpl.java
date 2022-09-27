package marcelodias.cadastroEstados.service.impl;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import marcelodias.cadastroEstados.domain.entity.UF;
import marcelodias.cadastroEstados.domain.entity.repository.UFs;

import marcelodias.cadastroEstados.exception.ExistenteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Controller
public class UFServiceImpl
{
    @Autowired
    private UFs ufs;


    public UF converterString(UF uf)
        {
            String nome = uf.getNome().toUpperCase();
            String sigla = uf.getSigla().toUpperCase();
            uf.setNome(nome);
            uf.setSigla(sigla);
            return uf;
        }

    public UF existenteNomeSigla(UF uf)
    {
        boolean existeNome = ufs.existsByNome(uf.getNome());
        boolean existeSigla = ufs.existsBySigla(uf.getSigla());
        if(existeNome != true)
        {
            if(existeSigla != true)
            {
                ufs.save(uf);
                return uf;
            }
            else {
                throw new ExistenteException("Sigla da UF existente");
            }
        }
        else{
            throw new ExistenteException("Nome da UF existente");
        }


    }



}
