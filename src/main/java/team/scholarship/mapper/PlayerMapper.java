package team.scholarship.mapper;

import team.scholarship.bean.PlayerBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Brian.Z
 * @project chocolateFactory
 * @classname PlayerMapper
 * @description TODO
 * @date 2020/12/16 16:22
 */
@Mapper
@Repository
public interface PlayerMapper {

    PlayerBean getInfo(String name);
}
