package com.rmty.framework.bean;

import java.util.*;

public class Param {

    private Map<String,Object> paraMap;

    public Param(Map<String,Object> paraMap) {
        this.paraMap = paraMap;
    }

    public Map<String,Object> getMap(){
        return paraMap;
    }
}
