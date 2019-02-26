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
public class Valoracion implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	private String puntaje;
	
	@ManyToOne
	@JoinColumn(name="proveedor")
	private Proveedor proveedor;
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(String puntaje) {
		this.puntaje = puntaje;
	}
	public Proveedor getproveedor() {
		return proveedor;
	}
	public void setproveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	


	

}
