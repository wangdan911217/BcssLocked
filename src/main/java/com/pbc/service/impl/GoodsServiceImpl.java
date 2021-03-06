package com.pbc.service.impl;

import com.pbc.dao.GoodsDao;
import com.pbc.mapper.GoodsMapper;
import com.pbc.po.Goods;
import com.pbc.service.GoodsService;
import com.pbc.domainentity.penetity.GoodsListResponse;
import com.pbc.utils.Tools.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.alibaba.fastjson.JSON.toJSONString;

/**
 * Created by LiuHuiChao on 2016/10/7.
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private RedisDao redisDao;

    @Override
    public List<GoodsListResponse> getAllGoods() {
        List<GoodsListResponse> goodsList = goodsDao.getAllGoodsList();
        return goodsList;
    }

    @Override
    public GoodsListResponse getGoodsById(String id) {
        return goodsDao.getGoodsById(id);
    }

    @Override
    public String get(String id) {
        String goods = redisDao.get("goods:" + id);
        if (goods != null ) return goods;
        goods = toJSONString(goodsDao.getGoodsById(id));
        redisDao.set("goods:" + id, goods);
        return goods;
    }

    @Override
    public int addGoods(Goods goods) {
        return goodsDao.addGoods(goods);
    }

    @Override
    public int updateGoodsByPK(Goods goods) {
        return goodsDao.updateGoodsByPK(goods);
    }

    @Override
    public int deleteGoodsByPK(String id) {
        return goodsDao.deleteGoodsByPK(id);
    }
}
