//package com.ssm.exception;
//
//import org.apache.log4j.Logger;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.ModelAndView;
//
//
//@ControllerAdvice
//public class MyHandlerException {
//
//    private Logger logger = Logger.getLogger(MyHandlerException.class);
//    @ExceptionHandler(Exception.class)
//    public ModelAndView doException(Exception e){
//        logger.error(e.getMessage());
//        e.printStackTrace();
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("error");
//        mav.addObject("msg",e.getMessage());
//        return mav;
//    }
//}
