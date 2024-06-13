package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.entity.*;

public class AlunoDao {
	
	public int verificarIdAluno(int prontuarioAluno) {
		
		int codigoAluno = 0;
		
		PreparedStatement stmt;
		ResultSet rs;		
		Conexao conexao = new Conexao();		
		String select = "SELECT idAluno from ALUNO WHERE prontuarioAluno = ?";
		
		try {
			stmt = conexao.getConn().prepareStatement(select);
			stmt.setInt(1, prontuarioAluno);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				codigoAluno = rs.getInt(1);
			}
			
			stmt.close();
			rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return codigoAluno;
	}
	
	public void adicionarAlunoDisciplina(String nomeDisciplina, int idDisciplina, int idAluno) {
		
		Conexao conexao = new Conexao();
		
		String insert = "INSERT INTO AlunoDisciplina (nomeDisciplina, idDisciplina, idAluno)" +
						"VALUES (?,?,?)";
		
		try {
			PreparedStatement stmt = conexao.getConn().prepareStatement(insert);
			stmt.setString(1, nomeDisciplina);
			stmt.setInt(2, idDisciplina);
			stmt.setInt(3, idAluno);
			
			stmt.execute();
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Aluno verificarInformacoesAluno(int prontuarioAluno) {
		
		Aluno aluno = new Aluno();
		
		PreparedStatement stmt;
		ResultSet rs;
		Conexao conexao = new Conexao();
		
		String select = "SELECT * FROM ALUNO AL WHERE AL.prontuarioAluno = ?";
		
		try {
			stmt = conexao.getConn().prepareStatement(select);
			stmt.setInt(1, prontuarioAluno);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				aluno = new Aluno();   			
				aluno.setNomeAluno(rs.getString("AL.nomeAluno"));
				aluno.setProntuarioAluno(rs.getInt("AL.prontuarioAluno"));
				aluno.setIraAluno(rs.getInt("AL.iraAluno"));
				aluno.setCpfAluno(rs.getString("AL.cpfAluno"));
				aluno.setEmailAluno(rs.getString("AL.emailAluno"));				
			}									
			rs.close();
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return aluno;
	}
}
