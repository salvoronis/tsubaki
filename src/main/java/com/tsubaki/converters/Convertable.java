package com.tsubaki.converters;

public interface Convertable<S, D> {
    public D transform(S src);
}
