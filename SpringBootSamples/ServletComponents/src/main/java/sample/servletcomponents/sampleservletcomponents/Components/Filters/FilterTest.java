package sample.servletcomponents.sampleservletcomponents.Components.Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

@WebFilter(urlPatterns = {"/*"},
initParams = {@WebInitParam(name = "filterConfigNames", value = "filterConfigValues")})
public class FilterTest implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("----------Components.Filters.FilterTest[init]----------");
        Enumeration<String> list = filterConfig.getInitParameterNames();
        while (list.hasMoreElements())
        {
            String values = list.nextElement();
            System.out.println("Components.Filters.FilterTest[init],filterConfigName:" + values + "; filterConfigValue:" + filterConfig.getInitParameter(values));
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("----------Components.Filters.FilterTest[doFilter]----------");
        if(servletRequest instanceof HttpServletRequest)
        {
            System.out.println("Components.Filters.FilterTest[doFilter] servletRequest is instanceof HttpServletRequest");
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("----------Components.Filters.FilterTest[destroy]----------");
    }
}
