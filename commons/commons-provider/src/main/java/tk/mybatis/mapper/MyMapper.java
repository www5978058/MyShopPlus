package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author wzh
 * @date 2020/1/10 - 14:25
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}

