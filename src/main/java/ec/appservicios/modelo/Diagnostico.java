package ec.appservicios.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
public class Diagnostico implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	@NotNull
	private String fecha;
		
	@NotNull
	private String descripcion;
	
		
	private String proximaCita;
	
	private String motivo;
	
	@ManyToOne
	@JoinColumn(name = "nuevoproductoservicio")
	private NuevoProductoServicio producto;
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getProximaCita() {
		return proximaCita;
	}
	public void setProximaCita(String proximaCita) {
		this.proximaCita = proximaCita;
	}
	
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public NuevoProductoServicio getproducto() {
		return producto;
	}
	public void setproducto(NuevoProductoServicio producto) {
		this.producto = producto;
	}
	
	
	
	

	
	

}
