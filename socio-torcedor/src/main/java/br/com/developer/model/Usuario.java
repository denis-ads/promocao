package br.com.developer.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "USUARIO", schema = "public")
@NamedQueries({
		@NamedQuery(name = "Usuario.findAll", query = "Select u from Usuario u"),
		@NamedQuery(name = "Usuario.findByTimeCoracaoId", query = "Select u from Usuario u where u.timeCoracao.id = :timeCoracaoId"),
		@NamedQuery(name = "Usuario.findbyEmail", query = "Select u from Usuario u where u.email = :email")
		})
@XmlRootElement
public class Usuario implements Serializable {

	private static final long serialVersionUID = 5474663631977273685L;

	@Id
	@Column(name = "ID", nullable = false)
	@SequenceGenerator(name = "USUARIO_SEQ", sequenceName = "USUARIO_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "USUARIO_SEQ", strategy = GenerationType.IDENTITY)
	private Long id;

    @Column(name = "NOME", length = 100)
    @Size(max = 100)
    @NotNull
    private String nome;

    @Column(name = "EMAIL", length = 50, unique=true)
    @Email
	@Size(max = 50)
	@NotNull
	private String email;

	@Column(name = "NASCIMENTO")
	@Temporal(TemporalType.DATE)
	private Date nascimento;

	@ManyToOne
	@JoinColumn(name = "timeCoracao")
	private TimeCoracao timeCoracao;

	@Version
	@Column(name = "version")
	private int version;

	public Usuario() {
        super();
    }

    public Usuario(String nome, String email, Date nascimento) {
        super();
        this.nome = nome;
        this.email = email;
        this.nascimento = nascimento;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public TimeCoracao getTimeCoracao() {
		return timeCoracao;
	}

	public void setTimeCoracao(TimeCoracao timeCoracao) {
		this.timeCoracao = timeCoracao;
	}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", nascimento=" + nascimento + ", timeCoracao=" + timeCoracao
            + ", version=" + version + "]";
    }

}
