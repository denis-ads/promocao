package br.com.developer.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "TIME_CORACAO", schema = "public")
@NamedQueries({@NamedQuery(name = "TimeCoracao.findAll", query = "Select t from TimeCoracao t")})
@XmlRootElement
@Cacheable
public class TimeCoracao implements Serializable {

	private static final long serialVersionUID = -3766288718737067014L;

	@Id
	@Column(name = "ID", nullable = false)
	@SequenceGenerator(name = "TIME_CORACAO_SEQ", sequenceName = "TIME_CORACAO_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "TIME_CORACAO_SEQ", strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 100)
	@Column(name = "NOME", length = 100)
	private String nome;

	@Version
	@Column(name = "version")
	private int version;

	
	public TimeCoracao() {
        super();
    }

    public TimeCoracao(Long id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		TimeCoracao other = (TimeCoracao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TimeCoracao [id=" + id + ", nome=" + nome + "]";
	}

}
