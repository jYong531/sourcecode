package sample.servletcomponents.sampleservletcomponents.Components.Intercepters;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
@Configuration
public class IntercepterConfiguration extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new IntercepterComponent()).addPathPatterns("/**");
    }
}
*/

/*与MessageConverts.ConvertTest相同，导致与MessageConverts不能执行，实际情况下，应该两处放在一起，暂时先屏蔽*/
