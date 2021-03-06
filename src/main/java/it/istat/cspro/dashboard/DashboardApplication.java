package it.istat.cspro.dashboard;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DashboardApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        Logger.getLogger(DashboardApplication.class.getName()).info("RUN");
        SpringApplication.run(DashboardApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DashboardApplication.class);
    }    

}
