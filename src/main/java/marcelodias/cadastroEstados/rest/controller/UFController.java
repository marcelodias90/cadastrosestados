package marcelodias.cadastroEstados.rest.controller;


import lombok.AllArgsConstructor;
import marcelodias.cadastroEstados.domain.entity.UF;
import marcelodias.cadastroEstados.domain.entity.repository.UFs;
import marcelodias.cadastroEstados.exception.ExistenteException;
import marcelodias.cadastroEstados.exception.RegradeNegocioException;
import marcelodias.cadastroEstados.service.impl.UFServiceImpl;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/uf")
@AllArgsConstructor
public class UFController
{
    private UFs ufs;

    private UFServiceImpl service;


    @GetMapping("{CODIGO_UF}")
    public UF getUFById(@PathVariable Long id)
    {
        return  ufs.findById(id).orElseThrow(()-> new RegradeNegocioException( " UF não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UF save(@RequestBody  @Valid UF uf)
    {
        service.converterString(uf);
        service.existenteNomeSigla(uf);
        return uf;
    }

    @PutMapping("{CODIGO_UF}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable("CODIGO_UF") Long codigo_uf, @RequestBody  @Valid UF uf)
    {
        ufs.findById(codigo_uf).map(ufExistente->{ uf.setCodigoUF(ufExistente.getCodigoUF());
                                                ufs.save(uf); return  ufExistente;
        }).orElseThrow(()-> new RegradeNegocioException("UF não encontrado"));
    }

    @GetMapping
    public List<UF> find( UF ufFiltro)
    {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(ufFiltro, matcher);
        return ufs.findAll(example);
    }


}
