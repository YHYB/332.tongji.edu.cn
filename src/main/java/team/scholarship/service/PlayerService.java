package team.scholarship.service;

import team.scholarship.bean.PlayerBean;
import org.springframework.stereotype.Service;

/**
 * @author Brian.Z
 * @project chocolateFactory
 * @classname PlayerService
 * @description TODO
 * @date 2020/12/16 16:22
 */
@Service
public interface PlayerService {

    PlayerBean searchPlayer(String name);
}
