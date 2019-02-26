package ec.appservicios.utilidades;

import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.json.simple.JSONObject;



@ManagedBean
public class Notificaciones{

	
	private String mensaje;
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@PostConstruct
	public void init(){
		
	}
	
	public final static String AUTH_KEY_FCM = "AAAAaEU-_04:APA91bG-h4SZ-l0nvhRpSK5jG_ROlYbGdkPib4Q7sNtcYcWOkPX4unJv4xvs0RN-Hee23TYf5olJQHcBOR3WPXgrQQFar3O1f1sH71XXxHlsUJ2oQp_gwU8HaOgiYlqPs0j3-AzJ-oWeqSrAFzFUdfCxqXdjTIHcZw";
	 public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	// userDeviceIdKey is the device id you will query from your database

	 
	 public void buttonAction(ActionEvent actionEvent) {
	        addMessage(push());
	        
	        
	        
	    }
	 
	 public void addMessage(String summary) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	 
	@SuppressWarnings("unchecked")
	public String push(){

	System.out.println("Entro");	
		
		
	String authKey = AUTH_KEY_FCM;   // You FCM AUTH key
	String FMCurl = API_URL_FCM;  
	String userDeviceIdKey="do09PeTBSkQ:APA91bGaYjDA2uIX6eWrQTmsWM-3Mf0TXyfWk0mCIvTzN_CvagL5cswSfvia4gIVl2tSejOCWeBTsQxg1VkFKHiJCxkoUTZA1gt2J1XqtuI7B-L6-x0on23UIvl-7j2izCds4oULQXN9";

	URL url;
	try {
		url = new URL(FMCurl);
	
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	conn.setUseCaches(false);
	conn.setDoInput(true);
	conn.setDoOutput(true);

	conn.setRequestMethod("POST");
	conn.setRequestProperty("Authorization","key="+authKey);
	conn.setRequestProperty("Content-Type","application/json");

	JSONObject json = new JSONObject();
	json.put("to",userDeviceIdKey.trim());
	JSONObject info = new JSONObject();
	info.put("title", "Ups");   // Notification title
	info.put("body", mensaje); // Notification body
	json.put("notification", info);

	OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	wr.write(json.toString());
	wr.flush();
	conn.getInputStream();
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return "Mensaje enviado";
	}


}
