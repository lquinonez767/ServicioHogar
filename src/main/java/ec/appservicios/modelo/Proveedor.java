package ec.appservicios.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.TransactionManagement;
import javax.faces.context.PartialResponseWriter;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.swing.plaf.basic.BasicScrollPaneUI.VSBChangeListener;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.ap.util.TypeNames.HibernateValidatorTypes;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.JsonAdapter;


@Entity
public class Proveedor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	@NotNull
	private String nombre;
	
	//@NotNull
	private String correo;
	
	//@NotNull
	private String username;
	
	//@NotNull
	private String password;
	
	@NotNull
	private String direccion;
	
	private String sitioWeb;
	
	@Column(name="fotoPerfil")
	private String urlFotoPerfil;
	
	@Column(name="fotoPortada")
	private String urlFotoPortada;
		
	@NotNull
	private String horario;
	
	@NotNull
	private String estado;
	
	private String latitud;
	
	private String longitud;
	
	@JsonIgnore
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, orphanRemoval = true )
	@JoinColumn(name="proveedor")
	private List<Comentario> listaComentarios= new ArrayList<Comentario>();
	
	@JsonIgnore
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name="proveedor")
	private List<Telefono> listaTelefonos= new ArrayList<Telefono>();

	@JsonIgnore
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, orphanRemoval = true )
	@JoinColumn(name="proveedor")
	private List<Diagnostico> listaDiagnosticos=new ArrayList<Diagnostico>();
	
	@JsonIgnore
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, orphanRemoval = true )
	@JoinColumn(name="proveedor")
	private List<NuevoProductoServicio> listaProductos=new ArrayList<NuevoProductoServicio>();
	
	
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getSitioWeb() {
		return sitioWeb;
	}

	public void setSitioWeb(String sitioWeb) {
		this.sitioWeb = sitioWeb;
	}

	public String getUrlFotoPerfil() {
		return urlFotoPerfil;
	}

	public void setUrlFotoPerfil(String urlFotoPerfil) {
		this.urlFotoPerfil = urlFotoPerfil;
	}

	public String getUrlFotoPortada() {
		return urlFotoPortada;
	}

	public void setUrlFotoPortada(String urlFotoPortada) {
		this.urlFotoPortada = urlFotoPortada;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


	public List<Comentario> getListaComentarios() {
		return listaComentarios;
	}

	public void setListaComentarios(List<Comentario> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}

	public List<Telefono> getListaTelefonos() {
		return listaTelefonos;
	}

	public void setListaTelefonos(List<Telefono> listaTelefonos) {
		this.listaTelefonos = listaTelefonos;
	}

	public List<Diagnostico> getListaDiagnosticos() {
		return listaDiagnosticos;
	}

	public void setListaDiagnosticos(List<Diagnostico> listaDiagnosticos) {
		this.listaDiagnosticos = listaDiagnosticos;
	}


	
	public List<NuevoProductoServicio> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<NuevoProductoServicio> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
		

}
