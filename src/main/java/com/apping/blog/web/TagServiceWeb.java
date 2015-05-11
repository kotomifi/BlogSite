package com.apping.blog.web;

import com.apping.blog.entity.TagView;
import com.apping.blog.mapper.TagMapper;
import org.apache.ibatis.session.SqlSession;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by baisu on 15-4-10.
 */
@Path("/tag")
public class TagServiceWeb {

    @GET
    @Path("/tags")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TagView> getTags() {
        SqlSession sqlSession = BaseServiceWeb.getSessionFactory().openSession();

        TagMapper tagMapper = sqlSession.getMapper(TagMapper.class);

        List<TagView> tags = tagMapper.getTags();
        sqlSession.close();
        return tags;
    }

}
