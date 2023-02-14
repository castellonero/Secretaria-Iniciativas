/* NACHO MART�NEZ S�NCHEZ - 18/10/2022 
 * 
 * Programa desarrollado en Openxava v6.3 y migrado a v7.0 
 * 
 * Gesti�n de Iniciativas y Control de Sesiones para Secretar�a General
 * 
 * contacto: nacho@nachomartinez.net
 * 
 */

package secretaria.iniciativas.modelo;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity 
@Getter @Setter

public class Corporacion extends Identificable {
		
	@Column (length=9, unique=true)
	@Required
	String corporacion;

}