package team.scholarship.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import team.scholarship.bean.Announcement;

import java.util.List;

@Mapper
@Repository
public interface AnnouncementMapper {
    Announcement searchById(int id);

    int getReadNumById(int id);

    void setReadNumById(@Param("id") int id, @Param("num") int num);

    List<Announcement> searchByTitle(String info);

    void addAnnouncement(@Param("date") String date, @Param("content") String content, @Param("title") String title);

    List<Announcement> getAll();
}
