package marcelodias.cadastroEstados.domain.entity.repository;

import marcelodias.cadastroEstados.domain.entity.UF;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UFs extends JpaRepository<UF, Long>
{
    boolean existsByNome(String nome);

    boolean existsBySigla(String sigla);

}
