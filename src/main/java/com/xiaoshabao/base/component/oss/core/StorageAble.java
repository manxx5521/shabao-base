package com.xiaoshabao.base.component.oss.core;

import java.io.InputStream;

public interface StorageAble {
	
	String uploadSuffix(byte[] data, String suffix);
	
	String upload(InputStream inputStream, String path);

}
