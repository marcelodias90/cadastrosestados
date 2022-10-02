package marcelodias.cadastroEstados.rest.controller;


import marcelodias.cadastroEstados.domain.entity.Endereco;
import marcelodias.cadastroEstados.domain.entity.repository.Enderecos;
import marcelodias.cadastroEstados.exception.RegradeNegocioException;
import marcelodias.cadastroEstados.rest.controller.dto.InformacaoEnderecoDTO;
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
    public InformacaoEnderecoDTO getEnderecoById(@PathVariable("CODIGO_ENDERECO") Long id)
    {
        return enderecos.findById(id).map(m-> converter(m)).orElseThrow(()-> new RegradeNegocioException("Endereço não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco save(@RequestBody @Valid Endereco endereco)
    {
        service.converterString(endereco);
        service.enderecoExistente(endereco);
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

    private InformacaoEnderecoDTO converter(Endereco endereco)
    {
        return InformacaoEnderecoDTO.builder().codigoEndereco(endereco.getCodigoEndereco()).nome_Rua(endereco.getNomeRua()).numero(endereco.getNumero())
                .complemento(endereco.getComplemento()).cep(endereco.getCep()).bairro(endereco.getBairro().getNome()).municipio(endereco.getBairro().getMunicipio().getNome())
                .uf(endereco.getBairro().getMunicipio().getUf().getNome()).nome(endereco.getPessoa().getNome()).sobrenome(endereco.getPessoa().getSobrenome()).build();
    }
}
