package com.car.auction.sys.standard.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * 
 * 项目名称：SDIC-Inner
 * 类名称：CheckLoginFilter
 * 类描述：验证是否登录过滤器，系统超时跳转登陆页
 * 创建人：刘民
 * 创建时间： 2016-2-25 下午2:35:15
 * 修改人：刘民
 * 修改时间： 2016-2-25 下午2:35:15
 * 修改备注： 
 * @version
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class CheckLoginFilter implements Filter{
	/**  
	 * 用于检测用户是否登陆的过滤器，如果未登录，则重定向到指的登录页面<p>  
	 * 配置参数<p>  
	 * checkSessionKey 需检查的在 Session 中保存的关键字<br/>  
	 * redirectURL 如果用户未登录，则重定向到指定的页面，URL不包括 ContextPath<br/>  
	 * notCheckURLList 不做检查的URL列表，以分号分开，并且 URL 中不包括 ContextPath<br/>  
	 */
	protected FilterConfig filterConfig = null;
	private String redirectURL = null;
	private List notCheckURLList = new ArrayList();
	private String sessionKey = null;
	
	private Logger log = Logger.getLogger(CheckLoginFilter.class);
	
	private static Properties props = new Properties();
	private static URL filePath = Thread.currentThread().getContextClassLoader().getResource("");
	private static String fileName = "allowPage.pp";
	
	@Override
	public void destroy() {
		notCheckURLList.clear();
	}

	@Override
	public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain filterChain) throws IOException, ServletException {
		//使浏览器不缓存页面的过滤器
		((HttpServletResponse) sResponse).setHeader("Cache-Control","no-cache");
		((HttpServletResponse) sResponse).setHeader("Pragma","no-cache");
		((HttpServletResponse) sResponse).setDateHeader ("Expires", -1);
		
		
        HttpServletRequest request = (HttpServletRequest) sRequest;
        HttpServletResponse response = (HttpServletResponse) sResponse;

        HttpSession session = request.getSession();
        if (sessionKey == null) {
    	   filterChain.doFilter(request, response);
    	   return;
        }
        log.info("==================记录页面跳转时session是否已失效：" + (session.getAttribute(sessionKey) == null) + "===============");
        
        if ((!checkRequestURIIntNotFilterList(request)) && session.getAttribute(sessionKey) == null) {
        	response.sendRedirect(request.getContextPath() + redirectURL);
        	return;
        }
        filterChain.doFilter(sRequest, sResponse);
		
	}
    private boolean checkRequestURIIntNotFilterList(HttpServletRequest request) {
        String uri = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());
        return notCheckURLList.contains(uri);
    }
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		try {
			props.load(new FileInputStream(filePath.getPath() + File.separator + fileName));
			
			Collection<Object> values = props.values();
			
			String[] allowPages = values.toArray(new String[0]);
			
			if (allowPages != null && allowPages.length > 0) {
				for (String allowPage : allowPages) {
					notCheckURLList.add(allowPage);
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        redirectURL = "/logout.html";
        sessionKey = "userInfo";
	}

}
