package Test;

import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Assert
{
	// Verifica que las colecciones sean iguales
	public static void iguales(int[] esperado, Set<Integer> obtenido)
	{
		assertEquals(esperado.length, obtenido.size());
		
		for(Integer valor: esperado)
			assertTrue(obtenido.contains(valor));
	}
}
