package es.programacinoextrema.bot;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import es.programacinoextrema.Configuracion;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class Bot {
	static public String procesar(String mensaje) {
		//Acción parametro1 parametro2 parametro3
		String[] partes=mensaje.split(" ",2);
		partes[0]=partes[0].toLowerCase();
		if(partes[0].equals("hora")) {
			//Hora zonaHoraria
			
			DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("H:m:s");
			LocalDateTime ahora=LocalDateTime.now();
			return ahora.format(formatoHora);
		}/*else if(partes[0].equals("tiempo")){
			HttpResponse<String> response = Unirest.get("https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/diaria/"+partes[1]+"?api_key="+Configuracion.parametro("KEY_AEMET"))
					  .header("cache-control", "no-cache")
					  .asString();
			return response.getBody();

		}/**/
		else if(partes[0].equals("hola")) {
			return "Que tal";
		}
		return "No te entiendo. Aún soy demasiado joven";
	}
}
