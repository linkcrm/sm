package com.owen;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.pool.DruidDataSource;


@RestController
@SpringBootApplication
public class MainApplication {
	@Autowired
	private Environment env;
	
	
	
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
        
    }
    
  //destroy-method="close
  	@Bean(destroyMethod =  "close")
  	public DataSource dataSource() {
  		DruidDataSource dataSource = new DruidDataSource();
  		dataSource.setUrl(env.getProperty("spring.datasource.url"));
  		dataSource.setUsername(env.getProperty("spring.datasource.username"));//�û���
  		dataSource.setPassword(env.getProperty("spring.datasource.password"));//����
  		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
  		dataSource.setInitialSize(2);//��ʼ��ʱ�����������ӵĸ���
  		dataSource.setMaxActive(20);//������ӳ�����
  		dataSource.setMinIdle(0);//��С���ӳ�����
  		dataSource.setMaxWait(60000);//��ȡ����ʱ���ȴ�ʱ�䣬��λ���롣
  		dataSource.setValidationQuery("SELECT 1");//������������Ƿ���Ч��sql
  		dataSource.setTestOnBorrow(false);//��������ʱִ��validationQuery��������Ƿ���Ч
  		dataSource.setTestWhileIdle(true);//��������Ϊtrue����Ӱ�����ܣ����ұ�֤��ȫ�ԡ�
  		dataSource.setPoolPreparedStatements(false);//�Ƿ񻺴�preparedStatement��Ҳ����PSCache
  		return dataSource;
  	}
  	
  	
  	
}