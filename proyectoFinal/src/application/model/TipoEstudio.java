package application.model;

public enum TipoEstudio {

	BACHILLER (0), TECNICO (1), PREGRADO(2), POSGRADO(3), MAESTRIA(4), DOCTORADO(5);
	
	private int tipoEstudio;

	private TipoEstudio(int tipoEstudio) {
		this.tipoEstudio = tipoEstudio;
	}

	public int getTipoEstudio() {
		return tipoEstudio;
	}

	
	
	
}
