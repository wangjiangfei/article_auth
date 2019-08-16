//package com.wangjiangfei.config.system;
//
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import javax.servlet.*;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 跨域处理
// */
//
//@Component("MyFilter")
//public class CORSFilter implements Filter {
//
//	public void init(FilterConfig filterConfig) throws ServletException {
//		// TODO Auto-generated method stub
//
//	}
//
//	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//			throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		HttpServletResponse response = (HttpServletResponse) res;
//	    response.setHeader("Access-Control-Allow-Origin", "*");
//	    response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT");
//	    response.setHeader("Access-Control-Max-Age", "3600");
//	    response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
//	    chain.doFilter(req, res);
//
//	}
//
//	public void destroy() {
//		// TODO Auto-generated method stub
//
//	}
//
//}
