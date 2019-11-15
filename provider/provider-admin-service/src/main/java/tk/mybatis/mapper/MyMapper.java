package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author wzh
 * @date 2019/11/14 - 13:34
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
