package com.wrw.newsystem.dao;

import java.util.List;

import com.wrw.newsystem.model.Function;

public interface FunctionDao {

	public abstract List<Function> findAll();

}