package com.javierfernandez.springboot.form.app.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@Component("tiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        //esto hace que no lo haga en el post que lo omita, se sale del interceptor para diferenciar el form del get o post
        if (request.getMethod().equalsIgnoreCase("post")){
            return true;
        }

        if (handler instanceof HandlerMethod){
            HandlerMethod metodo =(HandlerMethod) handler;
            logger.info("es un método del controlador:  "+metodo.getMethod().getName());
        }


        logger.info("TiempoTranscurridoInterceptor: preHandle() entrando ...");
        logger.info("Interceptando : "+handler);
        long tiempoInicio = System.currentTimeMillis();
        request.setAttribute("tiempoInicio", tiempoInicio);

        //Simulacion de lo que tardaria en cargae

        Random random = new Random();
        Integer demora = random.nextInt(100);
        Thread.sleep(demora);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        //Cuando esto ocurre retornamos sin seguir ejecutando
        if (request.getMethod().equalsIgnoreCase("post")){
            return;
        }


        long tiempoFin = System.currentTimeMillis();
        long tiempoInicio =(Long) request.getAttribute("tiempoInicio");

        long tiempoTranscurrido= tiempoFin-tiempoInicio;

        if (handler instanceof HandlerMethod && modelAndView!= null){
            modelAndView.addObject("tiempoTranscurrido", tiempoTranscurrido);
        }
        logger.info("TiempoTranscurrido: "+tiempoTranscurrido+" milisegundos");

        logger.info("TiempoTranscurridoInterceptor: postHandle() saliendo ...");
    }
}
