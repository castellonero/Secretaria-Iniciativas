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

@Tab(
		properties="iniciativa.idsesion, grupo.grupo"
)

public class IniciativaGrupos extends Identificable {
	
	@ManyToOne( // La referencia se almacena como una relación en la base de datos
			fetch=FetchType.LAZY, // La referencia se carga bajo demanda
	        optional=false) // La referencia puede estar sin valor
	//@NoCreate
	@NoModify
	@DescriptionsList (descriptionProperties = "idsesion") // Así la referencia se visualiza usando un combo
//		@ReferenceView("SoloNombreCargo") // Para que sólo salga el nombre
//		@NoFrame // Para evitar el marco de la referencia y salga integrado
//		@NoSearch // Para no poder buscar nuevos, solo crear (y modificar)
	/* Lo de arriba indentado sobra si no va y se descomenta el descriptionList */	
	Iniciativa iniciativa; // Una referencia Java convencional
	
	// Lo quito par probar lo de insertarlo desde cargo
	@ManyToOne( // La referencia se almacena como una relación en la base de datos
			fetch=FetchType.LAZY, // La referencia se carga bajo demanda
	        optional=false) // La referencia puede estar sin valor
	//@NoCreate
	//@NoModify
	@DescriptionsList (descriptionProperties = "grupo", order="${grupo} asc") // Así la referencia se visualiza usando un combo
	GrupoMunicipal grupo; // Una referencia Java convencional


}