package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import configuration.properties.DomainConfiguration;
import serviceoverride.CustomScreenNameValidator;

@RunWith(MockitoJUnitRunner.class)
public class CustomScreenNameValidatorTest {
	@Mock
	private volatile DomainConfiguration _domainConfiguration;
	
	@InjectMocks
	private CustomScreenNameValidator customScreenNameValidator;
	
	@Before
	public void init() {
		Mockito.when(_domainConfiguration.mydomain()).thenReturn("@mycompany.com");
	}
	
	@Test
	public void testScreenNameWithoutDomain_ThenValidateFalse() {		
		assertFalse(customScreenNameValidator.validate(0, "asasa"));		
	}

	
	@Test
	public void testScreenNameWithRightDomain_ThenValidateTrue() {
		assertTrue(customScreenNameValidator.validate(0, "asasa@mycompany.com"));
	}
	
	@Test
	public void testScreenNameWithWDomain_ThenValidateFalse() {
		assertFalse(customScreenNameValidator.validate(0, "asasa@gmail.com"));		
	}
	
	

}