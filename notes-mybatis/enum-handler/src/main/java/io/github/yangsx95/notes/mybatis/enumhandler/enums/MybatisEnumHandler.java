package io.github.yangsx95.notes.mybatis.enumhandler.enums;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MybatisEnumHandler
 * mybatis enum映射处理 转换器
 * <p>
 *
 * @author Feathers
 * @date 2018-05-24 14:19
 */
public class MybatisEnumHandler<E extends Enum<?> & IEnum> extends BaseTypeHandler<IEnum> {

    private final Class<E> type;

    public MybatisEnumHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    /**
     * 将Java参数转换为对应的数据库类型
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, IEnum iEnum, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, iEnum.getCode());
    }

    /**
     * 将结果集的数据库类型转换为java类型
     */
    @Override
    public IEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        return rs.wasNull() ? null : codeOf(type, code);
    }

    /**
     * 用于定义通过字段索引获取字段数据时，如何把数据库类型转换为对应的Java类型
     */
    @Override
    public IEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        return rs.wasNull() ? null : codeOf(type, code);
    }

    /**
     * 用定义调用存储过程后，如何把数据库类型转换为对应的Java类型
     */
    @Override
    public IEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return cs.wasNull() ? null : codeOf(type, code);
    }

    /**
     * 根据code获取枚举
     */
    public static <E extends Enum<?> & IEnum> E codeOf(Class<E> enumClass, int code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getCode() == code)
                return e;
        }
        return null;
    }
}
