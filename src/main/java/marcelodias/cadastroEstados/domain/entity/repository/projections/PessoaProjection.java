package marcelodias.cadastroEstados.domain.entity.repository.projections;

public interface PessoaProjection
{
    Long getCodigoPessoa();
    String getNome();
    String getSobrenome();
    Integer getIdade();
    Integer getStatus();
}
