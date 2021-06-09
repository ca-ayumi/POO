package ExamesClinicos;

public class Ecografia extends Exame {
    private static final long serialVersionUID = 1L;

	public String preparacao() {
		return "Jejum de pelo menos 6 horas, mas pode beber água, não deve beber café, tomar remédios ou fumar antes do exame, manter a bexiga cheia";
	}
	public Ecografia(String convenio, String nomeDoMedico, String nomeDoPaciente) {
		super(convenio, nomeDoMedico, nomeDoPaciente);
		this.tipoDoExame = "Ecografia";
	}
    
}