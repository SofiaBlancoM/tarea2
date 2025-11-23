package es.cifpcarlos3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString (
        doNotUseGetters = true,
        includeFieldNames = false
)

//Modelo de datos del profesor
public class Teacher {

    private String dni;
    private String name;
    private String surnames;
    private String phone;

}
