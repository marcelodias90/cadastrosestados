package marcelodias.cadastroEstados.service.impl;

import marcelodias.cadastroEstados.domain.entity.Pessoa;
import marcelodias.cadastroEstados.domain.entity.repository.Pessoas;
import marcelodias.cadastroEstados.exception.ExistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
@Controller
public class PessoaServiceImpl
{
    @Autowired
    private Pessoas pessoas;

    public Pessoa converterString(Pessoa pessoa)
    {
        String nome = pessoa.getNome().toUpperCase().replace(" ", "");
        String sobrenome = pessoa.getSobrenome().toUpperCase().replace(" ", "");
        String login = pessoa.getLogin().replace(" ", "");
        pessoa.setNome(nome);
        pessoa.setSobrenome(sobrenome);
        pessoa.setLogin(login);
        return pessoa;
    }

    public Pessoa userExistente(Pessoa pessoa)
    {

        Pessoa existeLogin = pessoas.findPessoaByLogin(pessoa.getLogin());
        Pessoa existeUsuario = pessoas.findPessoaByNomeAndSobrenome(pessoa.getNome(), pessoa.getSobrenome());

        if (existeUsuario == null)
        {
            if (existeLogin == null)
            {
                pessoas.save(pessoa);
                return pessoa;
            } else {
                throw new ExistenteException("Login já existe");
            }
        }
        else {
            throw new ExistenteException("Pessoa já existe");
        }
    }

    public Pessoa verificarPessoa (Pessoa pessoa)
    {

        Pessoa existeUsuario = pessoas.findPessoaByNomeAndSobrenome(pessoa.getNome(), pessoa.getSobrenome());

        if (existeUsuario != null )
        {
            if(existeUsuario.getCodigoPessoa() == pessoa.getCodigoPessoa())
            {
                verificarLogin(pessoa);
                return pessoa;
            }
            else
            {
                throw new ExistenteException("Pessoa já existe");
            }
        }
        else
        {
            verificarLogin(pessoa);
            return pessoa;
        }

    }

    private Pessoa verificarLogin (Pessoa pessoa)
    {
        Pessoa existeLogin = pessoas.findPessoaByLogin(pessoa.getLogin());

        if (existeLogin != null )
        {
            if(existeLogin.getCodigoPessoa() == pessoa.getCodigoPessoa())
            {
                pessoas.save(pessoa);
                return pessoa;
            }
            else {
                throw new ExistenteException("Login já existe");
            }
        }
        else
        {
            pessoas.save(pessoa);
            return pessoa;
        }

    }


}
