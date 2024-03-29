package com.pallas.study.dispatch.action;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.pallas.sys.dispatch.action.ContextVariablesContainer;

public class FrameWindowAction implements Controller {
	@SuppressWarnings("unchecked")
	@Override
	public ModelAndView handleRequest(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) throws Exception {
		ModelAndView view = null;

		String path = "frameWindow";
		if (path != null && !path.equals("")) {
			path = ContextVariablesContainer.instance()
					.replaceContextVariables(path);
			view = new ModelAndView(path);
			Map<String, String[]> params = httpRequest.getParameterMap();
			Iterator<String> keys = params.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				if (params.get(key) != null) {
					if (params.get(key).length == 1) {
						view.addObject(key, params.get(key)[0]);
					} else {
						view.addObject(key, params.get(key));
					}
				}
			}
		}
		return view;
	}

}
