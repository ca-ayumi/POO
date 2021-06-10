package ExamesClinicos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ExamesClinicos1 {

	private ArrayList<Exame> exames;


	public ExamesClinicos1( ) {
		this.exames = new ArrayList<Exame>();
	}

	public void adicionaExame(Exame menu) {
		this.exames.add(menu);
	}

	public void listarExame() {
		for(Exame menu:exames) {
			System.out.println(menu.toString());
		}
		System.out.println("Total = " + this.exames.size() + " exames listados com sucesso!\n");
	}
	
	public void excluirExame(Exame menu) {
		if (this.exames.contains(menu)) {
			this.exames.remove(menu);
			System.out.println("[Exame " + menu.toString() + "excluido com sucesso!]\n");
		}
		else
			System.out.println("Exame inexistente!\n");
	}

	public void excluirExame() {
		exames.clear();
		System.out.println("Exames excluídos com sucesso!\n");
	}
	public void gravarExame()  {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream (new FileOutputStream("c:\\temp\\exames.dat"));
			for(Exame menu:exames) {
				outputStream.writeObject(menu);
			}
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (outputStream != null ) {
					outputStream.flush();
					outputStream.close();
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public void recuperarExame() {
		ObjectInputStream inputStream = null;
		try {
			inputStream	= new ObjectInputStream (new FileInputStream ("c:\\temp\\exames.dat"));
			Object obj = null;
			while((obj = inputStream.readObject ()) != null) {
				if (obj instanceof Sangue)  
					this.exames.add((Sangue)obj);
				else if (obj instanceof Ecografia)  
					this.exames.add((Ecografia)obj);
                else if (obj instanceof RaioX)  
					this.exames.add((RaioX)obj);
			}
		}catch (EOFException ex) {     // when EOF is reached
			System.out.println ("End of file reached");
		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (inputStream != null ) {
					inputStream.close();
					System.out.println("Exames recuperados com sucesso!\n");
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}


	// public static void main(String[] args) {
	// 	ExamesClinicos1 exam  = new ExamesClinicos1();

	// 	Sangue camila    = new Sangue("Unimed","Dr. Rossival", "Camila");
	// 	// Gato garfield = new Gato("Garfield", 7, "Maria");
	// 	// Cao  rex      = new Cao ("Rex",  2, "Jose");
	// 	// Cao  toto     = new Cao ("Toto", 5, "Jose");
        
	// 	exam.adicionaExame(camila);
	// 	// exam.adicionaExame(garfield);
	// 	// exam.adicionaExame(rex);
	// 	// exam.adicionaExame(toto);
	// 	exam.listarExame();
	// 	exam.gravarExame();
	// 	exam.excluirExame();
	// 	exam.listarExame();
	// 	exam.excluirExame();
	// 	exam.listarExame();
	// 	exam.recuperarExame();
	// 	exam.listarExame();
	// }

}

