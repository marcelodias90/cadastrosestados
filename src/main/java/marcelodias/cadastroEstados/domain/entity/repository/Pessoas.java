package marcelodias.cadastroEstados.domain.entity.repository;

import marcelodias.cadastroEstados.domain.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface Pessoas extends JpaRepository<Pessoa, Long>, JpaSpecificationExecutor<Pessoa>
{
    Pessoa findPessoaByLogin (String login);

    Pessoa findPessoaByNomeAndSobrenome (String nome, String sobrenome);


}
