package marcelodias.cadastroEstados.domain.entity.repository;


import marcelodias.cadastroEstados.domain.entity.Bairro;


import org.springframework.data.jpa.repository.JpaRepository;

public interface Bairros  extends JpaRepository<Bairro, Long>
{
    Bairro findPessoaByNome (String nome);
}
