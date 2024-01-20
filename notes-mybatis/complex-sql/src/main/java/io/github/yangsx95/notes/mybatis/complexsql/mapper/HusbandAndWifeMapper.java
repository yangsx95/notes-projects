package io.github.yangsx95.notes.mybatis.complexsql.mapper;

import io.github.yangsx95.notes.mybatis.complexsql.pojo.one2one2.Wife;
import io.github.yangsx95.notes.mybatis.complexsql.pojo.one2one1.HusbandAndWife;

import java.util.List;

/**
 * HusbandAndWifeMapper
 * 一对一 级联查询： 两种方式
 * <p>
 *
 * @author Feathers
 * @date 2018-05-25 18:14
 */
public interface HusbandAndWifeMapper {

    List<HusbandAndWife> findAllHusbandAndWife();

    List<Wife> findAllHusbandAndWife2();
}
