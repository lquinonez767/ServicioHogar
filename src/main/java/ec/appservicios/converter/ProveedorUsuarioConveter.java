package ec.appservicios.converter;

import java.util.List;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ec.appservicios.modelo.*;

@FacesConverter("VeterinariaUsuario_Converter")
public class ProveedorUsuarioConveter implements Converter{
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent componenet, String value) {
		System.out.println("DATA...... " + value);
		List<ProveedorUsuario> veteusuarios=this.getDAO();

		

		for (ProveedorUsuario vu : veteusuarios) {
			System.out.println("ERRROOOORR 2...... " + vu.getCodigo());
			if (value.equals("" + vu.getCodigo())) {
				System.out.println(vu.toString());
				return vu;
			}

		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		//String a = "" + ((VeterinariaUsuario) value).getCodigo();
		String a = "" + ((Usuario) value).getCodigo();
		System.out.println("ESOOO...." + a);
		return a;
	}

	/**
	 * ActionController que permite recuperar la entidad Cursos desde la
	 * interfaz de donde se implementa el FacesConverter
	 * 
	 */
	public List<ProveedorUsuario> getDAO() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Application app = ctx.getApplication();
		return (List) app.evaluateExpressionGet(ctx, "#{veterinariaUsuario.listaveteUser}", List.class);

	}

}
