package com.deswaef.weakauras.configuration;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
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

    @Bean
    public Filter crawlFilter() {
        return new CrawlServlet();
    }

    public class CrawlServlet implements Filter{
        public String getFullURL(HttpServletRequest request) {
            StringBuffer requestURL = request.getRequestURL();
            String queryString = request.getQueryString();


            if (queryString == null) {
                return requestURL.toString();
            } else {
                return requestURL.append('?').append(queryString).toString();
            }
        }

        @Override
        public void destroy() {
            // TODO Auto-generated method stub

        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response,
                             FilterChain chain) throws IOException, ServletException {

            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String fullURLQueryString = getFullURL(httpRequest);
            System.out.println(fullURLQueryString+" what wrong");

            if ((fullURLQueryString != null) && (fullURLQueryString.contains("?_escaped_fragment_="))) {
                // remember to unescape any %XX characters
                fullURLQueryString=URLDecoder.decode(fullURLQueryString,"UTF-8");
                // rewrite the URL back to the original #! version
                String ajaxPage=fullURLQueryString.replace("?_escaped_fragment_=", "");


                final WebClient webClient = new WebClient();
                webClient.setCssEnabled(true);
                webClient.setThrowExceptionOnScriptError(false);
                webClient.setThrowExceptionOnFailingStatusCode(false);
                webClient.setJavaScriptEnabled(true);

                HtmlPage page = webClient.getPage(ajaxPage);

                // important!  Give the headless browser enough time to execute JavaScript
                // The exact time to wait may depend on your application.

                webClient.setJavaScriptTimeout(20000);

                // return the snapshot
                //String originalHtml=page.getWebResponse().getContentAsString();
                //System.out.println(originalHtml+" +++++++++");
                System.out.println(page.asXml()+" +++++++++");

                PrintWriter out = response.getWriter();
                response.setContentType("text/html");
                out.println(page.asXml());
                //out.println(originalHtml);
            } else {
                try {
                    // not an _escaped_fragment_ URL, so move up the chain of servlet (filters)
                    chain.doFilter(request, response);
                } catch (ServletException e) {
                    System.err.println("Servlet exception caught: " + e);
                    e.printStackTrace();
                }
            }

        }


        @Override
        public void init(FilterConfig arg0) throws ServletException {
            // TODO Auto-generated method stub

        }


    }

}
