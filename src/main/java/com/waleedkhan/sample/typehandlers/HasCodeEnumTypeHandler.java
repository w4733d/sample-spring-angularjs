package com.waleedkhan.sample.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.waleedkhan.sample.model.HasCode;

public class HasCodeEnumTypeHandler<E extends Enum<E> & HasCode> extends BaseTypeHandler<E> {
    private final E[] values;

    public HasCodeEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }

        values = type.getEnumConstants();

        if (values == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E parameter, JdbcType jdbcType)
            throws SQLException {
        preparedStatement.setString(i, parameter.getCode());
    }

    @Override
    public E getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String code = resultSet.getString(columnName);

        if (resultSet.wasNull()) {
            return null;
        }

        return getEnumValue(code);
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String code = resultSet.getString(columnIndex);

        if (resultSet.wasNull()) {
            return null;
        }

        return getEnumValue(code);
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String code = callableStatement.getString(columnIndex);

        if (callableStatement.wasNull()) {
            return null;
        }

        return getEnumValue(code);
    }

    private E getEnumValue(String code) {
        for (E value : values) {
            if (code.equals(value.getCode())) {
                return value;
            }
        }

        return null;
    }
}
