package application.model;

public enum MedioPago {

	TARJETA_DEBITO (0), TARJETA_CREDITO(1);
	
	private int medioPago;

	private MedioPago(int medioPago) {
		this.medioPago = medioPago;
	}

	public int getMedioPago() {
		return medioPago;
	}


	
	
}
