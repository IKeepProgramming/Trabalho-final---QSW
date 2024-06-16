package testesUnitarios;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import controller.InscricaoController;
import model.dao.*;
import model.entity.*;
import view.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DisciplinasTestRN01 {
	
	InscricaoController controler = new InscricaoController();
	
	boolean resultadoTeste = false;
	
	//Teste para verificar se o aluno não utilizou mais de 20 créditos
	@Test
	public void creditoSuperior_RetornaTrue_QuandoCreditoMaiorqueVinte() {
		
		//Declaração das disciplinas e ArrayList para teste.
		Disciplina disciplinaTest = new Disciplina();
		Disciplina disciplinaTest2 = new Disciplina();
		Disciplina disciplinaTest3 = new Disciplina();
		Disciplina disciplinaTest4 = new Disciplina();
		Disciplina disciplinaTest5 = new Disciplina();
		ArrayList<Disciplina> arrayDisciplinasTeste = new ArrayList<>();
		
		//Número de aula atribuido manualmente para as 4 disciplinas para teste
		disciplinaTest.setNumeroAulasDisciplina(4);
		disciplinaTest2.setNumeroAulasDisciplina(4);
		disciplinaTest3.setNumeroAulasDisciplina(4);
		disciplinaTest4.setNumeroAulasDisciplina(4);
		disciplinaTest5.setNumeroAulasDisciplina(5);
		
		//Disciplinas adicionadas no ArrayList arrayDisciplinasTeste
		arrayDisciplinasTeste.add(disciplinaTest);
		arrayDisciplinasTeste.add(disciplinaTest2);
		arrayDisciplinasTeste.add(disciplinaTest3);
		arrayDisciplinasTeste.add(disciplinaTest4);
		
		//Chamada do método verificarSomaCreditos() no controler
		resultadoTeste = controler.verificarSomaCreditos(arrayDisciplinasTeste);
		
		//Caso o resultado seja True o crédito utilizado foi maior que 20
		Assertions.assertTrue(resultadoTeste, "O crédito das disciplinas escolhidas foi maior que 20");
	}
	
	@Test
	public void creditoInferior_RetornaFalse_QuandoCreditoMenorqueVinte() {
			
			//Declaração das disciplinas e ArrayList para teste.
			Disciplina disciplinaTest = new Disciplina();
			Disciplina disciplinaTest2 = new Disciplina();
			Disciplina disciplinaTest3 = new Disciplina();
			Disciplina disciplinaTest4 = new Disciplina();
			Disciplina disciplinaTest5 = new Disciplina();
			ArrayList<Disciplina> arrayDisciplinasTeste = new ArrayList<>();
			
			//Número de aula atribuido manualmente para as 4 disciplinas para teste
			disciplinaTest.setNumeroAulasDisciplina(2);
			disciplinaTest2.setNumeroAulasDisciplina(2);
			disciplinaTest3.setNumeroAulasDisciplina(4);
			disciplinaTest4.setNumeroAulasDisciplina(4);
			disciplinaTest5.setNumeroAulasDisciplina(2);
			
			//Disciplinas adicionadas no ArrayList arrayDisciplinasTeste
			arrayDisciplinasTeste.add(disciplinaTest);
			arrayDisciplinasTeste.add(disciplinaTest2);
			arrayDisciplinasTeste.add(disciplinaTest3);
			arrayDisciplinasTeste.add(disciplinaTest4);
			
			//Chamada do método verificarSomaCreditos no controler
			resultadoTeste = controler.verificarSomaCreditos(arrayDisciplinasTeste);
			
			//Caso o resultado seja falso o crédito utilizado foi menor que 20
			Assertions.assertFalse(resultadoTeste, "O crédito das disciplinas selecionadas foi inferior a 20");
		}
		
}
