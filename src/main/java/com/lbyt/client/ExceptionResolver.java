package com.lbyt.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.lbyt.client.error.ErrorBean;

public class ExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception e) {
		ModelAndView mv = new ModelAndView();
		ErrorBean error = new ErrorBean();
		error.setErrorMessage(e.getMessage());
		error.setErrorCode(e.getClass().getName());
		return mv.addObject(error);
	}

}
