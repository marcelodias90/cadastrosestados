package marcelodias.cadastroEstados.domain.entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="TB_ENDERECO")
public class Endereco
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Endereco_sequence")
    @SequenceGenerator(name="Endereco_sequence", sequenceName = "SEQUENCE_ENDERECO", allocationSize = 1)
    @Column(name = "CODIGO_ENDERECO")
    private Long codigoEndereco;

    @Column(name = "NOME_RUA")
    @NotEmpty(message = "{campo.nome_Rua.obrigatorio}")
    private String nome_Rua;

    @Column(name = "NUMERO")
    @NotEmpty(message = "{campo.numero.obrigatorio}")
    private String numero;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @Column(name = "CEP")
    @NotNull(message = "{campo.cep.obrigatorio}")
    private Integer cep;

    @ManyToOne
    @JoinColumn(name = "CODIGO_BAIRRO")
    private Bairro bairro;

    @ManyToOne
    @JoinColumn(name = "CODIGO_PESSOA")
    private  Pessoa pessoa;

    public Long getCodigoEndereco() {
        return codigoEndereco;
    }

    public void setCodigoEndereco(Long codigo_Endereco) {
        this.codigoEndereco = codigo_Endereco;
    }

    public String getNome_Rua() {
        return nome_Rua;
    }

    public void setNome_Rua(String nome_Rua) {
        this.nome_Rua = nome_Rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
