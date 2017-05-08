package br.com.developer.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

//@Audited
@Entity
@Table(name = "CAMPANHA", schema = "public")
@NamedQueries({
		@NamedQuery(name = "Campanha.findAll", query = "Select c from Campanha c"),
		@NamedQuery(name = "Campanha.findByTimeCoracaoId", query = "Select c from Campanha c where c.timeCoracao.id = :timeCoracaoId"),
		@NamedQuery(name = "Campanha.campanhasAtivas", query = "Select c from Campanha c where c.fimVigencia >= :dataAtual")})
@XmlRootElement
public class Campanha implements Serializable {

	private static final long serialVersionUID = 1866472873357418589L;

	@Id
	@Column(name = "ID", nullable = false)
	@SequenceGenerator(name = "CAMPANHA_SEQ", sequenceName = "CAMPANHA_ID_SEQ", allocationSize = 1, initialValue = 1)
	@GeneratedValue(generator = "CAMPANHA_SEQ", strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 100)
	@Column(name = "NOME", length = 100)
	private String nome;

	@Column(name = "INICIO_VIGENCIA")
	@Temporal(TemporalType.DATE)
	private Date inicioVigencia;

	@Column(name = "FIM_VIGENCIA")
	@Temporal(TemporalType.DATE)
	private Date fimVigencia;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "timeCoracao")
	private TimeCoracao timeCoracao;

	@Version
	@Column(name = "version")
	private int version;

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
	public Date getInicioVigencia() {
		return inicioVigencia;
	}
	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}
	public Date getFimVigencia() {
		return fimVigencia;
	}
	public void setFimVigencia(Date fimVigencia) {
		this.fimVigencia = fimVigencia;
	}
	public TimeCoracao getTimeCoracao() {
		return timeCoracao;
	}
	public void setTimeCoracao(TimeCoracao timeCoracao) {
		this.timeCoracao = timeCoracao;
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
		Campanha other = (Campanha) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Campanha [id=" + id + ", nome=" + nome + ", inicioVigencia="
				+ inicioVigencia + ", fimVigencia=" + fimVigencia
				+ ", timeCoracao=" + timeCoracao + "]";
	}

}
