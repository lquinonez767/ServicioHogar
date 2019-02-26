package ec.appservicios.converter;

import java.util.List;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ec.appservicios.modelo.*;


@FacesConverter("Usuario_Converter")
public class UsuarioConveter implements Converter{
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent componenet, String value) {
		System.out.println("DATA...... " + value);
		List<Usuario> usuarios = this.getDAO(); 
		

		for (Usuario u : usuarios) {
			System.out.println("ERRROOOORR 2...... " + u.getCodigo());
			if (value.equals("" + u.getCodigo())) {
				System.out.println(u.toString());
				return u;
			}

		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String a = "" + ((Usuario) value).getCodigo();
		System.out.println("OOKK...." + a);
		return a;
	}

	/**
	 * ActionController que permite recuperar la entidad Cursos desde la
	 * interfaz de donde se implementa el FacesConverter
	 * 
	 */
	public List<Usuario> getDAO() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Application app = ctx.getApplication();
		return (List) app.evaluateExpressionGet(ctx, "#{veterinaria.listaUsuarios}", List.class);

	}

}
