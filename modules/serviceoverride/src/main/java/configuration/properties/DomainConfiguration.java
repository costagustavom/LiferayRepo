package configuration.properties;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
    category = "domain-configuration"
)
@Meta.OCD(
    id = "configuration.properties.DomainConfiguration",
    localization = "content/Language", name = "my-domain-configuration"
)
public interface DomainConfiguration {

    @Meta.AD(
        deflt = "@mycompany.com", description = "autosave-domain-description",
        name = "autosave-domain-name", required = false
    )
    public String mydomain();
    
}