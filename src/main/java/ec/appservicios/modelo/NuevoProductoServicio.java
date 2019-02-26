package ec.appservicios.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;


@Entity
public class NuevoProductoServicio implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	@NotNull
	private String descripcion;
	
	@NotNull
	private String tipo;
	
	@NotNull
	private int valor;
	
	@JoinColumn(name="proveedor")
	@ManyToOne(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER)
	private Proveedor proveedor;
	
	
	@Column(name="fotoProducto")
	private String urlFotoProducto;
	
	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUrlFotoProducto() {
		return urlFotoProducto;
	}

	public void setUrlFotoProducto(String urlFotoProducto) {
		this.urlFotoProducto = urlFotoProducto;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	

}
