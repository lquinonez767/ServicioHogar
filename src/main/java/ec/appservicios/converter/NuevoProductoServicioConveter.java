package ec.appservicios.converter;

import java.util.List;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ec.appservicios.modelo.NuevoProductoServicio;


@FacesConverter("NuevoProducto_Converter")
public class NuevoProductoServicioConveter implements Converter{
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent componenet, String value) {
		System.out.println("DATA...... " + value);
		List<NuevoProductoServicio> NuevoProductos = this.getDAO(); 
		

		for (NuevoProductoServicio m : NuevoProductos) {
			System.out.println("ERRROOOORR 2...... " +m.getCodigo());
			if (value.equals("" + m.getCodigo())) {
				System.out.println(m.toString());
				return m;
			}

		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String a = "" + ((NuevoProductoServicio) value).getCodigo();
		System.out.println("ESOOO...." + a);
		return a;
	}

	/**
	 * ActionController que permite recuperar la entidad Cursos desde la
	 * interfaz de donde se implementa el FacesConverter
	 * 
	 */
	public List<NuevoProductoServicio> getDAO() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Application app = ctx.getApplication();
		return (List) app.evaluateExpressionGet(ctx, "#{nuevoproductoservicio_bean.listaProductos}", List.class);

	}

}
