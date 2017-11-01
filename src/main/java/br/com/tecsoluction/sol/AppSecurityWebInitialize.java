package br.com.tecsoluction.sol;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(2)
public class AppSecurityWebInitialize extends AbstractSecurityWebApplicationInitializer {

}
