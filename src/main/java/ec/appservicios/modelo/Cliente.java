package ec.appservicios.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	@NotNull
	private String nombre;
	
	@NotNull
	private String direccion;
	


	private String edad;
	
	private String fechaNacimiento;
	
	@Column(name="fotoCliente")
	private String urlFotoCliente;
	
	@NotNull
	private String estado;
	
	
	@OneToMany (cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name="cliente")
	private List<Diagnostico> listaDiagnosticos= new ArrayList<Diagnostico>();
	
	@OneToMany (cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name="cliente")
	private List<ProveedorCliente> listaProveCliente= new ArrayList<ProveedorCliente>();

	

	@ManyToOne
	@JoinColumn(name="propietario")
	private Usuario usuario;


	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}
	
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public List<Diagnostico> getListaDiagnosticos() {
		return listaDiagnosticos;
	}

	public void setListaDiagnosticos(List<Diagnostico> listaDiagnosticos) {
		this.listaDiagnosticos = listaDiagnosticos;
	}

		 
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


	public List<ProveedorCliente> getListaProveCliente() {
		return listaProveCliente;
	}

	public void setListaVeteMascota(List<ProveedorCliente> listaProveCliente) {
		this.listaProveCliente = listaProveCliente;
	}




	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getUrlFotoCliente() {
		return urlFotoCliente;
	}

	public void setUrlFotoCliente(String urlFotoCliente) {
		this.urlFotoCliente = urlFotoCliente;
	}
	
	

		

}
