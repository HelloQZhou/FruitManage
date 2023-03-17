package com.Qzhou.fruit.dao;

import com.Qzhou.fruit.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
//    //获取所有的库存列表信息
//    List<Fruit> getFruitList();

    //获取指定页面的库存列表信息 每页显示5条
    List<Fruit> getFruitList(String keyword,Integer pageNo);

    //根据fid获取特定的水果储存信息
    Fruit getFruitByFid(Integer fid);

    //修改指定的库存记录
    void updateFruit(Fruit fruit);

    //根据fid删除指定的库存记录
    void delFruit(Integer fid);

    //添加新库存记录
    void addFruit(Fruit fruit);

    //获取库存总记录条数
    int getFruitCount(String keyword);
}
