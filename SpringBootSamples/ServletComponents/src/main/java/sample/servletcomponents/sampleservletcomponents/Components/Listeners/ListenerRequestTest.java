package sample.servletcomponents.sampleservletcomponents.Components.Listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class ListenerRequestTest implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println("----------Components.Listener.ListenerRequestTest[requestDestroyed]----------");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        System.out.println("----------Components.Listener.ListenerRequestTest[requestInitialized]----------");
        ServletRequest hsr = servletRequestEvent.getServletRequest();
        if(hsr instanceof HttpServletRequest)
        {
            System.out.println("Components.Filters.ListenerRequestTest[requestInitialized] 'servletRequestEvent.getServletRequest()' is instanceof HttpServletRequest");
        }
    }
}
