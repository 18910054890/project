package com.lyzd.om.web;

import static java.time.ZoneId.of;
import static java.util.TimeZone.getTimeZone;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;


/**
 * @author Thinker
 *
 */
//启动类 
@SpringBootApplication (exclude={org.activiti.spring.boot.SecurityAutoConfiguration.class, SecurityAutoConfiguration.class, })
@ComponentScan(basePackages = {"com.lyzd.om"})
@MapperScan(basePackages = {"com.lyzd.om.shared.dao.admin","com.lyzd.om.shared.dao.log", "com.lyzd.om.workflow.emp.model.repository"})
public class DomApplication {

    public static void main(String[] args) {
        SpringApplication.run(DomApplication.class, args);
    }
    
    @PostConstruct
    void started() {
        TimeZone.setDefault(getTimeZone(of("Asia/Shanghai")));
    }
    

    /**
	 * 配置地址栏不能识别 // 的情况
	 * @return
	 */
	@Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
		StrictHttpFirewall firewall = new StrictHttpFirewall();
		//此处可添加别的规则,目前只设置 允许双 //
		firewall.setAllowUrlEncodedDoubleSlash(true);
		return firewall;
	}

}
