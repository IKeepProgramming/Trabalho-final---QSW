package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.entity.*;
import controller.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int prontuarioAluno = 0;
	private JTextField txtAluno;
	private JTextField txtProntuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 554);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(501, 502, 2, 2);
		contentPane.add(scrollPane);
		
		JButton btnNewButton = new JButton("Realizar Inscrição");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtAluno.getText().trim().isEmpty() || !txtProntuario.getText().trim().isEmpty()) {
					try {
						InscricaoController controleInscricao = new InscricaoController();
						DisciplinasInscricao frameDisciplinasInscricao = new DisciplinasInscricao(controleInscricao.selecionarDisciplinas());
						frameDisciplinasInscricao.prontuarioAluno = Integer.parseInt(txtProntuario.getText());
						frameDisciplinasInscricao.nomeAluno = txtAluno.getText();
						frameDisciplinasInscricao.setVisible(true);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
			  }else {
				  JOptionPane.showMessageDialog(null, "É preciso estar logado para realizar uma inscrição!");
			  }
			}
		});
		btnNewButton.setBounds(310, 219, 142, 53);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Fazer Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InscricaoController controle = new InscricaoController();
				
				try {
					prontuarioAluno = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o seu Prontuario por favor: Sem as primeiras Siglas de Campus"));
					
					Aluno infoAluno = controle.verificarInformacoesAluno(prontuarioAluno);
					
					if (infoAluno != null) {
                        txtAluno.setText(infoAluno.getNomeAluno());
                        txtProntuario.setText(String.valueOf(infoAluno.getProntuarioAluno()));
                    } else {
                        JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
                    }
				}catch (NumberFormatException erro) {		
					JOptionPane.showMessageDialog(null, "Inserido Caracteres Inválidos!!!");
				}
				
			}
		});
		btnNewButton_1.setBounds(76, 219, 142, 53);
		contentPane.add(btnNewButton_1);
		
		JTextArea txtrSistemaAcademico = new JTextArea();
		txtrSistemaAcademico.setBackground(new Color(255, 255, 255));
		txtrSistemaAcademico.setFont(new Font("Arial", Font.PLAIN, 42));
		txtrSistemaAcademico.setText("Sistema Academico");
		txtrSistemaAcademico.setEditable(false);
		txtrSistemaAcademico.setBounds(75, 52, 377, 53);
		contentPane.add(txtrSistemaAcademico);
		
		txtAluno = new JTextField();
		txtAluno.setEditable(false);
		txtAluno.setBounds(65, 484, 205, 20);
		contentPane.add(txtAluno);
		txtAluno.setColumns(10);
		
		txtProntuario = new JTextField();
		txtProntuario.setEditable(false);
		txtProntuario.setBounds(377, 484, 86, 20);
		contentPane.add(txtProntuario);
		txtProntuario.setColumns(10);
		
		JTextArea txtrAluno = new JTextArea();
		txtrAluno.setEditable(false);
		txtrAluno.setText("Aluno:");
		txtrAluno.setBounds(10, 482, 53, 22);
		contentPane.add(txtrAluno);
		
		JTextArea txtrProntuario = new JTextArea();
		txtrProntuario.setEditable(false);
		txtrProntuario.setText("Prontuario:");
		txtrProntuario.setBounds(280, 482, 87, 22);
		contentPane.add(txtrProntuario);
	}

	public JTextField getTxtAluno() {
		return txtAluno;
	}

	public void setTxtAluno(JTextField txtAluno) {
		this.txtAluno = txtAluno;
	}

	public JTextField getTxtProntuario() {
		return txtProntuario;
	}

	public void setTxtProntuario(JTextField txtProntuario) {
		this.txtProntuario = txtProntuario;
	}
	
	
}
