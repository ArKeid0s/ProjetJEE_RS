package com.proj.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

@WebFilter(filterName = "XssFilter", urlPatterns = {"/*"})
public class XssFilter implements Filter
{

	/*
	 * This class prevents XSS attacks 
	 * */
	
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        chain.doFilter( new XssRequestWrapper( (HttpServletRequest) request), response );
    }

    @Override
    public void destroy() {
    }


    private static class XssRequestWrapper extends HttpServletRequestWrapper {


        private static final Pattern [] XSS_PATTERNS = {
                Pattern.compile( "<.*?>" ),
                Pattern.compile( "&.*?;" ),
                Pattern.compile( "%[0-9a-fA-F]*" )
        };
        

        public XssRequestWrapper(HttpServletRequest servletRequest) {
            super(servletRequest);
        }

        @Override
        public String[] getParameterValues( String parameterName ) {
            
            String [] values = super.getParameterValues(parameterName);

            if (values == null) return null;

            int count = values.length;
            for ( int i = 0; i < count; i++ ) {
                // Replace every characters in the string
                values[i] = removeTags(values[i]);
            }

            return values;
        }

        @Override
        public String getParameter( String parameter ) {
            return removeTags( super.getParameter(parameter) );
        }

        @Override
        public String getHeader( String name ) {
            return removeTags( super.getHeader(name) );
        }

        
        private String removeTags( String value ) {
            if ( value != null ) {
                // Remove the ASCII code 0
                value = value.replaceAll( "\0", "" );

                // Remove the tags
                for ( Pattern pattern : XSS_PATTERNS ) {
                    value = pattern.matcher( value ).replaceAll( "" );
                }
                
                // Check if < and > are not in parity
                value = value.replaceAll( "<", "" );
                value = value.replaceAll( ">", "" );
            }
            return value;
        }
    }
}
