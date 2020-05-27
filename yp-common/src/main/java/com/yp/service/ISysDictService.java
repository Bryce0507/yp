package com.yp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yp.entity.SysDict;
import com.yp.entity.SysDictItem;
import com.yp.dto.DictDTO;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author lihaodong
 * @since 2019-05-17
 */
public interface ISysDictService extends IService<SysDict> {

    /**
     * 修改字典
     * @param dictDto
     * @return
     */
    boolean updateDict(DictDTO dictDto);


    /**
     * 根据主键Id删除字典
     * @param id
     * @return
     */
    @Override
    boolean removeById(Serializable id);

    /**
     * 根据字典名称查询字段详情
     * @param dictName
     * @return
     */
    List<SysDictItem> queryDictItemByDictName(String dictName);
}
