package ec.appservicios.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Disponible implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	@NotNull
	private String Fecha;
	
	@NotNull
	private String HoraInicio;
	
	@NotNull
	private String HoraFin;
	
	private String nombreProve;
	
	
	@ManyToOne
	@JoinColumn(name="proveedorCliente")
	private Proveedor newProveedor;



	public int getCodigo() {
		return codigo;
	}



	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}



	public String getFecha() {
		return Fecha;
	}



	public void setFecha(String fecha) {
		Fecha = fecha;
	}



	public String getHoraInicio() {
		return HoraInicio;
	}



	public void setHoraInicio(String horaInicio) {
		HoraInicio = horaInicio;
	}



	public String getHoraFin() {
		return HoraFin;
	}



	public void setHoraFin(String horaFin) {
		HoraFin = horaFin;
	}



	public Proveedor getnewProveedor() {
		return newProveedor;
	}



	public void setnewProveedor(Proveedor newProveedor) {
		this.newProveedor = newProveedor;
	}



	public String getnombreProve() {
		return nombreProve;
	}



	public void setnombreProve(String nombreProve) {
		this.nombreProve = nombreProve;
	}
	

	
	
	

}
