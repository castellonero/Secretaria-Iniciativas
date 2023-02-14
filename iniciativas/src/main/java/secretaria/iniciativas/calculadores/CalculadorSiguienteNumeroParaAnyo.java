package secretaria.iniciativas.calculadores;

import javax.persistence.*;

import org.openxava.calculators.*;
import org.openxava.jpa.*;

import lombok.*;
 
public class CalculadorSiguienteNumeroParaAnyo
    implements ICalculator { // Un calculador tiene que implementar ICalculator
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Getter @Setter     
    int anyo; // Este valor se inyectar� antes de calcular
 
    public Object calculate() throws Exception { // Hace el c�lculo
        Query query = XPersistence.getManager() // Una consulta JPA
            .createQuery("select max(i.numero) from Iniciativa i where i.anyo = :anyo"); // La consulta devuelve
                                                              // el n�mero de iniciativa m�ximo del a�o indicado
        query.setParameter("anyo", anyo); // Ponemos el a�o inyectado como par�metro de la consulta
        Integer ultimoNumero = (Integer) query.getSingleResult();
        return ultimoNumero == null ? 1 : ultimoNumero + 1; // Devuelve el �ltimo n�mero
                                            // de iniciatia del a�o + 1 o 1 si no hay �ltimo n�mero
    }
 
}