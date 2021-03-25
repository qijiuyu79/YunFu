package com.yunfu.help.bean;

import java.io.Serializable;

public class DiseaseBean implements Serializable {

    private String name;
    private boolean isSelect=false;

    public DiseaseBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
