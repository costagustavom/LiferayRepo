package configuration.propertie;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(
    category = "domain-configuration"
)
@Meta.OCD(
    id = "com.liferay.dynamic.data.mapping.form.web.configuration.DomainConfiguration",
    localization = "content/Language", name = "my-domain-configuration"
)
public interface DomainConfiguration {

    @Meta.AD(
        deflt = "@mycompany.com", description = "autosave-domain-description",
        name = "autosave-domain-name", required = false
    )
    public String mydomain();
    
}