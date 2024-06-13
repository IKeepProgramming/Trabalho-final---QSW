package view;

import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import controller.InscricaoController;
import model.entity.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Time;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class DisciplinasInscricao extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    public int prontuarioAluno;
    public String nomeAluno;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InscricaoController controleInscricao = new InscricaoController();
                    DisciplinasInscricao frame = new DisciplinasInscricao(controleInscricao.selecionarDisciplinas());
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
    public DisciplinasInscricao(ArrayList<Disciplina> disciplinas) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 850, 465);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 75, 813, 283);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));

        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 6 || columnIndex == 1) { 
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }
        };

        tableModel.addColumn("Disciplina");
        tableModel.addColumn("Requisito?");
        tableModel.addColumn("Horario Inicio");
        tableModel.addColumn("Horario Fim");
        tableModel.addColumn("Quantidade Maxima");
        tableModel.addColumn("Numero de Aulas");
        tableModel.addColumn("Selecionar");

        for (int i = 0; i < disciplinas.size(); i++) {
            Object[] rowData = {
                disciplinas.get(i).getNomeDisciplina(),
                disciplinas.get(i).isRequisitoDisciplina(),
                disciplinas.get(i).getHorarioInicioDisciplina(),
                disciplinas.get(i).getHorarioFimDisciplina(),
                disciplinas.get(i).getQuantidadeMaximaAlunosDisciplina(),
                disciplinas.get(i).getNumeroAulasDisciplina(),
                false
            };
            tableModel.addRow(rowData);
        }
        table.setModel(tableModel);
        
        table.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof Boolean) {
                    JCheckBox checkBox = new JCheckBox();
                    checkBox.setSelected((Boolean) value);
                    checkBox.setHorizontalAlignment(JLabel.CENTER);
                    return checkBox;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });
        table.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(new JCheckBox()));

        scrollPane.setViewportView(table);

        JTextArea txtrDisciplinasCadastradas = new JTextArea();
        txtrDisciplinasCadastradas.setFont(new Font("Arial", Font.PLAIN, 36));
        txtrDisciplinasCadastradas.setEditable(false);
        txtrDisciplinasCadastradas.setText("Selecione as Disciplinas para Inscrição:");
        txtrDisciplinasCadastradas.setBounds(99, 23, 628, 41);
        contentPane.add(txtrDisciplinasCadastradas);
        
        JButton btnConfirmarDisciplinas = new JButton("Fazer Inscrição");
        btnConfirmarDisciplinas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		ArrayList<Disciplina> disciplinasIncricao = new ArrayList<Disciplina>();
        		InscricaoController controle = new InscricaoController();
        		
        		int posicaoAluno = 0;
        		int numeroCreditos = 0;
        		int quantidadeAlunosDisciplina = 0;
        		int idAluno = controle.verificarIdAluno(prontuarioAluno); 
        		boolean alunoListaEspera = false;        		        		
        		ArrayList<Disciplina> disciplinasParaIncricao = new ArrayList<>();
        		boolean excedeuCreditos = false;
        		StringBuilder mensagemErro = new StringBuilder();

        		try {
        		    for (int i = 0; i < table.getRowCount(); i++) {
        		        if (Boolean.TRUE.equals(table.getValueAt(i, 6))) {
        		            String nomeDisciplina = (String) tableModel.getValueAt(i, 0);
        		            Disciplina disciplina = controle.verificarInformacoesDisciplina(nomeDisciplina);
        		            quantidadeAlunosDisciplina = controle.verificarQuantidadeAlunosDisciplina(nomeDisciplina);
        		            numeroCreditos += disciplina.getNumeroAulasDisciplina();

        		            disciplinasParaIncricao.add(disciplina);

        		            if (numeroCreditos > 20) {
        		                excedeuCreditos = true;
        		                mensagemErro.append("O Limite de 20 créditos foi excedido")
        		                            .append("\n");
        		                break; 
        		            }
        		        }
        		    }

        		    boolean conflitoDetectado = verificarConflitos(disciplinasParaIncricao);
        		    
        		    if (excedeuCreditos || conflitoDetectado) {
        		    	if(!mensagemErro.isEmpty()) {
        		    		JOptionPane.showMessageDialog(null, mensagemErro.toString(), "Erro de Inscrição", JOptionPane.ERROR_MESSAGE);  
        		    	}
        		    } else {
        		        for (Disciplina disciplina : disciplinasParaIncricao) {
        		            int idDisciplina = controle.verificarIdDisciplina(disciplina.getNomeDisciplina());

        		            if (quantidadeAlunosDisciplina >= disciplina.getQuantidadeMaximaAlunosDisciplina()) {
        		                int decisao = JOptionPane.showConfirmDialog(
        		                    btnConfirmarDisciplinas,
        		                    "A disciplina: " + disciplina.getNomeDisciplina() + " está com a turma cheia, você gostaria de se inscrever na Lista de Espera?",
        		                    "Turma Cheia",
        		                    JOptionPane.YES_NO_OPTION
        		                );

        		                if (decisao == JOptionPane.YES_OPTION) {
        		                    try {
        		                        alunoListaEspera = controle.verificarAlunoListaEspera(prontuarioAluno, disciplina.getNomeDisciplina());
        		                        if (!alunoListaEspera) {
        		                        	
        		                            controle.adicionarAlunoListaEspera(idDisciplina, disciplina.getNomeDisciplina(), idAluno);
        		                            posicaoAluno = controle.verificarPosicaoAlunoListaEspera(prontuarioAluno, disciplina.getNomeDisciplina());
        		                            
        		                            JOptionPane.showMessageDialog(null, "Você foi cadastrado na Lista de Espera da disciplina: " + disciplina.getNomeDisciplina() + "\nVocê está na posição número " + posicaoAluno);
        		                        } else {
        		                            JOptionPane.showMessageDialog(null, "Você já está cadastrado na Lista de Espera da disciplina: " + disciplina.getNomeDisciplina());
        		                        }
        		                    } catch (Exception erro) {
        		                        erro.printStackTrace();
        		                    }
        		                } else {
        		                    JOptionPane.showMessageDialog(null, "Você optou por não se inscrever na Lista de Espera da disciplina: " + disciplina.getNomeDisciplina());
        		                }
        		            } else {
        		                disciplinasIncricao.add(disciplina);
        		            }
        		        }
        		        
        		    }
        		} catch (Exception erro) {
        		    erro.printStackTrace();
        		}

        		
        		int decisao = 0;
        		StringBuilder teste = new StringBuilder();
        		teste.append("<html><body><table>");
        		
        		if(!disciplinasIncricao.isEmpty()) {
        		for (int i = 0; i < disciplinasIncricao.size(); i++) {
        		    String nomeDisciplina = disciplinasIncricao.get(i).getNomeDisciplina();
        		    turmaDisciplina turma = controle.verificarHorariosDisciplinasEscolhidas(nomeDisciplina);
        		    
        		    teste.append("<tr>");
        		    teste.append("<td><b>Disciplina:</b>  ").append(nomeDisciplina).append("</td>");
        		    if (turma != null) {
        		        teste.append("<td><b>Professor(a):</b> ").append(turma.getProfessorDisciplina()).append("</td>");
        		        teste.append("<td><b>Horário Inicio:</b> ").append(disciplinasIncricao.get(i).getHorarioInicioDisciplina()).append("</td>");
        		        teste.append("<td><b>Horário Fim:</b> ").append(disciplinasIncricao.get(i).getHorarioFimDisciplina()).append("</td>");
        		        teste.append("<td><b>Sala:</b> ").append(turma.getSalaTurma()).append("</td>");
        		    } else {
        		        teste.append("<td colspan='3'><b>Horário não encontrado</b></td>");
        		    }
        		    teste.append("</tr>");
        		}

        		teste.append("</table></body></html>");
        		teste.append("\n\n Gostaria de confirmar a sua inscrição?");

        		decisao = JOptionPane.showConfirmDialog(btnConfirmarDisciplinas, teste.toString());
        		boolean cadastradoDisciplina = false;
        		int idDisciplina = 0;
        		
        		switch(decisao) {
        		case JOptionPane.YES_OPTION:
        			try {
        			    StringBuilder mensagem1 = new StringBuilder();
        			    mensagem1.append("Você já está inscrito na(s) disciplina(s):\n");

        			    boolean encontrouCadastradas = false;

        			    for (int i = 0; i < disciplinasIncricao.size(); i++) {
        			        Disciplina disciplina = disciplinasIncricao.get(i);
        			        idDisciplina = controle.verificarIdDisciplina(disciplina.getNomeDisciplina());
        			        cadastradoDisciplina = controle.verificarAlunoDisciplina(prontuarioAluno, disciplina.getNomeDisciplina());

        			        if (!cadastradoDisciplina) {
        			            controle.adicionarAlunoDisciplina(disciplina.getNomeDisciplina(), idDisciplina, idAluno);
        			        } else {
        			            mensagem1.append("- ").append(disciplina.getNomeDisciplina()).append("\n");
        			            encontrouCadastradas = true;
        			        }
        			    }

        			    if (encontrouCadastradas) {
        			        JOptionPane.showMessageDialog(null, mensagem1.toString(), "Disciplinas Já Inscritas", JOptionPane.INFORMATION_MESSAGE);
        			    } else {
        			        JOptionPane.showMessageDialog(null, "Inscrição realizada com sucesso!", "Inscrição Completa", JOptionPane.INFORMATION_MESSAGE);
        			    }
        			} catch (Exception erro) {
        			    erro.printStackTrace();
        			}
				break;
				
				case JOptionPane.NO_OPTION:
					JOptionPane.showMessageDialog(null, "Aproveite para revisar suas disciplinas.");
				break;       
        		}
        	  }
        	}
        });
        btnConfirmarDisciplinas.setBounds(333, 371, 144, 44);
        contentPane.add(btnConfirmarDisciplinas);
    }
    
    private boolean hasTimeConflict(Time inicio1, Time fim1, Time inicio2, Time fim2) {
        return !(fim1.before(inicio2) || inicio1.after(fim2));
    }
    
    private boolean verificarConflitos(ArrayList<Disciplina> disciplinasSelecionadas) {
        boolean temConflito = false;
        StringBuilder frase = new StringBuilder();

        for (int i = 0; i < disciplinasSelecionadas.size(); i++) {
            Disciplina disciplina1 = disciplinasSelecionadas.get(i);
            Time inicio1 = disciplina1.getHorarioInicioDisciplina();
            Time fim1 = disciplina1.getHorarioFimDisciplina();

            for (int j = i + 1; j < disciplinasSelecionadas.size(); j++) {
                Disciplina disciplina2 = disciplinasSelecionadas.get(j);
                Time inicio2 = disciplina2.getHorarioInicioDisciplina();
                Time fim2 = disciplina2.getHorarioFimDisciplina();
                
                if (!disciplina1.getNomeDisciplina().equals(disciplina2.getNomeDisciplina()) && hasTimeConflict(inicio1, fim1, inicio2, fim2)) {
                    frase.append("Conflito de horário entre ").append(disciplina1.getNomeDisciplina())
                         .append(" e ").append(disciplina2.getNomeDisciplina()).append("\n")
                         .append("Horários: \n").append(disciplina1.getNomeDisciplina()).append(": ")
                         .append(disciplina1.getHorarioInicioDisciplina()).append(" - ")
                         .append(disciplina1.getHorarioFimDisciplina()).append("\n")
                         .append(disciplina2.getNomeDisciplina()).append(": ")
                         .append(disciplina2.getHorarioInicioDisciplina()).append(" - ")
                         .append(disciplina2.getHorarioFimDisciplina()).append("\n\n");
                    temConflito = true;
                }
            }
        }
        
        if (temConflito) {
            JOptionPane.showMessageDialog(null, frase.toString(), "Conflitos de Horário", JOptionPane.WARNING_MESSAGE);
        }

        return temConflito;
    }
}
