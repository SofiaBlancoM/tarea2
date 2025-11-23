package es.cifpcarlos3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(
        doNotUseGetters = true,
        includeFieldNames = false
)

//Modelo de datos del módulo
public class Module {

    private String name;
    private int hours;
    private String dni;


}
