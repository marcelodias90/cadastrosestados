package marcelodias.cadastroEstados.rest.controller;


import marcelodias.cadastroEstados.domain.entity.Bairro;

import marcelodias.cadastroEstados.domain.entity.repository.Bairros;
import marcelodias.cadastroEstados.exception.RegradeNegocioException;
import marcelodias.cadastroEstados.service.impl.BairroServiceImpl;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bairro")

public class BairroController
{
    private Bairros bairros;


    private BairroServiceImpl service;

    public BairroController(Bairros bairros, BairroServiceImpl service) {
        this.bairros = bairros;
        this.service = service;
    }


    @GetMapping("{CODIGO_BAIRRO}")
    public Bairro getBairroById(@PathVariable Long id)
    {
        return bairros.findById(id).orElseThrow(() -> new RegradeNegocioException("Bairro não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bairro save(@RequestBody @Valid Bairro bairro)
    {
        service.converterString(bairro);
        service.verificarBairro(bairro);
        return bairros.save(bairro);
    }

    @PutMapping("{CODIGO_BAIRRO}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("CODIGO_BAIRRO") Long id, @RequestBody @Valid Bairro bairro)
    {
        bairros.findById(id).map(bairroExistente->{bairro.setCodigoBairro(bairroExistente.getCodigoBairro());
                                                       service.converterString(bairro);
                                                       bairros.save(bairro); return bairroExistente;
        }).orElseThrow(()-> new RegradeNegocioException("Bairro não encontrado"));
    }

    @GetMapping
    public List<Bairro> find ( Bairro bairroFiltro)
    {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(bairroFiltro, matcher);
        return bairros.findAll(example);
    }
}
