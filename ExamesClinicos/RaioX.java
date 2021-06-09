package ExamesClinicos;

public class RaioX extends Exame {
    private static final long serialVersionUID = 1L;

	public String preparacao() {
		return "A maioria dos exames de Raio X não precisa de preparação, exceto o Raio X de abdome que requer a ingestão de laxantes e jejum de 12 horas";
	}
	public RaioX(String convenio, String nomeDoMedico, String nomeDoPaciente) {
		super(convenio, nomeDoMedico, nomeDoPaciente);
		this.tipoDoExame = "Raio X";
	}
    
}