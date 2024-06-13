package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.*;

public class DisciplinaDao {	
	
	public ArrayList<Disciplina> selecionarDisciplinas(){
		
		Disciplina disciplina;		
		ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>(); 
		PreparedStatement stmt;
    	ResultSet rs;
    	
    	Conexao conexao = new Conexao();
    	
    	String selecionarDisciplinas = "SELECT * FROM DISCIPLINA";
    	
    	try {
    		stmt = conexao.getConn().prepareStatement(selecionarDisciplinas);
    		rs = stmt.executeQuery();
    		
    		while(rs.next()) {
    			disciplina = new Disciplina();
    			disciplina.setNomeDisciplina(rs.getString("DISCIPLINA.nomeDisciplina"));
    			disciplina.setRequisitoDisciplina(rs.getBoolean("DISCIPLINA.requisitoDisciplina"));
    			disciplina.setHorarioInicioDisciplina(rs.getTime("DISCIPLINA.horarioInicioDisciplina"));
    			disciplina.setHorarioFimDisciplina(rs.getTime("DISCIPLINA.horarioFimDisciplina"));
    			disciplina.setQuantidadeMaximaAlunosDisciplina(rs.getInt("DISCIPLINA.quantidadeMaximoAlunosDisciplina"));
    			disciplina.setNumeroAulasDisciplina(rs.getInt("DISCIPLINA.numeroAulasDisciplina"));
    			
    			disciplinas.add(disciplina);
    		}
    		rs.close();
    		stmt.close();
    	}catch (SQLException e) {
			e.printStackTrace();
		}	
		return(disciplinas.isEmpty() ? null : disciplinas);
	}
	
	public int verificarQuantidadeAlunosDisciplina(String nomeDisciplina){		

		int quantidadeAlunos = 0;
		
		PreparedStatement stmt;
		ResultSet rs;
		
		Conexao conexao = new Conexao();
		
		String select = "SELECT COUNT(idAluno) FROM AlunoDisciplina ad WHERE ad.nomeDisciplina = ?";
		
		try {
			stmt = conexao.getConn().prepareStatement(select);
			stmt.setString(1, nomeDisciplina);
    		rs = stmt.executeQuery();
    		
    		if (rs.next()) {
                // Obtém o valor do resultado e atribui à variável
                quantidadeAlunos = rs.getInt(1);
            }    
    		
    		rs.close();
    		stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		return quantidadeAlunos;
	}
	
	public Disciplina verificarInformacoesDisciplina(String nomeDisciplina) {
		
		Disciplina disciplina = new Disciplina();
		
		PreparedStatement stmt;
		ResultSet rs;
		Conexao conexao = new Conexao();
		
		String select = "SELECT * FROM DISCIPLINA DI WHERE DI.nomeDisciplina = ?";
		
		try {
			stmt = conexao.getConn().prepareStatement(select);
			stmt.setString(1, nomeDisciplina);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				disciplina = new Disciplina();   			
    			disciplina.setNomeDisciplina(rs.getString("DI.nomeDisciplina"));
    			disciplina.setRequisitoDisciplina(rs.getBoolean("DI.requisitoDisciplina"));
    			disciplina.setHorarioInicioDisciplina(rs.getTime("DI.horarioInicioDisciplina"));
    			disciplina.setHorarioFimDisciplina(rs.getTime("DI.horarioFimDisciplina"));
    			disciplina.setQuantidadeMaximaAlunosDisciplina(rs.getInt("DI.quantidadeMaximoAlunosDisciplina"));
    			disciplina.setNumeroAulasDisciplina(rs.getInt("DI.numeroAulasDisciplina"));
			}					
			rs.close();
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return disciplina;
	}
	
	public int verificarIdDisciplina(String nomeDisciplina) {
		
		int codigoDisciplina = 0;
		
		PreparedStatement stmt;
		ResultSet rs;		
		Conexao conexao = new Conexao();		
		String select = "SELECT idDisciplina from DISCIPLINA WHERE nomeDisciplina = ?";
		
		try {
			stmt = conexao.getConn().prepareStatement(select);
			stmt.setString(1, nomeDisciplina);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				codigoDisciplina = rs.getInt(1);
			}
			
			stmt.close();
			rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return codigoDisciplina;
	}	
	
	public ArrayList<Disciplina> selecionarDisciplinasAluno(int idAluno){

		Disciplina disciplina;		
		ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>(); 
		PreparedStatement stmt;
    	ResultSet rs;
    	
    	Conexao conexao = new Conexao();
    	
    	String selecionarDisciplinas = "SELECT D.* FROM DISCIPLINA D " + 
    								   "INNER JOIN AlunoDisciplina AD " + 
    								   "ON D.idDisciplina = AD.idDisciplina " + 
    								   "INNER JOIN Aluno A " + 
    								   "ON AD.idAluno = A.idAluno " +
    								   "WHERE A.idAluno = ?";
    	
    	try {
    		stmt = conexao.getConn().prepareStatement(selecionarDisciplinas);
    		stmt.setInt(1, idAluno);
    		rs = stmt.executeQuery();
    		
    		while(rs.next()) {
    			disciplina = new Disciplina();   			
    			disciplina.setNomeDisciplina(rs.getString("D.nomeDisciplina"));
    			disciplina.setRequisitoDisciplina(rs.getBoolean("D.requisitoDisciplina"));
    			disciplina.setHorarioInicioDisciplina(rs.getTime("D.horarioInicioDisciplina"));
    			disciplina.setHorarioFimDisciplina(rs.getTime("D.horarioFimDisciplina"));
    			disciplina.setQuantidadeMaximaAlunosDisciplina(rs.getInt("DISCIPLINA.quantidadeMaximoAlunosDisciplina"));
    			disciplina.setNumeroAulasDisciplina(rs.getInt("D.numeroAulasDisciplina"));
    			
    			disciplinas.add(disciplina);
    		}
    		rs.close();
    		stmt.close();
    	}catch (SQLException e) {
			e.printStackTrace();
		}	
		return(disciplinas.isEmpty() ? null : disciplinas);
	}
	
	public turmaDisciplina verificarHorariosDisciplinasEscolhidas(String nomeDisciplina) {
		
		turmaDisciplina turma = new turmaDisciplina();
		PreparedStatement stmt;
		ResultSet rs;
		
		Conexao conexao = new Conexao();
		
		String select = "select d.nomeDisciplina, t.professor, t.sala " + 
						"from turmaDisciplina td " + 
						"inner join Disciplina d on d.idDisciplina = td.idDisciplina " + 
						"inner join turma t on t.idTurma = td.idTurma " + 
						"WHERE d.nomeDisciplina = ?";
		try {
			stmt = conexao.getConn().prepareStatement(select);
			stmt.setString(1, nomeDisciplina);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				turma = new turmaDisciplina();
                turma.setNomeDisciplina(rs.getString("d.nomeDisciplina"));
                turma.setProfessorDisciplina(rs.getString("t.professor"));
                turma.setSalaTurma(rs.getString("t.sala"));
            }
			
			stmt.close();
			rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return turma;
	}
	
	public boolean verificarAlunoDisciplina(int prontuarioAluno, String nomeDisciplina) {
		
		boolean cadastrado = false;
		Conexao conexao = new Conexao();
		
		PreparedStatement stmt;
		ResultSet rs;
		
		String select = "select ad.* from alunoDisciplina ad "
						+ "inner join Aluno a on ad.idAluno = a.idAluno "
						+ "inner join disciplina d on ad.idDisciplina = d.idDisciplina "
						+ "where a.prontuarioAluno = ? and d.nomeDisciplina = ?";
		
		try {
			stmt = conexao.getConn().prepareStatement(select);
			stmt.setInt(1, prontuarioAluno);
			stmt.setString(2, nomeDisciplina);
			rs = stmt.executeQuery();

			if(rs.next()) {
				cadastrado = true;			
			}
			
			stmt.close();
			rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cadastrado;
	}
}
