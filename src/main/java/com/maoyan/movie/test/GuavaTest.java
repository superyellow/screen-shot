package com.maoyan.movie.test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaTest {
    public static LoadingCache<String, String> cache1 = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String s) throws Exception {
                    return s + "'s value";
                }
            });

    public static void main(String[] args) throws ExecutionException {
        String test1 = cache1.get("h", new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "i";
            }
        });
        String test2 = cache1.get("g");
        System.out.println(test1);
        System.out.println(test2);
    }

}
