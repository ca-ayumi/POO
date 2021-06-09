package ExamesClinicos;

public class Sangue extends Exame {
    private static final long serialVersionUID = 1L;

	public String preparacao() {
		return "Manter a dieta habitual nos 3 dias que antecedem o exame, não ingerir bebidas alcóolicas 72 horas antes, fazer jejum de no mínimo 12 horas";
	}
	public Sangue(String convenio, String nomeDoMedico, String nomeDoPaciente) {
		super(convenio, nomeDoMedico, nomeDoPaciente);
		this.tipoDoExame = "Exame de Sangue";
	}
    
}
