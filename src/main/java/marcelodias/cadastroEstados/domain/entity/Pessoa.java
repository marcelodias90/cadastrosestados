package marcelodias.cadastroEstados.domain.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name="TB_PESSOA")
public class Pessoa
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Pessoa_sequence")
    @SequenceGenerator(name="Pessoa_sequence", sequenceName = "SEQUENCE_PESSOA", allocationSize = 1)
    @Column(name = "CODIGO_PESSOA")
    private Long codigoPessoa;

    @Column(name = "NOME")
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(name = "SOBRENOME")
    @NotEmpty(message = "{campo.sobrenome.obrigatorio}")
    private String sobrenome;

    @Column(name = "IDADE")
    @NotNull(message = "{campo.idade.obrigatorio}")
    private Integer idade;

    @Column(name = "LOGIN")
    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String login;

    @Column(name = "SENHA", length = 100)
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String senha;

    @Column(name = "STATUS")
    @NotNull(message = "{campo.status.obrigatorio}")
    private Integer status;

    public Long getCodigoPessoa() {
        return codigoPessoa;
    }

    public void setCodigoPessoa(Long codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
