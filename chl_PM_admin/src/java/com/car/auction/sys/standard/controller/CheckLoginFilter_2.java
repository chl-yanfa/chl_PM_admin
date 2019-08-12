package com.car.auction.sys.standard.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.car.auction.common.Base64;
import com.car.auction.common.CookieUtils;
import com.car.auction.sys.dto.UserInfoDto;
import com.car.auction.sys.standard.service.UserManagementService;

@SuppressWarnings({"unchecked", "rawtypes"})
public class CheckLoginFilter_2 implements Filter{
	/**  
	 * CheckLoginFilter 用于检测用户是否登陆的过滤器，如果未登录，则重定向到指的登录页面<p>  
	 * redirectURL 如果用户未登录，则重定向到指定的页面，URL不包括 ContextPath
	 * notCheckURLList 不做检查的URL列表，以分号分开，并且 URL 中不包括 ContextPath
	 */
	protected FilterConfig filterConfig = null;
	private String redirectURL = null;
	private List notCheckURLList = new ArrayList();
	private String cookieKey = null;
	
	private Logger log = Logger.getLogger(CheckLoginFilter.class);
	
	private static Properties props = new Properties();
	private static URL filePath = Thread.currentThread().getContextClassLoader().getResource("");
	private static String fileName = "allowPage.pp";
	
	private UserManagementService userManagementService;
	
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

        if (cookieKey == null) {
        	filterChain.doFilter(request, response);
        	return;
        }
		//get cookie
        String cookieVal = CookieUtils.getCookieValue(request, cookieKey);
        if ((!checkRequestURIIntNotFilterList(request)) && StringUtils.isBlank(cookieVal)) {
        	response.sendRedirect(request.getContextPath() + redirectURL);
        	return;
        }
        if(StringUtils.isBlank(cookieVal)||checkRequestURIIntNotFilterList(request)) {
        	filterChain.doFilter(sRequest, sResponse);
        	return;
        }
        UserInfoDto userInfoDto =userManagementService.queryUserByCookieVal(cookieVal);
    	log.info("==================记录页面跳转时cookie是否已失效：" + (userInfoDto == null) + "===============");
    	if(userInfoDto == null) {
			response.sendRedirect(request.getContextPath() + redirectURL);
			return;
		}
    	log.info(userInfoDto.toString());
    	//保存信息到cookie中
		Map<String, String> saveCookies = new HashMap<String, String>();
		saveCookies.put("userCode", userInfoDto.getId().toString());
		saveCookies.put("loginID", userInfoDto.getLoginName());
		saveCookies.put("userName", Base64.encodeToString(userInfoDto.getRealName()));
		CookieUtils.addCookie(saveCookies, "/", response);
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
        cookieKey = "v_v-s-ticket";
        
        ServletContext sc = arg0.getServletContext(); 
        XmlWebApplicationContext cxt = (XmlWebApplicationContext)WebApplicationContextUtils.getWebApplicationContext(sc);
        
        if(cxt != null && cxt.getBean("userManagementService") != null && userManagementService == null) {
        	userManagementService = (UserManagementService) cxt.getBean("userManagementService");        	
        }
	}

}
