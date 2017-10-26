package br.com.tecsoluction.sol.conf.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

//@Configuration
public class MultPartResolver extends CommonsMultipartResolver{
	
	
	long maxUploadSize = 10000000;
	
	/**
	 * @return the maxUploadSize
	 */
	public long getMaxUploadSize() {
		
		
		return maxUploadSize;
	}

	@Override
	public void setMaxUploadSize(long maxUploadSize) {
		// TODO Auto-generated method stub
		super.setMaxUploadSize(getMaxUploadSize());
	}

}
