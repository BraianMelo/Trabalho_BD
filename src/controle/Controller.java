package controle;

public abstract class Controller {
	
	protected AlertaController alertaController = new AlertaController() {
	};
	
	// Passa a enum CONFIRMADO para Confirmado
	protected String formatarEnum(String enumStr){
		String strFormatada = enumStr.toLowerCase();
		strFormatada = strFormatada.substring(0, 1).toUpperCase() + strFormatada.substring(1);
		return strFormatada;
	}


	
}
