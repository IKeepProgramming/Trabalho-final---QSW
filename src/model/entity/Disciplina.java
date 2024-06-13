package model.entity;

import java.sql.Time;

public class Disciplina {
	
	private String nomeDisciplina;
	private int numeroAulasDisciplina;
	private boolean requisitoDisciplina;
	private Time horarioInicioDisciplina;
	private Time horarioFimDisciplina;
	private int quantidadeMaximaAlunosDisciplina;
	
	public Disciplina() {
		
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public int getNumeroAulasDisciplina() {
		return numeroAulasDisciplina;
	}

	public void setNumeroAulasDisciplina(int numeroAulasDisciplina) {
		this.numeroAulasDisciplina = numeroAulasDisciplina;
	}

	public boolean isRequisitoDisciplina() {
		return requisitoDisciplina;
	}

	public void setRequisitoDisciplina(boolean requisitoDisplina) {
		this.requisitoDisciplina = requisitoDisplina;
	}

	public Time getHorarioInicioDisciplina() {
		return horarioInicioDisciplina;
	}

	public void setHorarioInicioDisciplina(Time horarioInicioDisciplina) {
		this.horarioInicioDisciplina = horarioInicioDisciplina;
	}

	public Time getHorarioFimDisciplina() {
		return horarioFimDisciplina;
	}

	public void setHorarioFimDisciplina(Time horarioFimDisciplina) {
		this.horarioFimDisciplina = horarioFimDisciplina;
	}

	public int getQuantidadeMaximaAlunosDisciplina() {
		return quantidadeMaximaAlunosDisciplina;
	}

	public void setQuantidadeMaximaAlunosDisciplina(int quantidadeMaximaAlunosDisciplina) {
		this.quantidadeMaximaAlunosDisciplina = quantidadeMaximaAlunosDisciplina;
	}

	
	

}
