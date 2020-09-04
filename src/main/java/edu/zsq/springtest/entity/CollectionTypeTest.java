package edu.zsq.springtest.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 连续：
 * 注入集合类型属性值
 */
public class CollectionTypeTest {

    private String[] course;
    private List<String> list;
    private Map<String, String> map;
    private Set<String> set;

    public String[] getCourse() {
        return course;
    }

    public void setCourse(String[] course) {
        this.course = course;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    @Override
    public String toString() {
        return
                "course=" + Arrays.toString(course) +
                        ", list=" + list +
                        ", map=" + map +
                        ", set=" + set
                ;
    }
}
