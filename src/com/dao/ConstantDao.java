package com.dao;

import java.util.List;

public interface ConstantDao<T> {

	public List<T> find(T T);
	
}
