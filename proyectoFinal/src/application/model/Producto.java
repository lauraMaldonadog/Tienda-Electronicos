package application.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Producto {
	String nombre;
	String codigo;
	Double precio;
	String descripcion;
	Image fotoProducto;
	Integer existencias;
	TipoProducto categoria;
	public Producto(String nombre, String codigo, Double precio, String descripcion, Image fotoProducto,
			 Integer existencias, TipoProducto categoria) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.precio = precio;
		this.descripcion = descripcion;
		this.fotoProducto=fotoProducto;
		this.existencias = existencias;
		this.categoria = categoria;
	}
	public void actualizarDatos(Producto producto) {
		this.nombre=producto.getNombre();
		this.codigo=producto.getCodigo();
		this.precio=producto.getPrecio();
		this.descripcion=producto.getDescripcion();
		this.fotoProducto=producto.getFotoProducto();
		this.existencias=producto.getExistencias();
		this.categoria=producto.getCategoria();
		
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Image getFotoProducto() {
		return fotoProducto;
	}
	public void setFotoProducto(Image fotoProducto) {
		this.fotoProducto = fotoProducto;
	}
	public Integer getExistencias() {
		return existencias;
	}
	public void setExistencias(Integer existencias) {
		this.existencias = existencias;
	}
	public TipoProducto getCategoria() {
		return categoria;
	}
	public void setCategoria(TipoProducto categoria) {
		this.categoria = categoria;
	}
	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", codigo=" + codigo + ", precio=" + precio + ", descripcion="
				+ descripcion + ", fotoProducto=" + fotoProducto +  ", existencias="
				+ existencias + ", categoria=" + categoria + "]";
	}
	public boolean verificar(String nombre2, String codigo2) {
		if(nombre.equals(nombre2) && codigo.equals(codigo2))
			return true;
		return false;
	}
	public boolean validarIdentidad(String codigo2) {
		if(codigo.equals(codigo2))
			return true;
		return false;
	}
	public void reducirExistencias(int cantidad) {
		existencias-=cantidad;
		
	}
	public void sumarExistencias(Integer existencias2) {
		existencias+=existencias2;
		
	}


}
