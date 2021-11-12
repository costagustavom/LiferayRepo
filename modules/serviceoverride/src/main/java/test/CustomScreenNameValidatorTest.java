//package test;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//import com.liferay.portal.kernel.util.Props;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import serviceoverride.CustomScreenNameValidator;
//
//@RunWith(MockitoJUnitRunner.class)
//public class CustomScreenNameValidatorTest {
//	@Mock
//	private Props _props;
//	
//	
//	@InjectMocks
//	private CustomScreenNameValidator customScreenNameValidator;
//	
//	
//	@Test
//	public void testScreenNameWithoutDomain_ThenValidateFalse() {
//		Mockito.when(_props.get("my.property.key")).thenReturn("@mycompany.com");
//		assertFalse(customScreenNameValidator.validate(0, "asasa"));		
//	}
//
//	
//	@Test
//	public void testScreenNameWithRightDomain_ThenValidateTrue() {
//		Mockito.when(_props.get("my.property.key")).thenReturn("@mycompany.com");
//		assertTrue(customScreenNameValidator.validate(0, "asasa@mycompany.com"));
//	}
//	
//	@Test
//	public void testScreenNameWithWDomain_ThenValidateFalse() {
//		Mockito.when(_props.get("my.property.key")).thenReturn("@mycompany.com");
//		assertFalse(customScreenNameValidator.validate(0, "asasa@gmail.com"));		
//	}
//	
//	
//
//}