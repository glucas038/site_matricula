package br.com.fiesc.admin_educacao.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"pessoa_id", "curso_id"})
})
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dtMatricula;

    public Matricula() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Date getDtMatricula() {
        return dtMatricula;
    }

    public void setDtMatricula(Date dtMatricula) {
        this.dtMatricula = dtMatricula;
    }

    @Override
    public String toString() {
        return "Matricula [id=" + id + ", pessoa=" + pessoa + ", curso=" + curso + ", dtMatricula=" + dtMatricula + "]";
    }
}
