package marcelodias.cadastroEstados.rest.controller;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import marcelodias.cadastroEstados.domain.entity.Endereco;
import marcelodias.cadastroEstados.domain.entity.repository.Enderecos;
import marcelodias.cadastroEstados.exception.RegradeNegocioException;
import marcelodias.cadastroEstados.service.impl.EnderecoServiceImpl;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController
{
    private Enderecos enderecos;

    private EnderecoServiceImpl service;

    public EnderecoController(Enderecos enderecos, EnderecoServiceImpl service) {
        this.enderecos = enderecos;
        this.service = service;
    }

    @GetMapping("{CODIGO_ENDERECO}")
    public Endereco getEnderecoById(@PathVariable Long id)
    {
        return enderecos.findById(id).orElseThrow(()-> new RegradeNegocioException("Endereço não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco save(@RequestBody @Valid Endereco endereco)
    {
        service.converterString(endereco);
        return enderecos.save(endereco);
    }

    @PutMapping("{CODIGO_ENDERECO}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("CODIGO_ENDERECO") Long id, @Valid Endereco endereco)
    {
        enderecos.findById(id).map(enderecoExistente->{endereco.setCodigoEndereco(enderecoExistente.getCodigoEndereco());
                                                        enderecos.save(endereco); return  enderecoExistente;
        }).orElseThrow(()-> new RegradeNegocioException("Endereço não encontrado"));
    }

    @GetMapping
    public List<Endereco> find (Endereco enderecoFiltro)
    {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(enderecoFiltro, matcher);
        return enderecos.findAll(example);
    }
}
