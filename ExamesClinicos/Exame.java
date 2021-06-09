package ExamesClinicos;

import java.io.Serializable;

public abstract class Exame implements Serializable {

	private static final long serialVersionUID = 1L;
	private   String convenio;
	private   String nomeDoMedico;
	private   String nomeDoPaciente;
	protected String tipoDoExame;
	
	public Exame(String convenio, String nomeDoMedico, String nomeDoPaciente) {
		this.convenio = convenio;
		this.nomeDoMedico = nomeDoMedico;
		this.nomeDoPaciente = nomeDoPaciente;
	}
	public String toString() {
		String retorno = "";
		retorno += "Convênio: "     + this.convenio     + "\n";
		retorno += "Nome do Médico: "    + this.nomeDoMedico    + "\n";
		retorno += "Nome do Paciente: "     + this.nomeDoPaciente     + "\n";
		retorno += "Exame: "  + this.tipoDoExame  + "\n";
		retorno += "Preparação: "  + preparacao()        + "\n";
		return retorno;
	}
	public abstract String preparacao();
}