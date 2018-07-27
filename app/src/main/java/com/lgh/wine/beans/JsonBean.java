package com.lgh.wine.beans;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * Created by niujingtong on 2018/7/27.
 * 模块：
 */
public class JsonBean implements IPickerViewData {
    private String name;

    @Override
    public String getPickerViewText() {
        return name;
    }

    private List<CityBean> city;

    public static class CityBean {
        private String name;
        private List<String> area;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getArea() {
            return area;
        }

        public void setArea(List<String> area) {
            this.area = area;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityBean> getCity() {
        return city;
    }

    public void setCity(List<CityBean> city) {
        this.city = city;
    }
}
