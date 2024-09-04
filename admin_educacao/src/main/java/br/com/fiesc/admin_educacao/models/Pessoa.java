package br.com.fiesc.admin_educacao.models;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@CPF(message = "CPF inválido")
	@NotBlank
	@Column(unique = true)
	private String cpf;
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dtNascimento;

	@OneToMany(mappedBy = "pessoa")
	private Set<Matricula> matriculas = new HashSet<Matricula>();

	public Pessoa() {
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Set<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(Set<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
	
	public Long calcularIdade(Date d) {

        java.util.Date dt = new java.util.Date(dtNascimento.getTime());
        LocalDate nascimento = dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate data = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        return (long) Period.between(nascimento, data).getYears();
		
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", dtNascimento=" + dtNascimento + "]";
	}

}
