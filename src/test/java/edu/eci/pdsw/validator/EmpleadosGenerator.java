package edu.eci.pdsw.validator;

import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;

import static org.quicktheories.generators.SourceDSL.*;
import edu.eci.pdsw.model.Employee;
import edu.eci.pdsw.model.SocialSecurityType;

public class EmpleadosGenerator {

	public static Gen<Employee> empleados(){
		return ids().zip(salario(),social(),(personId,salary,socialSecurityType) -> new Employee(personId,salary,socialSecurityType));	
	}

	public static Gen<Integer> ids(){
		return integers().from(1000).upToAndIncluding(100000);
	}
	
	public static Gen<Long> salario(){
		return longs().from(1).upToAndIncluding(50000);
	}
	
	public static Gen<SocialSecurityType> social(){
		return Generate.enumValues(SocialSecurityType.class);
	}
}