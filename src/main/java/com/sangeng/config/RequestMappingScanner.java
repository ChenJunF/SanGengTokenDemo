package com.sangeng.config;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;


@Service
public class RequestMappingScanner {
    //通过反射扫描和解析`@Controller`注解的类，以及其带有`@RequestMapping`注解的方法
    public void scanControllerMappings() {
        // 扫描的包路径
        String basePackage = "com.sangeng.controller";

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false),new TypeAnnotationsScanner())
                .setUrls(ClasspathHelper.forPackage(basePackage));

        Reflections reflections = new Reflections(configurationBuilder);


        // 获取所有带有@Controller注解的类
        Set<Class<?>> controllerClasses = reflections.getTypesAnnotatedWith(RestController.class);

        for (Class<?> controllerClass : controllerClasses) {
            // 获取类上的@RequestMapping注解
            RequestMapping classRequestMapping = controllerClass.getAnnotation(RequestMapping.class);
            String classPath = "";
            if (classRequestMapping != null) {
                classPath = classRequestMapping.value()[0];
            }

            // 获取类中带有@RequestMapping注解的方法
            Method[] methods = controllerClass.getMethods();
        /*    for (Method method : methods) {
//                RequestMapping methodRequestMapping = null;
                String s =null;
                if (!StringUtils.isEmpty(method.getAnnotation(RequestMapping.class))){
                    RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                    s = annotation.value()[0];
                } else if (!StringUtils.isEmpty(method.getAnnotation(GetMapping.class))) {
                    GetMapping annotation = method.getAnnotation(GetMapping.class);
                    s = annotation.value()[0];
                }else if(!StringUtils.isEmpty(method.getAnnotation(PostMapping.class))){
                    PostMapping annotation = method.getAnnotation(PostMapping.class);
                    s = annotation.value()[0];
                } else if (!StringUtils.isEmpty(method.getAnnotation(DeleteMapping.class))){
                    DeleteMapping annotation = method.getAnnotation(DeleteMapping.class);
                    s = annotation.value()[0];
                } else if (!StringUtils.isEmpty(method.getAnnotation(PutMapping.class))){
                    PutMapping annotation = method.getAnnotation(PutMapping.class);
                    s = annotation.value()[0];
                }

                if (s != null) {
                    String fullPath = classPath + s;
                    System.out.println("请求路径：" + fullPath);
                }
            }*/

            for (Method method : methods) {
                String s = Stream.of(
                                method.getAnnotation(RequestMapping.class),
                                method.getAnnotation(GetMapping.class),
                                method.getAnnotation(PostMapping.class),
                                method.getAnnotation(DeleteMapping.class),
                                method.getAnnotation(PutMapping.class))
                        .filter(Objects::nonNull)
                        .findFirst()
                        .map(annotation -> AnnotationUtils.getValue(annotation, "value"))
                        .map(values -> (String[]) values)
                        .map(values -> values[0])
                        .orElse(null);

                if (s != null) {
                    String fullPath = classPath + s;
                    System.out.println("请求路径：" + fullPath);
                }
            }

        }
    }

    public void scanControllerMappings2() {
        // 扫描的包路径
        String basePackage = "com.sangeng.controller";

        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false),new TypeAnnotationsScanner())
                .setUrls(ClasspathHelper.forPackage(basePackage));

        Reflections reflections = new Reflections(configurationBuilder);

        // 获取所有带有@Controller注解的类
        Set<Class<?>> controllerClasses = reflections.getTypesAnnotatedWith(RestController.class);

        for (Class<?> controllerClass : controllerClasses) {
            // 获取类上的@RequestMapping注解
            RequestMapping classRequestMapping = controllerClass.getAnnotation(RequestMapping.class);
            String classPath = "";
            if (classRequestMapping != null) {
                classPath = classRequestMapping.value()[0];
            }

            // 获取类中带有@RequestMapping注解的方法
            Method[] methods = controllerClass.getMethods();
            for (Method method : methods) {
                Annotation annotation = null;
                String s =null;
                if (!Objects.isNull(method.getAnnotation(RequestMapping.class))){
                    annotation = method.getAnnotation(RequestMapping.class);
                } else if (!Objects.isNull(method.getAnnotation(GetMapping.class))) {
                    annotation = method.getAnnotation(GetMapping.class);
                }else if(!Objects.isNull(method.getAnnotation(PostMapping.class))){
                    annotation = method.getAnnotation(PostMapping.class);
                } else if (!Objects.isNull(method.getAnnotation(DeleteMapping.class))){
                    annotation = method.getAnnotation(DeleteMapping.class);
                } else if (!Objects.isNull(method.getAnnotation(PutMapping.class))){
                    annotation = method.getAnnotation(PutMapping.class);
                }

                if (annotation != null) {
                    String[] value = (String[]) AnnotationUtils.getValue(annotation, "value");
                    String s1 = value[0];
                    String fullPath = classPath + s1;
                    System.out.println("请求路径：" + fullPath);
                }
            }
        }
    }

    public void fanshe() throws ClassNotFoundException {

        //这个方式是错误的,看它的返回值时一个Class，这方式只能对指定的类使用，不能对包使用。
        Class<?> aClass = Class.forName("com.sangeng.controller");
        if (aClass.isAnnotationPresent(RestController.class)) {
            Method[] methods = aClass.getMethods();
            getMethod(methods);
        }

    }

    public void getMethod(Method[] methods){
        for (Method method : methods) {
            if (method.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                String s = annotation.value()[0];
                System.out.println(s);
            }
        }
    }
}
