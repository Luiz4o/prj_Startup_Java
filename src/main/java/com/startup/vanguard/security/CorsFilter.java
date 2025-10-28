package com.startup.vanguard.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        String origin = request.getHeader("Origin");

        // Log para verificar se o filtro está sendo executado
        System.out.println("🔵 CORS Filter executado!");
        System.out.println("Method: " + request.getMethod());
        System.out.println("Origin: " + origin);
        System.out.println("Path: " + request.getRequestURI());

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, PATCH");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            System.out.println("✅ OPTIONS detectado - retornando 200 OK");
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        System.out.println("➡️ Continuando cadeia de filtros");
        chain.doFilter(req, res);
    }
}