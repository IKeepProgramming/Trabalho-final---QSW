package model.entity;

import java.sql.Time;

public class turmaDisciplina {
	
	private String nomeDisciplina;
	private String professorDisciplina;
	private String salaTurma;
	
	public turmaDisciplina() {
		
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getProfessorDisciplina() {
		return professorDisciplina;
	}

	public void setProfessorDisciplina(String professorDisciplina) {
		this.professorDisciplina = professorDisciplina;
	}

	public String getSalaTurma() {
		return salaTurma;
	}

	public void setSalaTurma(String salaTurma) {
		this.salaTurma = salaTurma;
	}
	
	
}
