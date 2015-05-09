package com.deswaef.weakauras.configuration;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class CrawlingFilterConfiguration {
/*
    @Bean
    public FilterRegistrationBean authorizationFilter(){
        FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        filterRegBean.setFilter(authorizationFilter);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("**_escaped_fragment_**");
        filterRegBean.setUrlPatterns(urlPatterns);
        return filterRegBean;
    }


    @Bean
    public Filter crawlFilter() {
        return new CrawlServlet();
    }

    public final class CrawlServlet implements Filter {
        public void doFilter(ServletRequest request, ServletResponse response,
                             FilterChain chain) throws IOException {

            HttpServletRequest httpRequest = (HttpServletRequest) request;
            if(httpRequest.getParameter("_escaped_fragment_")!=null){
                String redirectUrl = httpRequest.getRequestURL().toString()+"#!"+httpRequest.getParameter("_escaped_fragment_");
                redirectUrl = URLDecoder.decode(redirectUrl, "UTF-8");
                System.out.println(redirectUrl);
                try {
//Get content from the web
                    String content = contentBuilderHelper(redirectUrl);
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();
                    out.write(content);
                    out.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            chain.doFilter(request, response);
        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void destroy() {

        }
    }
**/
}
