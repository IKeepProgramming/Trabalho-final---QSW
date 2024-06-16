package controller;

import java.util.ArrayList;

import model.dao.*;
import model.entity.*;

public class InscricaoController {
	public ArrayList<Disciplina> selecionarDisciplinas() {
		DisciplinaDao disciplinaDao = new DisciplinaDao();		
		return(disciplinaDao.selecionarDisciplinas());	
	}
	
	public int verificarQuantidadeAlunosDisciplina(String nomeDisciplina) {
		DisciplinaDao disciplinaDao = new DisciplinaDao();		
		return disciplinaDao.verificarQuantidadeAlunosDisciplina(nomeDisciplina);	
	}
	
	public Disciplina verificarInformacoesDisciplina(String nomeDisciplina) {
		DisciplinaDao disciplinaDao = new DisciplinaDao();		
		return disciplinaDao.verificarInformacoesDisciplina(nomeDisciplina);
	}
	
	public int verificarIdAluno(int prontuarioAluno) {
		AlunoDao alunoDao = new AlunoDao();		
		return alunoDao.verificarIdAluno(prontuarioAluno);
	}
	
	public int verificarIdDisciplina(String nomeDisciplina) {
		DisciplinaDao disciplinaDao = new DisciplinaDao();
		
		return disciplinaDao.verificarIdDisciplina(nomeDisciplina);
	}
	
	public void adicionarAlunoListaEspera(int idDisciplina, String nomeDisciplina ,int idAluno) {
		ListaEsperaDao listaEsperaDao = new ListaEsperaDao();
		
		listaEsperaDao.adicionarAlunoListaEspera(idDisciplina, nomeDisciplina, idAluno);
	}
	
	public void adicionarAlunoDisciplina(String nomeDisciplina, int idDisciplina, int idAluno) {
		AlunoDao alunoDao = new AlunoDao();
		
		alunoDao.adicionarAlunoDisciplina(nomeDisciplina, idDisciplina, idAluno);
	}
	
	public ArrayList<Disciplina> selecionarDisciplinasAluno(int idAluno){
		DisciplinaDao disciplinaDao = new DisciplinaDao();
		
		return disciplinaDao.selecionarDisciplinasAluno(idAluno);
	}
	
	public Aluno verificarInformacoesAluno(int prontuarioAluno) {
		AlunoDao alunoDao = new AlunoDao();
		return alunoDao.verificarInformacoesAluno(prontuarioAluno);
	}
	
	public turmaDisciplina verificarHorariosDisciplinasEscolhidas(String nomeDisciplina) {
		DisciplinaDao disciplinaDao = new DisciplinaDao();
		
		return disciplinaDao.verificarHorariosDisciplinasEscolhidas(nomeDisciplina);
	}
	
	public ArrayList<ListaEspera> buscarListaEsperaAluno(int prontuarioAluno){
		ListaEsperaDao listaEsperaDao = new ListaEsperaDao();
		
		return listaEsperaDao.buscarListaEsperaAluno(prontuarioAluno);
	}
	
	public boolean verificarAlunoListaEspera(int prontuarioAluno, String nomeDisciplina) {
		ListaEsperaDao listaEsperaDao = new ListaEsperaDao();
		
		return listaEsperaDao.verificarAlunoListaEspera(prontuarioAluno, nomeDisciplina);
	}
	
	public boolean verificarAlunoDisciplina(int prontuarioAluno, String nomeDisciplina) {
		DisciplinaDao disciplinaDao = new DisciplinaDao();
		
		return disciplinaDao.verificarAlunoDisciplina(prontuarioAluno, nomeDisciplina);
	}
	
	public int verificarPosicaoAlunoListaEspera(int prontuarioAluno, String nomeDisciplina) {
		ListaEsperaDao listaEspera = new ListaEsperaDao();
		
		return listaEspera.verificarPosicaoAlunoListaEspera(prontuarioAluno, nomeDisciplina);
	}
	
	public boolean verificarSomaCreditos(ArrayList<Disciplina> arrayDisciplinas) {
		Disciplina disciplina = new Disciplina();
		
		return disciplina.verificarSomaCreditos(arrayDisciplinas);
	}
	
	public boolean verificarMaximoAlunosDisciplina(Disciplina disciplina) {
		Disciplina disciplina2 = new Disciplina();
		
		return disciplina2.verificarMaximoAlunosDisciplina(disciplina);
	}
	
	public String verificarMateriaRequisitoDisciplina(String nomeDisciplina) {
		DisciplinaDao disciplinadao = new DisciplinaDao();
		
		return disciplinadao.verificarMateriaRequisitoDisciplina(nomeDisciplina);
	}
}
