package marcelodias.cadastroEstados.domain.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table( name = "TB_UF")
public class UF
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UF_sequence")
    @SequenceGenerator(name="UF_sequence", sequenceName = "SEQUENCE_UF", allocationSize = 1)
    @Column(name = "CODIGO_UF")
    private Long codigoUF;

    @Column(name = "NOME", length = 100 )
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(name = "SIGLA", length = 2 )
    @NotEmpty(message = "{campo.sigla.obrigatorio}")
    private String sigla;

    @Column(name = "STATUS", length = 1)
    @NotNull(message = "{campo.status.obrigatorio}")
    private Integer status;

    public Long getCodigoUF() {
        return codigoUF;
    }

    public void setCodigoUF(Long codigoUF) {
        this.codigoUF = codigoUF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
