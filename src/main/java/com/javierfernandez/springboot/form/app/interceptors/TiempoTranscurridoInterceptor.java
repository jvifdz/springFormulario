package com.javierfernandez.springboot.form.app.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@Component
public class TiempoTranscurridoInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("TiempoTranscurridoInterceptor: preHandle() entrando ...");
        long tiempoInicio = System.currentTimeMillis();
        request.setAttribute("tiempoInicio", tiempoInicio);

        //Simulacion de lo que tardaria en cargae

        Random random = new Random();
        Integer demora = random.nextInt(500);
        Thread.sleep(demora);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {



        long tiempoFin = System.currentTimeMillis();
        long tiempoInicio =(Long) request.getAttribute("tiempoInicio");

        long tiempoTranscurrido= tiempoFin-tiempoInicio;

        if (modelAndView!= null){
            modelAndView.addObject("tiempoTranscurrido", tiempoTranscurrido);
        }
        logger.info("TiempoTranscurrido: "+tiempoTranscurrido+" milisegundos");

        logger.info("TiempoTranscurridoInterceptor: postHandle() saliendo ...");
    }
}
