package com.lsh.handler;

import com.lsh.utils.AESUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AESEncryptHandler extends BaseTypeHandler<Object>  {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        String content = (parameter == null) ? "" : parameter.toString();
        try {
            ps.setString(i, AESUtil.encrypt(content, AESUtil.KEY.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String columnValue = rs.getString(columnName);
        try {
            return AESUtil.decrypt(columnValue,  AESUtil.KEY.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException{
        String columnValue = rs.getString(columnIndex);
        try {
            return AESUtil.decrypt(columnValue,  AESUtil.KEY.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException{
        String columnValue = cs.getString(columnIndex);
        try {
            return AESUtil.decrypt(columnValue,  AESUtil.KEY.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
