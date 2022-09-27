package marcelodias.cadastroEstados.domain.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="TB_MUNICIPIO")
public class Municipio
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Municipio_sequence")
    @SequenceGenerator(name="Municipio_sequence", sequenceName = "SEQUENCE_MUNICIPIO", allocationSize = 1)
    @Column(name= "CODIGO_MUNICIPIO")
    private Long codigoMunicipio;



    @Column(name = "NOME")
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(name = "STATUS")
    @NotNull(message = "{campo.status.obrigatorio}")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "CODIGO_UF")
    private UF uf;

    public Long getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Long codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public UF getUf() {
        return uf;
    }

    public void setUf(UF uf) {
        this.uf = uf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
