package sample.servletcomponents.sampleservletcomponents.Components.MessageConverts;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class MessageConvertTest extends WebMvcConfigurationSupport {

    @Value("${components.messageConvertMode}")
    private int messageConvertMode;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        System.out.println("----------Components.MessageConverts.ConvertTest[configureMessageConverters]----------");
        for(HttpMessageConverter converter : converters)
        {
            System.out.println("Components.MessageConverts.ConvertTest[configureMessageConverters] Convert:" + converter.toString());
        }

        if(messageConvertMode == 1) {
            FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.valueOf("text/html;charset=UTF-8"));
            mediaTypes.add(MediaType.valueOf("application/json"));
            converter.setSupportedMediaTypes(mediaTypes);
            converter.setDateFormat("yyyy年MM-dd HH:mm:ss");
            converter.setFeatures(SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero,
                    SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty);

            converters.add(converter);
        }
        else if(messageConvertMode == 2) {
            MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
            ObjectMapper objectMapper = new ObjectMapper();
            SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM月dd");
            objectMapper.setDateFormat(smt);
            mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
            //设置中文编码格式
            List<MediaType> list = new ArrayList<MediaType>();
            list.add(MediaType.APPLICATION_JSON_UTF8);
            mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
            mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
            converters.add(mappingJackson2HttpMessageConverter);
        }
    }
}
