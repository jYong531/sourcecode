package sample.servletcomponents.sampleservletcomponents.Components.Listeners;

import org.apache.catalina.SessionEvent;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Enumeration;

@WebListener
public class ListenerSessionTest implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        String sessionName = "s1";
        System.out.println("----------Components.Listener.ListenerSessionTest[sessionCreated]----------");
        /*此时Session还没有被完全创建好，所以下面的语句将会报空引用*/
       /* String sessionValue = httpSessionEvent.getSession().getAttribute(sessionName).toString();
        System.out.println("Components.Listener.ListenerSessionTest[sessionCreated] SessionName:" + sessionName + "; SessionValue:" + sessionValue);*/

       /*常用的场景是：根据Session统计在线人数，保存在ServletContext里*/
        ServletContext ctx = httpSessionEvent.getSession( ).getServletContext( );
        Integer numSessions = (Integer) ctx.getAttribute("numSessions");

        if (numSessions == null) {
            numSessions = new Integer(1);
        }
        else {
            int count = numSessions.intValue( );
            numSessions = new Integer(count + 1);
        }
        ctx.setAttribute("numSessions", numSessions);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("----------Components.Listener.ListenerSessionTest[sessionDestroyed]----------");
    }
}
