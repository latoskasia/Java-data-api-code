//package com.example.data_service;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.FilterConfig;
//import jakarta.servlet.Filter;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class CorsFilter implements Filter {
//
//    @Override
//    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//        throws IOException, ServletException {
//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
//        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//
//        if ("OPTIONS".equalsIgnoreCase(request.getMethod())){
//            response.setStatus(HttpServletResponse.SC_OK);
//            return;
//        }
//
//        chain.doFilter(request,response);
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) {}
//
//    @Override
//    public void destroy(){}
//
//}
