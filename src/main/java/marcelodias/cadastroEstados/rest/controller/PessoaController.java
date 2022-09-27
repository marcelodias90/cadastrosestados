package marcelodias.cadastroEstados.rest.controller;

import lombok.AllArgsConstructor;
import marcelodias.cadastroEstados.domain.entity.Pessoa;
import marcelodias.cadastroEstados.domain.entity.repository.Pessoas;
import marcelodias.cadastroEstados.exception.RegradeNegocioException;
import marcelodias.cadastroEstados.rest.controller.dto.InformacaoPessoaDTO;
import marcelodias.cadastroEstados.service.impl.PessoaServiceImpl;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/pessoa")
@AllArgsConstructor
public class PessoaController
{
    private Pessoas pessoas;
    private final PasswordEncoder passwordEncoder;

    private PessoaServiceImpl service;


    @GetMapping("{CODIGO_PESSOA}")
    public InformacaoPessoaDTO getPessoaById(@PathVariable("CODIGO_PESSOA") Long id)
    {
        return pessoas.findById(id).map(p -> converter(p)).orElseThrow(()-> new RegradeNegocioException("Pessoa não encontrada"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa save (@RequestBody @Valid Pessoa pessoa)
    {
        String senhaCriptografada = passwordEncoder.encode(pessoa.getSenha());
        pessoa.setSenha(senhaCriptografada);
        service.converterString(pessoa);
        service.userExistente(pessoa);
        return pessoa;
    }

    @PutMapping("{CODIGO_PESSOA}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("CODIGO_PESSOA")Long id, @RequestBody @Valid Pessoa pessoa)
    {
        pessoas.findById(id).map(pessoaExistente ->{ pessoa.setCodigoPessoa(pessoaExistente.getCodigoPessoa());
                                                     String senhaCriptografada = passwordEncoder.encode(pessoa.getSenha());
                                                       pessoa.setSenha(senhaCriptografada);
                                                       service.converterString(pessoa);
                                                       service.verificarPessoa(pessoa); return pessoaExistente;
        }).orElseThrow(()-> new RegradeNegocioException("Pessoa não encontrada"));
    }

    @GetMapping
    public List<Pessoa> find (Pessoa pessoaFiltro)
    {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(pessoaFiltro, matcher);
        return pessoas.findAll(example);
    }

    private InformacaoPessoaDTO converter(Pessoa pessoa)
    {
        return  InformacaoPessoaDTO.builder().codigoPessoa(pessoa.getCodigoPessoa()).nome(pessoa.getNome()).sobrenome(pessoa.getSobrenome())
                .idade(pessoa.getIdade()).status(pessoa.getStatus()).build();
    }
}

