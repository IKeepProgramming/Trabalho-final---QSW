package testesUnitarios;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import controller.InscricaoController;
import model.dao.*;
import model.entity.*;
import view.*;

class DisciplinasTestRN02 {
	
	boolean resultadoTeste = false;
	InscricaoController controle = new InscricaoController();
	
	@Test
	public void maximovagas_RetornaTrue_QuandoQuantidadeAlunoMaiorQueVagasMaximas() {
		
		//Declaração de disciplina para Teste
		Disciplina disciplina = new Disciplina();
		
		//Atribuições manuais no Set dos atributos da disciplina para teste
		disciplina.setNomeDisciplina("Algoritmos e Estruturas de Dados");
		disciplina.setRequisitoDisciplina(false);
		disciplina.setDiaSemanaDisciplina("Quarta-Feira");
		disciplina.setHorarioInicioDisciplina(null);
		disciplina.setHorarioFimDisciplina(null);
		disciplina.setNumeroAulasDisciplina(4);
		disciplina.setQuantidadeMaximaAlunosDisciplina(40);
		
		//Chamada do método verificarMaximoAlunoDisciplina() no controller para teste
		resultadoTeste = controle.verificarMaximoAlunosDisciplina(disciplina);	
		
		//Caso o resultado seja True, a disciplina possui vagas para alunos novos.
		Assertions.assertTrue(resultadoTeste, "A disciplina escolhida possui vagas para inscrição");
	}
	
	@Test
	public void maximovagas_RetornaFalse_QuandoQuantidadeAlunoMenorQueVagasMaximas() {
		
		//Declaração da disciplina para teste
		Disciplina disciplina = new Disciplina();
		
		//Atribuições manuais no Set dos atributos da disciplina para teste
		disciplina.setNomeDisciplina("Introdução à Programação");
		disciplina.setRequisitoDisciplina(false);
		disciplina.setDiaSemanaDisciplina("Sexta-Feira");
		disciplina.setHorarioInicioDisciplina(null);
		disciplina.setHorarioFimDisciplina(null);
		disciplina.setNumeroAulasDisciplina(4);
		disciplina.setQuantidadeMaximaAlunosDisciplina(40);
		
		//Chamada do método verificarMaximoAlunoDisciplina() no controller para teste
		resultadoTeste = controle.verificarMaximoAlunosDisciplina(disciplina);	
		
		//Caso o resultado seja False, a disciplina não possui mais vagas para alunos novos
		Assertions.assertFalse(resultadoTeste, "A disciplina escolhida não possui vagas para inscrição");
	}

}
