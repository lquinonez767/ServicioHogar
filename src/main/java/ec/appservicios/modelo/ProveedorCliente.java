package ec.appservicios.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ProveedorCliente implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	private String nombreProve;
	
	private String nombreCliente;
	

	
	@ManyToOne
	@JoinColumn(name="proveedor")
	private Proveedor newProveedor;
	
	@ManyToOne
	@JoinColumn(name="cliente")
	private Cliente cliente;
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Proveedor getNewProveedor() {
		return newProveedor;
	}

	public void setNewProveedor(Proveedor newProveedor) {
		this.newProveedor = newProveedor;
	}

	public String getNombreProve() {
		return nombreProve;
	}

	public void setNombreProve(String nombreProve) {
		this.nombreProve = nombreProve;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	


	
		
}

