package ec.appservicios.converter;

import java.util.List;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ec.appservicios.converter.*;
import ec.appservicios.modelo.ProveedorCliente;

@FacesConverter("ProveedorCliente_Converter")
public class ProveedorClienteConveter implements Converter{
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent componenet, String value) {
		System.out.println("DATA...... " + value);
		List<ProveedorCliente> proveclientes=this.getDAO();

		

		for (ProveedorCliente pc : proveclientes) {
			System.out.println("ERRROOOORR 2...... " + pc.getCodigo());
			if (value.equals("" + pc.getCodigo())) {
				System.out.println(pc.toString());
				return pc;
			}

		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String a = "" + ((ProveedorCliente) value).getCodigo();
		System.out.println("OK...." + a);
		return a;
	}

	/**
	 * ActionController que permite recuperar la entidad Cursos desde la
	 * interfaz de donde se implementa el FacesConverter
	 * 
	 */
	public List<ProveedorCliente> getDAO() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Application app = ctx.getApplication();
		return (List) app.evaluateExpressionGet(ctx, "#{veterinariaMascota.listaVeteMascota}", List.class);

	}

}
