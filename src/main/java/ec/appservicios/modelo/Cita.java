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
public class Cita implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	@NotNull
	private String Fecha;
	
	@NotNull
	private String Hora;
	
	@NotNull
	private String Motivo;
	
	@ManyToOne
	@JoinColumn(name="proveedorcliente")
	private ProveedorCliente newProveCliente;
	

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

	public String getHora() {
		return Hora;
	}

	public void setHora(String hora) {
		Hora = hora;
	}

	public String getMotivo() {
		return Motivo;
	}

	public void setMotivo(String motivo) {
		Motivo = motivo;
	}

	public ProveedorCliente getNewProveCliente() {
		return newProveCliente;
	}

	public void setNewProveCliente(ProveedorCliente newProveCliente) {
		this.newProveCliente = newProveCliente;
	}
	
	

}
