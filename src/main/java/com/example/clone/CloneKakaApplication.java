package com.example.clone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/*
    @SpringBootApplication
    스프링부트 시작시 자동으로 MVC 관련된 설정을 해준다
    해당 어노테이션은 들어가보면 크게 세가지 어노테이션이 존재하는데
    @SpringBootConfiguration : 스프링 부트 관련 자동 설정
    @EnableAutoConfiguration : 기존 스프링과 관련된 설정을 자동으로 설정해주는 어노테이션 (서블릿과 관련된 부분들 자세한 사항은 공부해봐야함)
    @ComponentScan :
    AutoScan이 되어야 하는 컴포넌트 클래스들 — 대표적으로 @Controller, @Service, @Repository, @Component등-의
    위치가 메인클래스가 위치한 패키지보다 상위 패키지에 있거나, 하위가 아닌 다른 패키지에 있는 경우, 스캔이 되지 않는다.
    물론 이 설정도 임의의 패키지로 스캔이 되도록 따로 지정할 수 있다.

    상단에 componentScan 옵션을 변경하기위에 오버라이드 개념으로 넣었음
    scan시 우선순위로 @configuration 어노테이션 클래스먼저 읽는다.
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.example.*" })
public class CloneKakaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloneKakaApplication.class, args);
    }
}
