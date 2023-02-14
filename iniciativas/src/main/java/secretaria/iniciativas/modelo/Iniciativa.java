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

import java.time.*;
import java.util.*;

import javax.persistence.*;

import org.hibernate.validator.constraints.*;
import org.openxava.annotations.*;
import org.openxava.calculators.*;

import lombok.*;
import secretaria.iniciativas.calculadores.*;


@Entity 
@Getter @Setter

@View (members = "Fecha [# anyo; numero;  fechaSesion, corporacion;],  Datos [ tiniciativa; organonombre, acuerdonombre; expediente;]; titulo; observaciones; grupomunicipal;   "
/* Cuando mostraba dos pestañas 
 * 
 * "Asignaciones [ cargos; nombramientos; fechaInicio, fechaFin; diasVigente;materias],"+
			"ÓrganoColegiado [# fechaNombramiento; organismo];"+
			"# observaciones;"*/
)

/*@Tab (rowStyles=@RowStyle(style="aprobado", property="acuerdonombre", value="APROBADO"),properties="idsesion, organonombre, fechaSesion, tiniciativa, titulo, acuerdonombre, expediente, observaciones")*/
@Tab (rowStyles= {@RowStyle(style="row-green", property="acuerdonombre", value="APROBADO"),
@RowStyle(style="row-red", property="acuerdonombre", value="DESESTIMADO"),
@RowStyle(style="row-yellow", property="acuerdonombre", value="NOCONSENSO"),
@RowStyle(style="row-orange", property="acuerdonombre", value="RETIRADO"),
@RowStyle(style="row-blue", property="acuerdonombre", value="NOPRECISAACUERDO")} ,properties="idsesion, corporacion.corporacion, titulo, fechaSesion, organonombre, tiniciativa,  expediente, acuerdonombre, observaciones")

public class Iniciativa extends Identificable {
	
	String idsesion;
	@ReadOnly
	@Depends("anyo, numero")
	@Column (length=30)
	//@Required	
	
	public String getIdsesion(){
		String num;
        num = String.valueOf(numero);
        
        String year;
        year = String.valueOf(anyo);
        
        idsesion = num + "/" + year;
        
        return idsesion;
		
	}
	
	@Column(length=4)
    @DefaultValueCalculator(CurrentYearCalculator.class) // Año actual
    int anyo;
	
	
	@Required
	@Stereotype("TEXTO_GRANDE") // Esto es para un texto grande, se usará un área de texto o equivalente
	@Column(length=500)
	String titulo;
	
	@Column (length=15)
	String expediente;
	
	
	@Column(length=6)
	@ReadOnly
	@DefaultValueCalculator(value=CalculadorSiguienteNumeroParaAnyo.class,
	    properties=@PropertyValue(name="anyo") // Para inyectar el valor de anyo de Iniciativa
	                                           // en el calculador antes de llamar a calculate()
	)
	int numero;
	
	
	
	@ManyToOne( // La referencia se almacena como una relación en la base de datos
			fetch=FetchType.LAZY, // La referencia se carga bajo demanda
	        optional=false) // La referencia puede estar sin valor
	//@NoCreate
	@NoModify
	@DescriptionsList (descriptionProperties = "corporacion", order="${corporacion} desc")
	Corporacion corporacion; // Una referencia Java convencional
	
	@Required
	LocalDate fechaSesion;
	
	@Enumerated(EnumType.STRING) 
	private TipoIniciativa tiniciativa;
	private enum TipoIniciativa{MOCION, DINSTITUCIONAL, PROPOSICION, RUEGOSYPREGUNTAS, COMPARECENCIA, PLEXTRACONCEJAL};
	
	
	@Enumerated(EnumType.STRING) 
	private Organ organonombre;
	private enum Organ{PLENO, JUNTA};
	
	
	@Enumerated(EnumType.STRING) 
	private Acuerdos acuerdonombre;
	private enum Acuerdos{APROBADO, DESESTIMADO, NOCONSENSO, RETIRADO, NOPRECISAACUERDO};
		
	
	@Stereotype("TEXTO_GRANDE") // Esto es para un texto grande, se usará un área de texto o equivalente
	//@Length(max=1000)
	@Column(length=1000)
	String observaciones;
	
	@OneToMany(mappedBy="iniciativa", cascade=CascadeType.ALL)
	//@RemoveSelectedAction("") Para quitar la opción de eliminar del listaddo de elementos
	@ListProperties("grupo.grupo")
	@UniqueElements
	Collection<IniciativaGrupos> grupomunicipal;
	
	@PrePersist @PreUpdate
	
	private void CalculateIdSes(){
		setIdsesion(getIdsesion());
	}
	
	
}
