package es.programacinoextrema;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuracion {
	static Properties propiedades=null;
	static public String parametro(String clave) {
		if(propiedades==null) {
			propiedades = new Properties();
		    InputStream fichero;
			try {
				fichero = new FileInputStream("h:\\desarrollo\\discord.properties");
				propiedades.load(fichero);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return propiedades.getProperty(clave);
	}
}
