package ec.appservicios.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProveedorUsuario implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	private String nombreProve;
	
	private String nombreUsuario;
	
	@ManyToOne
	@JoinColumn(name="proveedor")
	private Proveedor newProveedor;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombreProve() {
		return nombreProve;
	}

	public void setNombreProve(String nombreProve) {
		this.nombreProve = nombreProve;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Proveedor getNewProveedor() {
		return newProveedor;
	}

	public void setNewProveedor(Proveedor newProveedor) {
		this.newProveedor = newProveedor;
	}

	
		
}
