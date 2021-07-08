package work.gg3083.template.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author Gimi
 * @date 2021-07-08 11:18
 */
public interface SystemSqlMapper {

    List<HashMap<String, Object>> getFieldByTableName(@Param("tableName") String tableName);

    List<String> listTable();
}
