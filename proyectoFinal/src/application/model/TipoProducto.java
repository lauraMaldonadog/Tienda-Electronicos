package application.model;

public enum TipoProducto {

	TECNOLOGIA_MOVIL(0), TECNOLOGIA_COMPUTACIONAL(1), MUSICA(2), HOGAR(3), EMPRESARIALES(4);
	
	private int tipoProducto;

	private TipoProducto(int tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public int getTipoProducto() {
		return tipoProducto;
	}

	
	
}
