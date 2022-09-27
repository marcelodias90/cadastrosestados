package marcelodias.cadastroEstados.rest.controller;

import marcelodias.cadastroEstados.domain.entity.Municipio;
import marcelodias.cadastroEstados.domain.entity.repository.Municipios;
import marcelodias.cadastroEstados.exception.RegradeNegocioException;
import marcelodias.cadastroEstados.rest.controller.dto.InformacoesMunicipioDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/municipio")
public class MunicipioController
{
    private Municipios municipios;

    public MunicipioController(Municipios municipios)
    {
        this.municipios = municipios;
    }

    @GetMapping("{CODIGO_MUNICIPIO}")
    public InformacoesMunicipioDTO getMunicipioById(@PathVariable("CODIGO_MUNICIPIO") Long id)
    {
        return municipios.findById(id).map(m-> converter(m)).orElseThrow(()-> new RegradeNegocioException("Municipio não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Municipio save(@RequestBody @Valid Municipio municipio)
    {
        return municipios.save(municipio);
    }

    @PutMapping("{CODIGO_MUNICIPIO}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  update(@PathVariable("CODIGO_MUNICIPIO") Long id, @RequestBody @Valid Municipio municipio)
    {
         municipios.findById(id).map(municipioExistente -> {municipio.setCodigoMunicipio(municipioExistente.getCodigoMunicipio());
                                                            municipios.save(municipio); return municipioExistente;
         }).orElseThrow(()-> new RegradeNegocioException("municipio não encontrado"));
    }

    @GetMapping
    public List<Municipio> find (Municipio municipioFiltro)
    {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(municipioFiltro, matcher);

        return municipios.findAll(example);
    }

    private InformacoesMunicipioDTO converter(Municipio municipio)
    {
          return InformacoesMunicipioDTO.builder().codigoMunicipio(municipio.getCodigoMunicipio())
                                                    .nome(municipio.getNome()).status(municipio.getStatus())
                                                    .uf(municipio.getUf().getNome()).sigla(municipio.getUf().getSigla()).build();

    }


}
