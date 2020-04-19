package com.zcl.study.spring.inteceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * spring-demo .
 *
 * @description: .
 * @author: Chenglin Zhu .
 * @date: 20-4-9 .
 */
public class MethodTimerInterceptor implements HandlerInterceptor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodTimerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//定义开始时间
		long start = System.currentTimeMillis();
		//2.将其存到请求域当中
		request.setAttribute("start",start);
		//记录请求日志
		LOGGER.info(request.getRequestURI()+",请求到达");
		//3,返回true，才会找下一个拦截器，如果没有下一个拦截器，则去Controller
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		//1,取出start
		long start = (long) request.getAttribute("start");


		//2,得到end
		long end =System.currentTimeMillis();


		//3,记录耗时
		long spendTime = end-start;
		if (spendTime>2000){
			LOGGER.warn("方法耗时严重，请及时处理，耗时："+spendTime);
		}else {
			LOGGER.info("方法耗时"+spendTime+"毫秒，正常");
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}

}
