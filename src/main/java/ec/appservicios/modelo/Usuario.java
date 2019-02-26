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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.google.gson.Gson;




@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT g FROM Usuario g"),
    @NamedQuery(name = "Usuario.BuscarUsuario", query = "SELECT g FROM Usuario g WHERE g.username = :codUsuario and g.password=:codPassword"),
   })

public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Usuario() {
		
	}
	public Usuario(String grupoAplicacaoJSON) {
		System.out.println("-----> llego a usuario");
        Gson gson = new Gson();
        Usuario grupoAplicacao = gson.fromJson(grupoAplicacaoJSON, Usuario.class);
        this.nombre = grupoAplicacao.getNombre();
       this.correo=grupoAplicacao.getCorreo();
       this.username = grupoAplicacao.getUsername();
       this.password = grupoAplicacao.getPassword();
       this.urlFotoPerfil = grupoAplicacao.getUrlFotoPerfil();
       this.estado = grupoAplicacao.getEstado();
        
    }
	
	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", nombre=" + nombre + ", correo=" + correo + ", username=" + username
				+ ", password=" + password + ", urlFotoPerfil=" + urlFotoPerfil + ", estado=" + estado
				+ ", listaTelefonos=" + listaTelefonos.size() + ", listaValoraciones=" + listaValoraciones.size()
				+ ", listaVeteUsuarios=" + listaProveUsuarios.size()+ "";
	}

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	//private String cedula;
	
	@NotNull
	private String nombre;
	
	@NotNull
	private String correo;
	
	@NotNull
	private String username;
	
	@NotNull
	private String password;
		
	@Column(name="fotoPerfil")
	private String urlFotoPerfil;
		
	
	@NotNull
	private String estado;
	
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name="usuario")
	private List<Telefono> listaTelefonos=new ArrayList<Telefono>();
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name="usuario")
	private List<Valoracion> listaValoraciones=new ArrayList<Valoracion>();
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name="usuario")
	private List<ProveedorUsuario> listaProveUsuarios=new ArrayList<ProveedorUsuario>();
	
	
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

	/*public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}*/

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Telefono> getListaTelefonos() {
		return listaTelefonos;
	}

	public void setListaTelefonos(List<Telefono> listaTelefonos) {
		this.listaTelefonos = listaTelefonos;
	}
	
	@XmlTransient
	public List<Valoracion> getListaValoraciones() {
		return listaValoraciones;
	}

	public void setListaValoraciones(List<Valoracion> listaValoraciones) {
		this.listaValoraciones = listaValoraciones;
	}

	@XmlTransient
	public List<ProveedorUsuario> getListaProveUsuarios() {
		return listaProveUsuarios;
	}

	public void setListaProveUsuarios(List<ProveedorUsuario> listaVeteUsuarios) {
		this.listaProveUsuarios = listaProveUsuarios;
	}

	public String getUrlFotoPerfil() {
		return urlFotoPerfil;
	}

	public void setUrlFotoPerfil(String urlFotoPerfil) {
		this.urlFotoPerfil = urlFotoPerfil;
	}

	
}
