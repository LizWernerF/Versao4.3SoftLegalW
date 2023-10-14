package gui8;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class GeradorDeDocumentoController {

	private static final Logger logger = Logger.getLogger(application.Main.class.getName());
	private UserDao userDao = new UserDao();
	private ProcDao procDao = new ProcDao();
	private RecDao recDao = new RecDao();
	private HistoricoDao HistoricoDao = new HistoricoDao();
	private AndamentoRecDao andamentoRecDao;

	private void alert(String title, String message, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	// NOMES CONTROLES

	// TABS e TABPANE
	@FXML
	private TabPane tabPaneGeradorDoc;

	public void setTabPane(TabPane tabPaneGeradorDoc) {
		this.tabPaneGeradorDoc = tabPaneGeradorDoc;
	}

	@FXML
	private Tab tabDocAdm;
	@FXML
	private Tab tabPetIniciais;
	@FXML
	private Tab tabPetIntercorrentes;

	// TAB DOCUMENTOS ADMINISTRATIVOS
	@FXML
	private ComboBox<String> comboClientesDocAdm;

	@FXML
	private Button btnContratoIntDocAdm;
	@FXML
	private Button btnContratoIntPisoComDocAdm;
	@FXML
	private Button btnContratoPisoComDocAdm;
	@FXML
	private Button btnContratoIntNEDocAdm;
	@FXML
	private Button btnContratoNEDocAdm;
	@FXML
	private Button btnContratoIntPisoSemDocAdm;
	@FXML
	private Button btnContratoPisoSemDocAdm;
	@FXML
	private Button btnContratoIntNEPisoComDocAdm;
	@FXML
	private Button btnContratoIntNEPisoSemDocAdm;
	@FXML
	private Button btnContratoNEPisoComDocAdm;
	@FXML
	private Button btnContratoNEPisoSemDocAdm;
	@FXML
	private Button btnDeclaracaoDocAdm;
	@FXML
	private Button btnProcInterDocAdm;
	@FXML
	private Button btnProcNEDocAdm;
	@FXML
	private Button btnProcPisoDocAdm;
	@FXML
	private Button btnProcNEPisoDocAdm;
	@FXML
	private Button btnProcInterNEPisoDocAdm;
	@FXML
	private Button btnProcInterNEDocAdm;
	@FXML
	private Button btnProcInterPisoDocAdm;
	@FXML
	private TextField txtNomeDocAdm;
	@FXML
	private TextField txtNacionalidadeDocAdm;
	@FXML
	private TextField txtProfissaoDocAdm;
	@FXML
	private TextField txtEstadoCivilDocAdm;
	@FXML
	private TextField txtRGDocAdm;
	@FXML
	private TextField txtCPFDocAdm;
	@FXML
	private TextField txtIdFDocAdm;
	@FXML
	private TextField txtEnderecoDocAdm;
	@FXML
	private TextField txtCEPDocAdm;

	
	// TAB PETIÇÕES INICIAIS
		@FXML
		private ComboBox<String> comboClientesPetInicial;
		@FXML
		private ListView<String> listClientesPetInicial = new ListView<>();
		
		@FXML
		private TextField txtNomePetInicial;
		@FXML
		private TextField txtNacionalidadePetInicial;
		@FXML
		private TextField txtEstadoCivilPetInicial;
		@FXML
		private TextField txtProfissaoDPetInicial;
		@FXML
		private TextField txtRGPetInicial;
		@FXML
		private TextField txtCPFPetInicial;
		@FXML
		private TextField txtIdFPetInicial;
		@FXML
		private TextField txtEnderecoPetInicial;
		@FXML
		private TextField txtCEPPetInicial;
		@FXML
		private TextField txtMatPetInicial;
		@FXML
		private TextField txtRefPetInicial;
		@FXML
		private TextField txtCargoPetInicial;
		@FXML
		private TextField txtCargaHPetInicial;
		@FXML
		private TextField txtNivelPetInicial;
		@FXML
		private TextField txtDataInicioPetInicial;
		@FXML
		private TextField txtDataaposentadoriaPetInicial;
		@FXML
		private TextField txtDataNascimentoPetInicial;
		@FXML
		private TextField txtTrienioPetInicial;
	
	
		
		
		//TAB PETIÇÕES INTERCORRENTES
		@FXML
		private ComboBox comboClientesPetInter;
		@FXML 
		private ListView listClientesPetInter;
		@FXML
		private TextField txtNomePetInter;
		@FXML
		private TextField txtMatPetInter;
		@FXML
		private TextField txtRefPetInter;
		@FXML
		private TextField txtCargoPetInter;
		@FXML
		private TextField txtCargaHPetInter;
		@FXML
		private TextField txtNivelPetInter;
		@FXML
		private TextField txtNumeroProcInter;
		@FXML
		private TextField txtTemaProcesso;
		@FXML
		private TextField txtVaraProcesso;
		@FXML
		private TextField txtComarcaProcesso;
		@FXML
		private Button btnPeticaoRespostaImpugnacaoERJNE;
		@FXML
		private Button btnEDsAvaliacaoAplicada;
		@FXML
		private Button btnPeticaoconcordandocalculoscontadorNE;
		@FXML
		private Button btnPeticaodescabimentoSuspensaoNE;
		@FXML
		private Button btnPeticaoProvasNE;
		@FXML
		private Button btnPeticaoSobreParametrosNE;
		
		
	
	// INITIALIZE
	public void initializeComboBox() {
		UserDao userDao = new UserDao();
	}

	@FXML
	public void initialize() {

		// COMBOBOX - TAB Documentos administrativos
		try {
			List<String> nomesClientes = procDao.obterNomesClientes();
			Collections.sort(nomesClientes);
			// Adicione os nomes dos clientes à ComboBox
			for (String nomeCliente : nomesClientes) {
				if (!comboClientesDocAdm.getItems().contains(nomeCliente)) {
					comboClientesDocAdm.getItems().add(nomeCliente);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		comboClientesDocAdm.setOnAction(event -> {
			String clienteSelecionado = comboClientesDocAdm.getSelectionModel().getSelectedItem();
			if (clienteSelecionado != null) {
				// Chame um método para atualizar as TextFields com os dados do cliente
				atualizarTextFieldscomDadosCliente(clienteSelecionado);

				// Chame um método para atualizar a ListView com o nome e as matrículas do
				// cliente
				atualizarListViewComDadosCliente(clienteSelecionado);
			}
		});
		// COMBOBOX - TAB Petição Inicial
		try {
			List<String> nomesClientes = procDao.obterNomesClientes();
			Collections.sort(nomesClientes);
			// Adicione os nomes dos clientes à ComboBox
			for (String nomeCliente : nomesClientes) {
				if (!comboClientesPetInicial.getItems().contains(nomeCliente)) {
					comboClientesPetInicial.getItems().add(nomeCliente);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		comboClientesPetInicial.setOnAction(event -> {
			String clienteSelecionado = comboClientesPetInicial.getSelectionModel().getSelectedItem();
			if (clienteSelecionado != null) {
				// Chame um método para atualizar as TextFields com os dados do cliente
				atualizarTextFieldscomDadosClientePetInicial(clienteSelecionado);

				// Chame um método para atualizar a ListView com o nome e as matrículas do
				// cliente
				atualizarListViewComDadosClientePetiInicial(clienteSelecionado);
			}
		});
		listClientesPetInicial.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
		    // Chame o método para preencher as TextFields com base na seleção
		    preencherTextFieldsComDadosClientePetInicial(newValue);
		});
		listClientesPetInicial.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
		    if (newValue != null) {
		        String selectedItem = newValue.toString(); // Obtém o texto da linha selecionada

		        if (selectedItem.startsWith("Matrícula 1")) {
		            // Foi selecionada a Matrícula 1, você pode extrair os dados da matrícula 1 aqui
		         
		            // Extrair dados e preencher as TextFields aqui
		        } else if (selectedItem.startsWith("Matrícula 2")) {
		            // Foi selecionada a Matrícula 2, você pode extrair os dados da matrícula 2 aqui
		          
		            // Extrair dados e preencher as TextFields aqui
		        }
		    }
		});

		
		
		
		
		
		// COMBOBOX - TAB Petição Intercorrente
		try {
		    // Obtém os nomes de clientes do UserDao
		    List<String> nomesClientesUser = userDao.obterNomesClientes();
		    
		    // Obtém os nomes de clientes do ProcDao
		    List<String> nomesClientesProc = procDao.obterNomesClientes();
		    
		    // Combine os nomes de clientes de ambas as fontes em uma única lista
		    List<String> todosNomesClientes = new ArrayList<>(nomesClientesUser);
		    todosNomesClientes.addAll(nomesClientesProc);
		    
		    // Remova duplicatas, se necessário
		    todosNomesClientes = new ArrayList<>(new HashSet<>(todosNomesClientes));
		    
		    // Ordene os nomes dos clientes
		    Collections.sort(todosNomesClientes);
		    
		    // Limpe a ComboBox antes de adicionar os nomes dos clientes
		    comboClientesPetInter.getItems().clear();
		    
		    // Adicione os nomes dos clientes à ComboBox
		    comboClientesPetInter.getItems().addAll(todosNomesClientes);
		    
		} catch (SQLException e) {
		    e.printStackTrace();
		    // Lide com exceções SQL, se necessário
		}

		comboClientesPetInter.setOnAction(event -> {
		    Object selectedObject = comboClientesPetInter.getSelectionModel().getSelectedItem();
		    if (selectedObject != null) {
		        String clienteSelecionado = selectedObject.toString();
		        // Chame um método para atualizar a ListView com o Processo e matrícula do cliente
		        atualizarListViewComDadosClientePetIntercorrente(clienteSelecionado);
		    }
		});
		
	
		    setupListViewClickHandler();

		    
		
		
		
		
		
		
		
		
		// INITIALIZE PARA GERAÇÃO DE DOCUMENTOS
		//TAB DOCUMENTOS ADMINISTRATIVOS

		btnDeclaracaoDocAdm.setOnAction(event -> {
			try {
				gerarDeclaracaoDocAdm();
			} catch (IOException e) {

				e.printStackTrace();
			}
		});

		btnProcInterDocAdm.setOnAction(event -> {
			try {
				gerarProcuracaoInterDocAdm();
			} catch (IOException e) {

				e.printStackTrace();
			}
		});
		btnProcNEDocAdm.setOnAction(event -> {
			try {
				gerarProcuracaoNEDocAdm();
			} catch (IOException e) {

				e.printStackTrace();
			}
		});
		btnProcPisoDocAdm.setOnAction(event -> {
			try {
				gerarProcuracaoPisoDocAdm();
			} catch (IOException e) {

				e.printStackTrace();
			}
		});
		btnProcNEPisoDocAdm.setOnAction(event -> {
			try {
				gerarProcuracaoNEPisoDocAdm();
			} catch (IOException e) {

				e.printStackTrace();
			}
		});
		btnProcInterNEPisoDocAdm.setOnAction(event -> {
			try {
				gerarProcuracaoInterNEPisoDocAdm();
			} catch (IOException e) {

				e.printStackTrace();
			}
		});
		btnProcInterNEDocAdm.setOnAction(event -> {
			try {
				gerarProcuracaoInterNEDocAdm();
			} catch (IOException e) {

				e.printStackTrace();
			}
		});
		btnProcInterPisoDocAdm.setOnAction(event -> {
			try {
				gerarProcuracaoInterPisoDocAdm();
			} catch (IOException e) {

				e.printStackTrace();
			}
		});
		btnContratoIntNEPisoComDocAdm.setOnAction(event -> {
			try {
				gerarContratoIntNEPisoCom();
			} catch (IOException e) {

				e.printStackTrace();
			}
		});
		btnContratoIntNEPisoSemDocAdm.setOnAction(event -> {
			try {
				gerarContratoIntNEPisoSem();
			} catch (IOException e) {

				e.printStackTrace();
			}
		});
		btnContratoIntDocAdm.setOnAction(event -> {
			try {
				gerarContratoInt();
			} catch (IOException e) {

				e.printStackTrace();
			}
		});
		btnContratoIntPisoComDocAdm.setOnAction(event -> {
			try {
				gerarContratoIntPisoCom();
			} catch (IOException e) {

				e.printStackTrace();
			}
		});
		btnContratoPisoComDocAdm.setOnAction(event -> {
			try {
				gerarContratoPisoCom();
			} catch (IOException e) {

				e.printStackTrace();
			}
		});
		btnContratoPisoSemDocAdm.setOnAction(event -> {
			try {
				gerarContratoPisoSem();
			} catch (IOException e) {

				e.printStackTrace();
			}
		});
		btnContratoIntPisoSemDocAdm.setOnAction(event -> {
			try {
				gerarContratoIntPisoSem();
			} catch (IOException e) {

				e.printStackTrace();
			}
		});
		btnContratoIntNEDocAdm.setOnAction(event -> {
			try {
				gerarContratoIntNE();
			} catch (IOException e) {

				e.printStackTrace();
			}
		});
		btnContratoNEDocAdm.setOnAction(event -> {
			try {
				gerarContratoNE();
			} catch (IOException e) {

				e.printStackTrace();
			}
		});
		btnContratoNEPisoComDocAdm.setOnAction(event -> {
			try {
				gerarContratoNEPisoSem();
			} catch (IOException e) {

				e.printStackTrace();
			}
		});

		btnContratoNEPisoSemDocAdm.setOnAction(event -> {
			try {
				gerarContratoNEPisoSem();
			} catch (IOException e) {

				e.printStackTrace();
			}
		});

		
		
		
		
		
		
		//TAB PETIÇÕES INICIAIS
		
		
		
		
		
		//TAB PETIÇÕES INTERCORRENTES
		
		btnPeticaoRespostaImpugnacaoERJNE.setOnAction(event -> {
			try {
				gerarPeticaoRespostaImpugnacaoERJNE();
			} catch (IOException e) {

				e.printStackTrace();
			}
		});
		btnEDsAvaliacaoAplicada.setOnAction(event -> {
			try {
				gerarPeticaoEDsAvaliacaoAplicada();
			} catch (IOException e) {

				e.printStackTrace();
			}
		});
		
		
		    // Configurar os eventos para os botões
		    btnPeticaoconcordandocalculoscontadorNE.setOnAction(event -> {
		        try {
		            gerarPeticaoConcordandoCalculosContadorNE();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    });

		    btnPeticaodescabimentoSuspensaoNE.setOnAction(event -> {
		        try {
		            gerarPeticaoDescabimentoSuspensaoNE();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    });

		    btnPeticaoProvasNE.setOnAction(event -> {
		        try {
		            gerarPeticaoProvasNE();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    });

		    btnPeticaoSobreParametrosNE.setOnAction(event -> {
		        try {
		            gerarPeticaoSobreParametrosNE();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    });
		
		
		
		
		// FECHA INITIALIZE
	}

	
	//TAB DOCUMENTOS ADMINISTRATIVOS
	//METODO PARA INFORMAÇÕES APARECEREM NAS TEXTFIELDS
	private void atualizarTextFieldscomDadosCliente(String DadosClienteSelecionado) {
		UserDao userDao = new UserDao();
		try {
			User clienteSelecionado = userDao.buscaClientePeloNomeTabEditar(DadosClienteSelecionado);

			User clienteSelecionadoAdm;

			if (clienteSelecionado != null) {
				// Se o processo for encontrado, atualize os campos TextField
				txtNomeDocAdm.setText(clienteSelecionado.getnome());
				txtNacionalidadeDocAdm.setText(clienteSelecionado.getnacionalidade());
				txtEstadoCivilDocAdm.setText(clienteSelecionado.getestadocivil());
				txtProfissaoDocAdm.setText(clienteSelecionado.getprofissao());
				txtRGDocAdm.setText(clienteSelecionado.getrg());
				txtCPFDocAdm.setText(clienteSelecionado.getcpf());
				txtIdFDocAdm.setText(clienteSelecionado.getidfuncional());
				txtEnderecoDocAdm.setText(clienteSelecionado.getendereco() + " " + clienteSelecionado.getcidade() + " "
						+ clienteSelecionado.getestado());
				txtCEPDocAdm.setText(clienteSelecionado.getcep());

			} else {
				// Limpe os campos Label se nenhum cliente for encontrado
				txtNomeDocAdm.clear();
				txtNacionalidadeDocAdm.clear();
				txtEstadoCivilDocAdm.clear();
				txtProfissaoDocAdm.clear();
				txtRGDocAdm.clear();
				txtCPFDocAdm.clear();
				txtIdFDocAdm.clear();
				txtEnderecoDocAdm.clear();
				txtCEPDocAdm.clear();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private ListView<String> listClientesDocAdm = new ListView<>();

	private void atualizarListViewComDadosCliente(String nomeCliente) {
		// Limpe a ListView
		listClientesDocAdm.getItems().clear();

		UserDao userDao = new UserDao();
		try {
			User clienteSelecionado = userDao.buscaClientePeloNomeTabEditar(nomeCliente);

			if (clienteSelecionado != null) {
				// Adicione o nome do cliente
			
				listClientesDocAdm.getItems().add("Nome: " + clienteSelecionado.getnome());

				// Verifique se mat1 não está vazio
				if (!clienteSelecionado.getmat1().isEmpty()) {
				
					listClientesDocAdm.getItems().add("Matrícula 1: " + clienteSelecionado.getmat1());
				}

				// Verifique se mat2 não está vazio ou é nulo

				if (clienteSelecionado.getmat2() != null && !clienteSelecionado.getmat2().isEmpty()) {
				
					listClientesDocAdm.getItems().add("Matrícula 2: " + clienteSelecionado.getmat2());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	//METODOS TAB PETIÇÕES INICIAIS
	private void atualizarListViewComDadosClientePetiInicial(String nomeCliente) {
		// Limpe a ListView
		listClientesPetInicial.getItems().clear();

		UserDao userDao = new UserDao();
		try {
			User clienteSelecionado = userDao.buscaClientePeloNomeTabEditar(nomeCliente);

			if (clienteSelecionado != null) {
				
				// Verifique se mat1 não está vazio
				if (!clienteSelecionado.getmat1().isEmpty()) {
				    listClientesPetInicial.getItems().add("Matrícula 1: " + clienteSelecionado.getmat1());
				}

				if (clienteSelecionado.getmat2() != null && !clienteSelecionado.getmat2().isEmpty()) {
				    listClientesPetInicial.getItems().add("Matrícula 2: " + clienteSelecionado.getmat2());
					}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//METODO PARA INFORMAÇÕES APARECEREM NAS TEXTFIELDS
		private void atualizarTextFieldscomDadosClientePetInicial (String DadosClienteSelecionadoInicial) {
			UserDao userDao = new UserDao();
			try {
				User clienteSelecionadoPI = userDao.buscaClientePeloNomeTabEditar(DadosClienteSelecionadoInicial);

				User clienteSelecionadoInicial;

				if (clienteSelecionadoPI != null) {
					// Se o processo for encontrado, atualize os campos TextField
					txtNomePetInicial.setText(clienteSelecionadoPI.getnome());
					txtNacionalidadePetInicial.setText(clienteSelecionadoPI.getnacionalidade());
					txtEstadoCivilPetInicial.setText(clienteSelecionadoPI.getestadocivil());
					txtProfissaoDPetInicial.setText(clienteSelecionadoPI.getprofissao());
					txtRGPetInicial.setText(clienteSelecionadoPI.getrg());
					txtCPFPetInicial.setText(clienteSelecionadoPI.getcpf());
					txtIdFPetInicial.setText(clienteSelecionadoPI.getidfuncional());
					txtEnderecoPetInicial.setText(clienteSelecionadoPI.getendereco() + " " + clienteSelecionadoPI.getcidade() + " "
							+ clienteSelecionadoPI.getestado());
					txtCEPPetInicial.setText(clienteSelecionadoPI.getcep());
					txtDataNascimentoPetInicial.setText(clienteSelecionadoPI.getdatanascimento());

				} else {
					// Limpe os campos Label se nenhum cliente for encontrado
					txtNomePetInicial.clear();
					txtNacionalidadePetInicial.clear();
					txtEstadoCivilPetInicial.clear();
					txtProfissaoDPetInicial.clear();
					txtRGPetInicial.clear();
					txtCPFPetInicial.clear();
					txtIdFPetInicial.clear();
					txtEnderecoPetInicial.clear();
					txtCEPPetInicial.clear();
					txtDataNascimentoPetInicial.clear();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		private void preencherTextFieldsComDadosClientePetInicial(String selecao) {
		    if (selecao != null) {
		        // Extrai a parte relevante da linha (por exemplo, "Matrícula 1" ou "Matrícula 2")
		        String parteRelevante = selecao.split(":")[0].trim();

		        UserDao userDao = new UserDao(); // Crie um objeto UserDao se já não existir

		        try {
		            User clienteSelecionado = userDao.buscaClientePeloNomeTabEditar(comboClientesPetInicial.getValue()); // Obtém o nome do cliente selecionado na ComboBox

		            if (clienteSelecionado != null) {
		                if (parteRelevante.equals("Matrícula 1")) {
		                 
		                    // Preencha as TextFields com os dados correspondentes a mat1
		                    txtMatPetInicial.setText(clienteSelecionado.getmat1());
		                    txtRefPetInicial.setText(clienteSelecionado.getref1());
		                    txtCargaHPetInicial.setText(clienteSelecionado.getcargh1());
		                    txtDataInicioPetInicial.setText(clienteSelecionado.getdatainicio1());
		                    txtCargoPetInicial.setText(clienteSelecionado.getcargo1());
		                    txtNivelPetInicial.setText(clienteSelecionado.getnivel1());
		                    txtTrienioPetInicial.setText(clienteSelecionado.gettrienio1());
		                    txtDataaposentadoriaPetInicial.setText(clienteSelecionado.getdataaposentadoria1());
		                } else if (parteRelevante.equals("Matrícula 2")) {
		               
		                    // Preencha as TextFields com os dados correspondentes a mat2
		                    txtMatPetInicial.setText(clienteSelecionado.getmat2());
		                    txtRefPetInicial.setText(clienteSelecionado.getref2());
		                    txtCargaHPetInicial.setText(clienteSelecionado.getcargh2());
		                    txtDataInicioPetInicial.setText(clienteSelecionado.getdatainicio2());
		                    txtCargoPetInicial.setText(clienteSelecionado.getcargo2());
		                    txtNivelPetInicial.setText(clienteSelecionado.getnivel2());
		                    txtTrienioPetInicial.setText(clienteSelecionado.gettrienio2());
		                    txtDataaposentadoriaPetInicial.setText(clienteSelecionado.getdataaposentadoria2());
		                }
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    } else {
		        // Limpe as TextFields se nenhum item for selecionado
		        txtMatPetInicial.clear();
		        txtRefPetInicial.clear();
		        txtCargaHPetInicial.clear();
		        txtDataInicioPetInicial.clear();
		        txtCargoPetInicial.clear();
		        txtNivelPetInicial.clear();
		        txtTrienioPetInicial.clear();
		        txtDataaposentadoriaPetInicial.clear();
		    }
		}


	
		//TAB PETIÇÃO INTERCORRENTE
		
		public void atualizarListViewComDadosClientePetIntercorrente(String nomeCliente) {
		    try {
		        // Chame o método do ProcDao para obter a lista de processos para o cliente selecionado
		        List<Proc> processos = procDao.obterNumerosProcessoseMatriculaPorNomeCliente(nomeCliente);

		        // Limpe a ListView
		        listClientesPetInter.getItems().clear();

		        // Percorra a lista de processos e adicione as informações à ListView
		        for (Proc processo : processos) {
		            String numeroProcesso = processo.getnumeroprocesso();
		            String matricula = processo.getMatricula();
		            String info = numeroProcesso + " - " + matricula;
		            listClientesPetInter.getItems().add(info);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        // Lide com exceções SQL, se necessário
		    }
		}

		private void setupListViewClickHandler() {
		    listClientesPetInter.setOnMouseClicked(event -> {
		        // Verifique se um item da ListView foi clicado
		        Object itemSelecionado = listClientesPetInter.getSelectionModel().getSelectedItem();

		        if (itemSelecionado != null && itemSelecionado instanceof String) {
		            // Converta o item selecionado para uma String
		            String linhaSelecionada = (String) itemSelecionado;

			        // Divida a linha pelo caractere '-' para extrair a matrícula
			        String[] partes = linhaSelecionada.split(" - ");

			        // Verifique se a linha foi dividida corretamente (deve haver pelo menos duas partes)
			        if (partes.length >= 2) {
			            // Remova espaços em branco em excesso e obtenha a matrícula
			            String matricula = partes[1].trim();
			            String numeroProcesso = partes[0].trim();

			            // Defina a matrícula na txtMatPetInter
			            txtMatPetInter.setText(matricula);
			            txtNumeroProcInter.setText(numeroProcesso);
			            try {
		                    UserDao userDao = new UserDao();
		                    boolean matriculaExiste = userDao.verificaMatricula(matricula);

		                    if (matriculaExiste) {
		                        // Matrícula existe na base de dados, agora preencha as TextFields
		                        User user = userDao.obterUsuarioPelaMatricula(matricula);
		                        if (user != null) {
		                            // Verifique qual matrícula corresponde e preencha as TextFields adequadamente
		                            if (matricula.equals(user.getmat1())) {
		                                // Matrícula corresponde a mat1
		                         
		                            	txtNomePetInter.setText(user.getnome());
		                                txtRefPetInter.setText(user.getref1());
		                                txtCargaHPetInter.setText(user.getcargh1());
		                                txtCargoPetInter.setText(user.getcargo1());
		                                txtNivelPetInter.setText(user.getnivel1());
		                                
		                            } else if (matricula.equals(user.getmat2())) {
		                                // Matrícula corresponde a mat2
		                           
		                            	txtNomePetInter.setText(user.getnome());
		                                txtRefPetInter.setText(user.getref2());
		                                txtCargaHPetInter.setText(user.getcargh2());
		                                txtCargoPetInter.setText(user.getcargo2());
		                                txtNivelPetInter.setText(user.getnivel2());
		                            }
		                        }
		                    } else {
		                        // Matrícula não existe na base de dados, faça o que for necessário aqui
		                    }
		                } catch (SQLException e) {
		                    // Trate exceções de SQL aqui
		                }
			         // Obtenha o número do processo da txtNumeroProcInter
			            String numeroDoProcesso = txtNumeroProcInter.getText();

			            try {
			                ProcDao procDao = new ProcDao();
			                String tema = procDao.obterTemaPorNumeroProcesso(numeroProcesso);

			                if (tema != null) {
			                    // Configure o tema na txtTemaProcesso
			                    txtTemaProcesso.setText(tema);
			                } else {
			                    // Não foi encontrado um tema para o número do processo
			                    // Trate conforme necessário
			                }
			            } catch (SQLException e) {
			                // Trate exceções de SQL aqui
			            }
			            String numeroDoProcessoVara = txtNumeroProcInter.getText();
			            try {
			                ProcDao procDao = new ProcDao();
			                String vara = procDao.obterVaraPorNumeroProcesso(numeroDoProcessoVara);
			                if (vara != null) {
			                  txtVaraProcesso.setText(vara);
			                } else {
			                }
			            } catch (SQLException e) {
			            }
			            String numeroDoProcessoComarca = txtNumeroProcInter.getText();
			            try {
			                ProcDao procDao = new ProcDao();
			                String comarca = procDao.obterComarcaPorNumeroProcesso(numeroDoProcessoComarca);
			                if (comarca != null) {
			                	txtComarcaProcesso.setText(comarca);
			                } else {
			                }
			            } catch (SQLException e) {
			            }
		            }
		        }
		    });
		}

		
		
		
		
		
		
		
		
		
	
	

	// CONTROLES PARA GERAÇÃO DE DOCUMENTOS
	//TAB Documentos Administrativos
	@FXML
	private void gerarDeclaracaoDocAdm() throws IOException {
		String nome = txtNomeDocAdm.getText();
		String nacionalidade = txtNacionalidadeDocAdm.getText();
		String estadoCivil = txtEstadoCivilDocAdm.getText();
		String profissao = txtProfissaoDocAdm.getText();
		String rg = txtRGDocAdm.getText();
		String cpf = txtCPFDocAdm.getText();
		String idFuncional = txtIdFDocAdm.getText();
		String endereco = txtEnderecoDocAdm.getText();
		String cep = txtCEPDocAdm.getText();

		// Verifique se algum campo está em branco
		if (nome.isEmpty() || nacionalidade.isEmpty() || estadoCivil.isEmpty() || profissao.isEmpty() || rg.isEmpty()
				|| cpf.isEmpty() || idFuncional.isEmpty() || endereco.isEmpty() || cep.isEmpty()) {
			// Exiba uma mensagem de erro se algum campo estiver em branco
			alert("Erro", "Todos os campos devem ser preenchidos.", AlertType.ERROR);
		} else {
			// Carregue o modelo existente
			FileInputStream templateFile = new FileInputStream(
					"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\DocumentosBase\\DECLARAÇÃO_HIPOSSUFICIÊNCIA.docx");
			XWPFDocument document = new XWPFDocument(templateFile);

			// Crie um mapa para armazenar os dados do cliente
			Map<String, String> dataMap = new HashMap<>();
			dataMap.put("${nome}", nome);
			dataMap.put("NACIONALIDADE", nacionalidade);
			dataMap.put("PROFISSAO", estadoCivil);
			dataMap.put("ESTADOCIVIL", profissao);
			dataMap.put("NRG", rg);
			dataMap.put("CPF", cpf);
			dataMap.put("IDFUNCIONAL", idFuncional);
			dataMap.put("ENDERECO", endereco);
			dataMap.put("${cep}", cep);

			// Antes da substituição
			System.out.println("Valores antes da substituição:");
			for (Map.Entry<String, String> entry : dataMap.entrySet()) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}

			// Substituição dos marcadores de posição

			// Depois da substituição
			System.out.println("Valores após a substituição:");
			for (XWPFParagraph paragraph : document.getParagraphs()) {
				for (XWPFRun run : paragraph.getRuns()) {
					String text = run.getText(0);
					System.out.println("Texto: " + text);
				}
			}
			// Substitua os marcadores de posição pelos dados do cliente
			for (XWPFParagraph paragraph : document.getParagraphs()) {
				for (XWPFRun run : paragraph.getRuns()) {
					String text = run.getText(0);
					for (Map.Entry<String, String> entry : dataMap.entrySet()) {
						if (text != null && text.contains(entry.getKey())) {
							text = text.replace(entry.getKey(), entry.getValue());
							run.setText(text, 0);
						}
					}
				}
			}

			// Salve o documento em um arquivo
			FileOutputStream out = new FileOutputStream(
					"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\DocumentosBase\\Gerados\\DocumentoGerado.docx");
			document.write(out);
			out.close();
			document.close();
			templateFile.close();

			// Exiba uma mensagem de sucesso após a geração da declaração
			alert("Sucesso", "Declaração de Hipossuficiência gerada com sucesso.", AlertType.INFORMATION);
		}
	}

	public static void gerarDeclaracaoHipo(String nome, String nacionalidade, String estadoCivil, String profissao,
			String rg, String cpf, String idFuncional, String endereco, String cep) {

		try {
			// Carregue o modelo existente
			FileInputStream templateFile = new FileInputStream(
					"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\DocumentosBase\\DECLARAÇÃO_HIPOSSUFICIÊNCIA.docx");
			XWPFDocument document = new XWPFDocument(templateFile);

			// Crie um mapa para armazenar os dados do cliente
			Map<String, String> dataMap = new HashMap<>();
			dataMap.put("${nome}", nome);
			dataMap.put("NACIONALIDADE", nacionalidade);
			dataMap.put("PROFISSAO", estadoCivil);
			dataMap.put("ESTADOCIVIL", profissao);
			dataMap.put("NRG", rg);
			dataMap.put("CPF", cpf);
			dataMap.put("IDFUNCIONAL", idFuncional);
			dataMap.put("ENDERECO", endereco);
			dataMap.put("${cep}", cep);

			// Substitua os marcadores de posição pelos dados do cliente
			for (XWPFParagraph paragraph : document.getParagraphs()) {
				for (XWPFRun run : paragraph.getRuns()) {
					String text = run.getText(0);
					for (Map.Entry<String, String> entry : dataMap.entrySet()) {
						if (text != null && text.contains(entry.getKey())) {
							text = text.replace(entry.getKey(), entry.getValue());
							run.setText(text, 0);
						}
					}
				}
			}

			// Salve o documento em um arquivo
			FileOutputStream out = new FileOutputStream("DeclaracaoHipossuficienciaPreenchida.docx");
			document.write(out);
			out.close();
			document.close();

			// Feche o modelo
			templateFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void gerarDocumentoComModelo(String modeloPath, String nomeDocumento) throws IOException {
		String nome = txtNomeDocAdm.getText();
		String nacionalidade = txtNacionalidadeDocAdm.getText();
		String estadoCivil = txtEstadoCivilDocAdm.getText();
		String profissao = txtProfissaoDocAdm.getText();
		String rg = txtRGDocAdm.getText();
		String cpf = txtCPFDocAdm.getText();
		String idFuncional = txtIdFDocAdm.getText();
		String endereco = txtEnderecoDocAdm.getText();
		String cep = txtCEPDocAdm.getText();

		// Verifique se algum campo está em branco
		if (nome.isEmpty() || nacionalidade.isEmpty() || estadoCivil.isEmpty() || profissao.isEmpty() || rg.isEmpty()
				|| cpf.isEmpty() || idFuncional.isEmpty() || endereco.isEmpty() || cep.isEmpty()) {
			// Exiba uma mensagem de erro se algum campo estiver em branco
			alert("Erro", "Todos os campos devem ser preenchidos.", AlertType.ERROR);
		} else {
			// Carregue o modelo existente
			FileInputStream templateFile = new FileInputStream(modeloPath);
			XWPFDocument document = new XWPFDocument(templateFile);

			// Crie um mapa para armazenar os dados do cliente
			Map<String, String> dataMap = new HashMap<>();
			dataMap.put("${nome}", nome);
			dataMap.put("NACIONALIDADE", nacionalidade);
			dataMap.put("PROFISSAO", estadoCivil);
			dataMap.put("ESTADOCIVIL", profissao);
			dataMap.put("IDENTIDADE", rg);
			dataMap.put("CPF", cpf);
			dataMap.put("IDFUNCIONAL", idFuncional);
			dataMap.put("ENDERECO", endereco);
			dataMap.put("${cep}", cep);

			// Substitua os marcadores de posição pelos dados do cliente
			for (XWPFParagraph paragraph : document.getParagraphs()) {
				for (XWPFRun run : paragraph.getRuns()) {
					String text = run.getText(0);
					for (Map.Entry<String, String> entry : dataMap.entrySet()) {
						if (text != null && text.contains(entry.getKey())) {
							text = text.replace(entry.getKey(), entry.getValue());
							run.setText(text, 0);
						}
					}
				}
			}

			// Salve o documento em um arquivo
			FileOutputStream out = new FileOutputStream(
					"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\DocumentosBase\\Gerados\\"
							+ nomeDocumento + ".docx");
			document.write(out);
			out.close();
			document.close();
			templateFile.close();

			// Exiba uma mensagem de sucesso após a geração do documento
			alert("Sucesso", nomeDocumento + " gerado com sucesso.", AlertType.INFORMATION);
		}
	}

	// Métodos para gerar documentos com base nos botões
	@FXML
	private void gerarProcuracaoInterDocAdm() throws IOException {
		gerarDocumentoComModelo(
				"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\DocumentosBase\\Procuração Interníveis.docx",
				"ProcuracaoInterniveis");
	}

	@FXML
	private void gerarProcuracaoNEDocAdm() throws IOException {
		gerarDocumentoComModelo(
				"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\DocumentosBase\\Procuração NE.docx",
				"ProcuracaoNovaEscola");
	}

	@FXML
	private void gerarProcuracaoPisoDocAdm() throws IOException {
		gerarDocumentoComModelo(
				"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\DocumentosBase\\Procuração Piso.docx",
				"procuracaoPiso");
	}

	@FXML
	private void gerarProcuracaoNEPisoDocAdm() throws IOException {
		gerarDocumentoComModelo(
				"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\DocumentosBase\\Procuração NE + Piso.docx",
				"ProcuracaoNEPiso");
	}

	@FXML
	private void gerarProcuracaoInterNEPisoDocAdm() throws IOException {
		gerarDocumentoComModelo(
				"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\DocumentosBase\\Procuração Interníveis + NE + Piso.docx",
				"ProcuracaoInterNEPiso");
	}

	@FXML
	private void gerarProcuracaoInterNEDocAdm() throws IOException {
		gerarDocumentoComModelo(
				"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\DocumentosBase\\Procuração Interníveis + NE.docx",
				"ProcuracaoInterNE");
	}

	@FXML
	private void gerarProcuracaoInterPisoDocAdm() throws IOException {
		gerarDocumentoComModelo(
				"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\DocumentosBase\\Procuração Interníveis + Piso.docx",
				"ProcuracaoInterPiso");
	}

	@FXML
	private void gerarContratoIntNEPisoCom() throws IOException {
		gerarDocumentoComModelo("C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\DocumentosBase\\Contrato Interníveis + NE + Piso com liminar.docx" , "ContratoIntNEPisoCom");
	}

	@FXML
	private void gerarContratoIntNEPisoSem() throws IOException {
		gerarDocumentoComModelo(
				"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\DocumentosBase\\Contrato Interníveis + NE + Piso sem liminar.docx",
				"ContratoIntNEPisoSem");
	}

	@FXML
	private void gerarContratoInt() throws IOException {
		gerarDocumentoComModelo(
				"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\DocumentosBase\\Contrato Interníveis.docx",
				"ContratoInt");
	}

	@FXML
	private void gerarContratoIntPisoCom() throws IOException {
		gerarDocumentoComModelo(
				"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\DocumentosBase\\Contrato Interníveis + Piso com liminar.docx",
				"ContratoIntPisoCom");
	}

	@FXML
	private void gerarContratoPisoCom() throws IOException {
		gerarDocumentoComModelo(
				"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\DocumentosBase\\Contrato Piso com liminar.docx",
				"ContratoPisoCom");
	}

	@FXML
	private void gerarContratoPisoSem() throws IOException {
		gerarDocumentoComModelo(
				"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\DocumentosBase\\Contrato Piso sem liminar.docx",
				"ContratoPisoSem");
	}

	@FXML
	private void gerarContratoIntPisoSem() throws IOException {
		gerarDocumentoComModelo(
				"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\DocumentosBase\\Contrato Interníveis + Piso sem liminar.docx",
				"ContratoIntPisoSem");
	}

	@FXML
	private void gerarContratoIntNE() throws IOException {
		gerarDocumentoComModelo(
				"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\DocumentosBase\\Contrato Interníveis + NE.docx",
				"ContratoIntNE");
	}

	@FXML
	private void gerarContratoNE() throws IOException {
		gerarDocumentoComModelo(
				"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\DocumentosBase\\Contrato Nova Escola.docx",
				"ContratoNE");
	}

	@FXML
	private void gerarContratoNEPisoSem() throws IOException {
		gerarDocumentoComModelo(
				"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\DocumentosBase\\Contrato NE + Piso sem liminar.docx",
				"ContratoNEPisoSem");
	}

	@FXML
	private void gerarContratoNEPisoCom() throws IOException {
		gerarDocumentoComModelo(
				"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\DocumentosBase\\Contrato NE + Piso com liminar.docx",
				"ContratoNEPisoCom");
	}
	
	
	
	
	
	
	
	//TAB PETIÇÕES INICIAIS
	
	
	
	
	//TAB PETIÇÕES INTERCORRENTES
	
	private void gerarPeticaoModelo(String modeloPath, String nomePeticao) throws IOException {
		String nome = txtNomePetInter.getText();
		String numeroprocesso = txtNumeroProcInter.getText();
		String matricula = txtMatPetInter.getText();
		String referencia = txtRefPetInter.getText();
		String cargo = txtCargoPetInter.getText();
		String cargahoraria = txtCargaHPetInter.getText();
		String nivel = txtNivelPetInter.getText();
		String vara = txtVaraProcesso.getText();
		String comarca = txtComarcaProcesso.getText();

		// Verifique se algum campo está em branco
		if (nome.isEmpty() || numeroprocesso.isEmpty() || matricula.isEmpty() || referencia.isEmpty() || cargo.isEmpty()
				|| cargahoraria.isEmpty() || nivel.isEmpty() || vara.isEmpty() || comarca.isEmpty()) {
			// Exiba uma mensagem de erro se algum campo estiver em branco
			alert("Erro", "Todos os campos devem ser preenchidos.", AlertType.ERROR);
		} else {
			// Carregue o modelo existente
			FileInputStream templateFile = new FileInputStream(modeloPath);
			XWPFDocument document = new XWPFDocument(templateFile);

			// Crie um mapa para armazenar os dados do cliente
			Map<String, String> dataMap = new HashMap<>();
			dataMap.put("[NOME]", nome);
			dataMap.put("[PROCESSO]", numeroprocesso);
			dataMap.put("PROFISSAO", matricula);
			dataMap.put("ESTADOCIVIL", referencia);
			dataMap.put("IDENTIDADE", cargo);
			dataMap.put("CPF", cargahoraria);
			dataMap.put("IDFUNCIONAL", nivel);
			dataMap.put("[VARA]", vara);
			dataMap.put("xxxxxxx", comarca);

			// Substitua os marcadores de posição pelos dados do cliente
			for (XWPFParagraph paragraph : document.getParagraphs()) {
				for (XWPFRun run : paragraph.getRuns()) {
					String text = run.getText(0);
					for (Map.Entry<String, String> entry : dataMap.entrySet()) {
						if (text != null && text.contains(entry.getKey())) {
							text = text.replace(entry.getKey(), entry.getValue());
							run.setText(text, 0);
						}
					}
				}
			}

			// Salve o documento em um arquivo
			FileOutputStream out = new FileOutputStream(
					"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\Documentos Base - Petições intercorrentes\\Gerados\\"
							+ nomePeticao + ".docx");
			document.write(out);
			out.close();
			document.close();
			templateFile.close();

			// Exiba uma mensagem de sucesso após a geração do documento
			alert("Sucesso", nomePeticao + " gerado com sucesso.", AlertType.INFORMATION);
		}
	}
	
	@FXML
	private void gerarPeticaoRespostaImpugnacaoERJNE() throws IOException {
		gerarPeticaoModelo(
				"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\Documentos Base - Petições intercorrentes\\Modelos\\NE Petição resposta a impugnação ERJ.docx",
				"RespostaImpugnacaoERJ-NE");
	}
	@FXML
	private void gerarPeticaoEDsAvaliacaoAplicada() throws IOException {
		gerarPeticaoModelo(
				"C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\Documentos Base - Petições intercorrentes\\Modelos\\NE EDs avaliação aplicada - NE.docx",
				"NE EDs avaliação aplicada - NE");
	}
	
	@FXML
	private void gerarPeticaoConcordandoCalculosContadorNE() throws IOException {
	    gerarPeticaoModelo(
	        "C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\Documentos Base - Petições intercorrentes\\Modelos\\NE Petição concordando com calculos contador.docx",
	        "NE Petição concordando com calculos contador");
	}

	@FXML
	private void gerarPeticaoDescabimentoSuspensaoNE() throws IOException {
	    gerarPeticaoModelo(
	        "C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\Documentos Base - Petições intercorrentes\\Modelos\\NE Petição resposta Pet ERJ NE - descabimento Suspensão.docx",
	        "NE Petição resposta Pet ERJ NE - descabimento Suspensão");
	}

	@FXML
	private void gerarPeticaoProvasNE() throws IOException {
	    gerarPeticaoModelo(
	        "C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\Documentos Base - Petições intercorrentes\\Modelos\\NE Petição sobre provas.docx",
	        "NE Petição sobre provas");
	}

	@FXML
	private void gerarPeticaoSobreParametrosNE() throws IOException {
	    gerarPeticaoModelo(
	        "C:\\Users\\lizwf\\OneDrive\\Área de Trabalho\\Software Werner ADV\\Documentos Base - Petições intercorrentes\\Modelos\\NE Petição sobre aplicação de avaliação de 2001 - NE.docx",
	        "NE Petição sobre aplicação de avaliação de 2001 - NE");
	}

	
	
	
	
	
	
	
	
	

}
