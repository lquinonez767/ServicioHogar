package ec.appservicios.converter;

import java.util.List;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ec.appservicios.modelo.*;

@FacesConverter("Proveedor_Converter")
public class ProveedorConveter implements Converter{
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent componenet, String value) {
		System.out.println("DATA...... " + value);
		List<Proveedor> vetes=this.getDAO();

		

		for (Proveedor v : vetes) {
			System.out.println("ERRROOOORR 2...... " + v.getCodigo());
			if (value.equals("" + v.getCodigo())) {
				System.out.println(v.toString());
				return v;
			}

		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String a = "" + ((Proveedor) value).getCodigo();
		System.out.println("ESOOO...." + a);
		return a;
	}

	/**
	 * ActionController que permite recuperar la entidad Cursos desde la
	 * interfaz de donde se implementa el FacesConverter
	 * 
	 */
	public List<Proveedor> getDAO() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Application app = ctx.getApplication();
		return (List) app.evaluateExpressionGet(ctx, "#{proveedor.lisProvActivos}", List.class);

	}

}
