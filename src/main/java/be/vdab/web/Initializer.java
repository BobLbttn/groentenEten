package be.vdab.web;

import javax.servlet.Filter;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import be.vdab.datasource.DataSourceConfig;
import be.vdab.repositories.RepositoriesConfig;
import be.vdab.restclients.RestClientConfig;
import be.vdab.restservices.*;
import be.vdab.services.ServiceConfig;

// enkele imports
public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { DataSourceConfig.class, RepositoriesConfig.class, ServiceConfig.class,  RestClientConfig.class };	
	}
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { ControllersConfig.class, RestControllerConfig.class };
	}
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new CharacterEncodingFilter("UTF-8"), new OpenEntityManagerInViewFilter() };
	}
}
