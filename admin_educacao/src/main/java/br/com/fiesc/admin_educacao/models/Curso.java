package br.com.fiesc.admin_educacao.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@NotNull
	private Integer vagasTotais;
	@NotNull
	private Integer idadeMin;
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dtInicio;
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dtTermino;
	@OneToMany(mappedBy = "curso")
	private Set<Matricula> matriculas = new HashSet<Matricula>();

	public Curso() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVagasTotais() {
		return vagasTotais;
	}

	public void setVagasTotais(Integer vagasTotais) {
		this.vagasTotais = vagasTotais;
	}

	public Integer getIdadeMin() {
		return idadeMin;
	}

	public void setIdadeMin(Integer idadeMin) {
		this.idadeMin = idadeMin;
	}

	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Date getDtTermino() {
		return dtTermino;
	}

	public void setDtTermino(Date dtTermino) {
		this.dtTermino = dtTermino;
	}

	public Set<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(Set<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nome=" + nome + ", vagasTotais=" + vagasTotais + ", idadeMin=" + idadeMin
				+ ", dtInicio=" + dtInicio + ", dtTermino=" + dtTermino + "]";
	}

}
