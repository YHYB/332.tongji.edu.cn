package team.scholarship.serviceImpl;

import team.scholarship.bean.PlayerBean;
import team.scholarship.mapper.PlayerMapper;
import team.scholarship.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Brian.Z
 * @project chocolateFactory
 * @classname UserServiceImpl
 * @description TODO
 * @date 2020/12/16 16:23
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerMapper playerMapper;

    @Override
    public PlayerBean searchPlayer(String name) {
        return playerMapper.getInfo(name);
    }

}
