package ExamesClinicos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

//comentário

public class ExamesClinicos {

	private ArrayList<Exame> exames;

	public ExamesClinicos() {
		this.exames = new ArrayList<Exame>();
	}
	public String[] leValores (String [] dadosIn){
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

		return dadosOut;
	}

	public Sangue leSangue (){

		String [] valores = new String [3];
		String [] nomeVal = {"Convênio", "Nome do Médico", "Nome do Paciente"};
		valores = leValores (nomeVal);

		Sangue sangue = new Sangue (valores[0],valores[1],valores[2]);
		return sangue;
	}

	public RaioX leRaioX (){

		String [] valores = new String [3];
		String [] nomeVal = {"Convênio", "Nome do Médico", "Nome do Paciente"};
		valores = leValores (nomeVal);

		RaioX raioX = new RaioX (valores[0],valores[1],valores[2]);
		return raioX ;
	}

    public Ecografia leEcografia (){

		String [] valores = new String [3];
		String [] nomeVal = {"Convênio", "Nome do Médico", "Nome do Paciente"};
		valores = leValores (nomeVal);

		Ecografia ecografia = new Ecografia (valores[0],valores[1],valores[2]);
		return ecografia ;
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s); 
			return true;
		} catch (NumberFormatException e) { 
			return false;
		}
	}
	public int retornaInteiro(String entrada) { 


		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
		}
		return Integer.parseInt(entrada);
	}

	public void salvaExame (ArrayList<Exame> exames){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("c:\\temp\\examesClinicos.dados"));
			for (int i=0; i < exames.size(); i++)
				outputStream.writeObject(exames.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Impossível criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectOutputStream
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Exame> recuperaExames (){
		ArrayList<Exame> examesTemp = new ArrayList<Exame>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("c:\\temp\\examesClinicos.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Exame) {
					examesTemp.add((Exame) obj);
				}   
			}          
		} catch (EOFException ex) { 
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo com exames não existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return examesTemp;
		}
	}

	public void menuExamesClinicos (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Controle Exames Clínicos\n" +
					"Opções:\n" + 
					"1. Entrar Exames\n" +
					"2. Exibir Exames\n" +
					"3. Limpar Exames\n" +
					"4. Gravar Exames\n" +
					"5. Recuperar Exames\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:// Entrar dados
				menu = "Entrada de Exames\n" +
						"Opções:\n" + 
						"1. Exame de Sangue\n" +
						"2. Ecografia\n" +
                        "3. Raio X\n";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: exames.add((Exame)leSangue());
				break;
				case 2: exames.add((Exame)leEcografia());
				break;
                case 3: exames.add((Exame)leRaioX());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"Exame para entrada NÃO escolhido!");
				}

				break;
			case 2:
				if (exames.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com exame primeiramente");
					break;
				}
				String dados = "";
				for (int i=0; i < exames.size(); i++)	{
					dados += exames.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
			case 3: 
				if (exames.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com exames primeiramente");
					break;
				}
				exames.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
			case 4: 
				if (exames.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com exames primeiramente");
					break;
				}
				salvaExame(exames);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
			case 5:
				exames = recuperaExames();
				if (exames.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo EXAMES CLÍNICOS");
				break;
			}
		} while (opc1 != 9);
	}


	public static void main (String [] args){

		ExamesClinicos exam = new ExamesClinicos() ;
		exam.menuExamesClinicos();

	}

}