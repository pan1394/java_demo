package com.pan.learning.async;

@FunctionalInterface
public interface Callback<T> {

	public void execute(T t);
}
