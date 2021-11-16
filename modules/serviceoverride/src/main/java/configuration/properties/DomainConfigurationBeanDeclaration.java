package configuration.properties;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;

import org.osgi.service.component.annotations.Component;

@Component(service = ConfigurationBeanDeclaration.class)
public class DomainConfigurationBeanDeclaration implements ConfigurationBeanDeclaration {
	@Override
	public Class<?> getConfigurationBeanClass() {
	    return DomainConfiguration.class;
	}
}
