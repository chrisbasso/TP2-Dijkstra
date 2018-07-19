package DataModel;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Persistencia {

	public static final String ARCHIVO_DATOS = "src/DataModel/data";

	public static void guardaObjeto(Object obj, String rutaArchivo) {
		
		try {
			FileOutputStream fos = new FileOutputStream(rutaArchivo);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(obj);
			out.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static Object leeObjeto(String rutaArchivo) throws Exception {
		
		Object obj = null;
		try {
			FileInputStream fis = new FileInputStream(rutaArchivo);
			ObjectInputStream in = new ObjectInputStream(fis);
			obj = in.readObject();
			in.close();
		} catch (Exception ex) {
			throw new Exception("Fallo la carga del Objeto");
		}
		return obj;
	}

}
