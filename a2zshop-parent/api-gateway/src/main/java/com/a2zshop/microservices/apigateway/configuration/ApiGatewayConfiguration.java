package com.a2zshop.microservices.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    //Customize the routing uri
    @Bean
    public RouteLocator customRouter(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p->p.path("/add-category/**")
                        .or().path("/categories/**")
                        .or().path("/category/**")
                        .or().path("/sub-category/**")
                        .or().path("/products/**")
                        .or().path("/product/**")
                        .or().path("/delete-product/**")
                        .uri("lb://product-info-service"))

//                .route(p->p.path("/currency-conversion/**")
//                        .uri("lb://currency-conversion"))
//                .route(p->p.path("/currency-conversion-feign/**")
//                        .uri("lb://currency-conversion"))
//                                //currency-conversion = name registered with eureka
//                .route(p->p.path("/currency-conversion-new/**")
//                        .filters(f->f.rewritePath(
//                                "/currency-conversion-new/(?<segment>.*)", //(?<segment>.*)=optional
//                                "/currency-conversion-feign/${segment}"         //${segment}=optional
//                                ))
//                        .uri("lb://currency-conversion"))

                .build();
    }
}
