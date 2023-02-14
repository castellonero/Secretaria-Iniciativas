/* NACHO MARTÍNEZ SÁNCHEZ - 18/10/2022 
 * 
 * Programa desarrollado en Openxava v6.3 y migrado a v7.0 
 * 
 * Gestión de Iniciativas y Control de Sesiones para Secretaría General
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