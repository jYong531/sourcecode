package sample.servletcomponents.sampleservletcomponents.Components.Listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ListenerServletContextTest implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("----------Components.Listener.ListenerServletContextTest[contextInitialized]----------");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("----------Components.Listener.ListenerServletContextTest[contextDestroyed]----------");
    }
}
