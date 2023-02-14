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

import org.hibernate.annotations.*;
import org.openxava.annotations.*;

import lombok.*;

@MappedSuperclass	
@Getter @Setter
public class Identificable {
	
	@Id
	@Hidden
	@GeneratedValue(generator ="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	@Column(length = 32)
	String oid;
}
