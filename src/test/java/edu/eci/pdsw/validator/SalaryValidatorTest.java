package edu.eci.pdsw.validator;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;

import java.util.Optional;

import org.junit.Test;

import edu.eci.pdsw.model.SocialSecurityType;

/**
 * Test class for {@linkplain SalaryValidator} class
 */
public class SalaryValidatorTest {

	/**
	 * The class under test.
	 */
	private SalaryValidator validator = new SalaryValidator();

	/**
	 * {@inheritDoc}}
	 */
	@Test
	public void validateTest() {
		qt()
		.forAll(EmpleadosGenerator.empleados())
		.check(empleado -> {
			Optional<ErrorType> resultado = validator.validate(empleado);
			if (empleado.getSalary() <= 100) {
				return ErrorType.INVALID_SALARY_AFFILIATION == resultado.get();
			} 
			else if (empleado.getSocialSecurityType() == SocialSecurityType.SISBEN && empleado.getSalary() >= 1500) {
				return ErrorType.INVALID_SISBEN_AFFILIATION == resultado.get();
			}
			else if (empleado.getSocialSecurityType() == SocialSecurityType.EPS && empleado.getSalary() >= 10000){
				return ErrorType.INVALID_EPS_AFFILIATION == resultado.get();
			}
			else if(empleado.getSocialSecurityType() == SocialSecurityType.PREPAID && empleado.getSalary() < 10000) {
				return ErrorType.INVALID_PREPAID_AFFILIATION == resultado.get();
			}
			else return true;
		});
	}
}