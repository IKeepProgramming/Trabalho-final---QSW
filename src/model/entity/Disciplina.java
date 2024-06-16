package model.entity;

import java.sql.Time;
import java.util.ArrayList;

import controller.InscricaoController;

public class Disciplina {
	
	private String nomeDisciplina;
	private int numeroAulasDisciplina;
	private boolean requisitoDisciplina;
	private String diaSemanaDisciplina;
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

	public String getDiaSemanaDisciplina() {
		return diaSemanaDisciplina;
	}

	public void setDiaSemanaDisciplina(String diaSemanaDisciplina) {
		this.diaSemanaDisciplina = diaSemanaDisciplina;
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

	public boolean verificarSomaCreditos(ArrayList<Disciplina> arrayDisciplinas) {
		
		boolean excedeu = false;
		int somaCreditos = 0;
		
		for(int i = 0; i < arrayDisciplinas.size(); i++) {
			somaCreditos += arrayDisciplinas.get(i).getNumeroAulasDisciplina();
		}
		
		if(somaCreditos > 20) {
			excedeu = true;
		}
		
		return excedeu;
	}
	
	public boolean verificarMaximoAlunosDisciplina(Disciplina disciplina) {
		
		int quantidadeAlunosDisciplina = 0;
		boolean haVagas = true;
		InscricaoController controle = new InscricaoController();		
		
		quantidadeAlunosDisciplina = controle.verificarQuantidadeAlunosDisciplina(disciplina.getNomeDisciplina());
			
		if(quantidadeAlunosDisciplina >= disciplina.getQuantidadeMaximaAlunosDisciplina()) {
			haVagas = false;
		}

		return haVagas;
	}
	

}
