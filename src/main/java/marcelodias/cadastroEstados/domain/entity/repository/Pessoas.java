package marcelodias.cadastroEstados.domain.entity.repository;

import marcelodias.cadastroEstados.domain.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;



public interface Pessoas extends JpaRepository<Pessoa, Long>
{
    Pessoa findPessoaByLogin (String login);

    Pessoa findPessoaByNomeAndSobrenome (String nome, String sobrenome);


}
