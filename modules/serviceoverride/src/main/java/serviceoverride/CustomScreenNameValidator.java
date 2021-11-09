package serviceoverride;

import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.security.auth.ScreenNameValidator;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, 
	property = {
		"service.ranking:Integer=100"
	}, 
	service = ScreenNameValidator.class
	)
public class CustomScreenNameValidator implements ScreenNameValidator {
	public static final String POSTFIX = "postfix";
	
	@Override
	public String getAUIValidatorJS() {
		return "function(val) {var pattern = new RegExp('[^A-Za-z0-9" + getJSEscapedSpecialChars()
				+ "@]');if (val.match(pattern)) {return false;}return true;}";
	}
	
	@Override
	public String getDescription(Locale locale) {
		System.out.println(locale);
		return LanguageUtil.format(
			locale,
			"email-domain-invalid",
			new String[] {POSTFIX, getSpecialChars()}, false);
	}

	@Override
	public boolean validate(long companyId, String screenName) {
		String myPropertyValue = _props.get("my.property.key");
		if (screenName.contains("@") && !screenName.contains(myPropertyValue))
			return false;
		if (!screenName.contains("@"))
			return false;
	
		return true;
	}

	protected String getJSEscapedSpecialChars() {
		if (_jsEscapedSpecialChars == null) {
			_jsEscapedSpecialChars = HtmlUtil.escapeJS(getSpecialCharsRegex());
		}

		return _jsEscapedSpecialChars;
	}
	
	protected String getSpecialChars() {
		if (_specialChars == null) {
			String specialChars = PropsUtil.get(
				PropsKeys.USERS_SCREEN_NAME_SPECIAL_CHARACTERS);

			_specialChars = StringUtil.removeChar(specialChars, CharPool.SLASH);
		}

		return _specialChars;
	}

	protected String getSpecialCharsRegex() {
		if (_specialCharsRegex == null) {
			Matcher matcher = _escapeRegexPattern.matcher(getSpecialChars());

			_specialCharsRegex = matcher.replaceAll("\\\\$0");
		}

		return _specialCharsRegex;
	}
	
	private static final Pattern _escapeRegexPattern = Pattern.compile(
			"[-+\\\\\\[\\]]");

	private String _jsEscapedSpecialChars;
	private String _specialChars;
	private String _specialCharsRegex;

	@Reference
	private Props _props;
	
	

}