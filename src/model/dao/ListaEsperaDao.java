package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Result;

import model.entity.*;

public class ListaEsperaDao {
	
	public void adicionarAlunoListaEspera(int idDisciplina, String nomeDisciplina ,int idAluno) {
		
		Conexao conexao = new Conexao();
		
		String insert = "INSERT INTO listaEspera (idDisciplina, nomeDisciplina, idAluno)" +
						"VALUES (?,?,?)";
		
		try {
			PreparedStatement stmt = conexao.getConn().prepareStatement(insert);
			stmt.setInt(1, idDisciplina);
			stmt.setString(2, nomeDisciplina);
			stmt.setInt(3, idAluno);
			
			stmt.execute();
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ListaEspera> buscarListaEsperaAluno(int prontuarioAluno){
		
		ListaEspera listaEspera;
		
	    Conexao conexao = new Conexao();
		
		ArrayList<ListaEspera> listaEsperaAluno = new ArrayList<ListaEspera>();
		PreparedStatement stmt;
		ResultSet rs;
		
		String select = "select l.* from listaEspera l"
				+ "inner join aluno "
				+ "on al.idAluno = l.idAluno"
				+ "where al.prontuarioAluno = ?";
		
		try {
    		stmt = conexao.getConn().prepareStatement(select);
    		stmt.setInt(1, prontuarioAluno);
    		rs = stmt.executeQuery();
    		
    		while(rs.next()) {
    			listaEspera = new ListaEspera();   			
    			listaEspera.setIdDisciplina(rs.getInt("l.idDisciplina"));
    			listaEspera.setNomeDisciplina(rs.getString("l.nomeDisciplina"));
    			listaEspera.setIdAluno(rs.getInt("l.idAluno"));
    			
    			listaEsperaAluno.add(listaEspera);
    		}
    		rs.close();
    		stmt.close();
    	}catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return (listaEsperaAluno.isEmpty() ? null : listaEsperaAluno);
	}
	
	public boolean verificarAlunoListaEspera(int prontuarioAluno, String nomeDisciplina) {

		boolean cadastrado = false;
		Conexao conexao = new Conexao();
		
		PreparedStatement stmt;
		ResultSet rs;
		
		String select = "SELECT l.* FROM listaEspera l " +
                		"INNER JOIN aluno al ON al.idAluno = l.idAluno " +
                		"INNER JOIN disciplina d ON d.idDisciplina = l.idDisciplina " +
                		"WHERE al.prontuarioAluno = ? AND d.nomeDisciplina = ?";
		
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
	
	public int verificarPosicaoAlunoListaEspera(int prontuarioAluno, String nomeDisciplina) {
		
		int posicao = 0;
		
		Conexao conexao = new Conexao();
		
		PreparedStatement stmt;
		ResultSet rs;
		
		String select = "select l.idListaEspera from listaEspera l "
					  + "inner join aluno al "
					  + "on al.idAluno = l.idAluno "
					  + "inner join disciplina d "
					  + "on d.idDisciplina = l.idDisciplina "
					  + "where al.prontuarioAluno = ? "
					  + "and d.nomeDisciplina = ?";
		
		try {
			stmt = conexao.getConn().prepareStatement(select);
			stmt.setInt(1, prontuarioAluno);
			stmt.setString(2, nomeDisciplina);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				posicao = rs.getInt(1);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return posicao;
	}
	
	
}
