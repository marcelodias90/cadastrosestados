package marcelodias.cadastroEstados.domain.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="TB_BAIRRO")
public class Bairro
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Bairro_sequence")
    @SequenceGenerator(name="Bairro_sequence", sequenceName = "SEQUENCE_BAIRRO", allocationSize = 1)
    @Column(name = "CODIGO_BAIRRO")
    private  Long codigoBairro;

    @Column(name = "NOME")
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(name = "STATUS")
    @NotNull(message = "{campo.status.obrigatorio}")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "CODIGO_MUNICIPIO")
    private Municipio municipio;

    public Long getCodigoBairro() {
        return codigoBairro;
    }

    public void setCodigoBairro(Long codigoBairro) {
        this.codigoBairro = codigoBairro;
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

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }
}
