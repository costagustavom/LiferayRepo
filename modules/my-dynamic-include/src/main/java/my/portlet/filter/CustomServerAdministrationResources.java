package my.portlet.filter;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.PortletFilter;
import javax.portlet.filter.RenderFilter;
import javax.portlet.filter.RenderResponseWrapper;


import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.cluster.ClusterMasterExecutorUtil;
import com.liferay.portal.kernel.util.PortletKeys;

@Component(
    immediate = true,
    property = {
            "javax.portlet.name=" + PortletKeys.SERVER_ADMIN
    },
    service = PortletFilter.class
)
public class CustomServerAdministrationResources implements RenderFilter {

    @Override
    public void init(FilterConfig config) throws PortletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain)
            throws IOException, PortletException {

        RenderResponseWrapper renderResponseWrapper = new BufferedRenderResponseWrapper(response);

        chain.doFilter(request, renderResponseWrapper);

        String text = renderResponseWrapper.toString();

        if (text != null) {
            String interestingText = "</div>\n" + 
            		"\n" + 
            		"			<div class=\"meter-wrapper text-center\">";

            int index = text.lastIndexOf(interestingText);

            if (index >= 0) {
            	boolean isMaster = ClusterMasterExecutorUtil.isMaster();
                String newText1 = text.substring(0, index);
                String newText2 = "<strong>IsMaster: </strong>"+isMaster;
                String newText3 = text.substring(index);
                
                String newText = newText1 + newText2 + newText3;

                response.getWriter().write(newText);
            }
        }
    }

}