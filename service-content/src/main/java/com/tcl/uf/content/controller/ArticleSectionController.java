package com.tcl.uf.content.controller;

import com.tcl.uf.common.base.dto.ResponseData;
import com.tcl.uf.content.consts.ResponseObjectConstans;
import com.tcl.uf.content.dto.ArticleSectionDto;
import com.tcl.uf.content.service.ArticleSectionService;
import com.tcl.uf.content.utils.RedisUtils;
import com.tcl.uf.content.vo.ArticleSectionPositionSortVo;
import com.tcl.uf.content.vo.ArticleSectionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jodd.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author youyun.xu
 * @ClassName: ArticleSectionController
 * @Description: 文章版块管理
 * @date 2020/7/23 上午09:00
 */
@Api(description = "文章版块管理")
@RestController
@RequestMapping("/article/section")
public class ArticleSectionController {

    private static final Logger _log = LoggerFactory.getLogger(ArticleSectionController.class);

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    private ArticleSectionService articleSectionService;

    @RequestMapping(value = "/back/saveOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "新增或修改版块", notes = "新增或修改版块", httpMethod = "POST")
    public ResponseData<ArticleSectionVo> saveOrUpdate(@RequestBody ArticleSectionDto articleSectionDto){
        ArticleSectionVo articleSectionVo = null;
        try{
            if (StringUtil.isBlank(articleSectionDto.getSectionName()) || StringUtil.isBlank(articleSectionDto.getSubTitle())) {
                return ResponseObjectConstans.TITLE_NULL_NOTICE;
            }
            articleSectionVo = articleSectionService.saveOrUpdate(articleSectionDto);
        }catch (Exception e){
            _log.error("保存文章版块发生异常 请求参数{} 异常信息 {}",articleSectionDto,e);
            return ResponseObjectConstans.SECTION_SAVE_ERROR;
        }
        return new ResponseData<ArticleSectionVo>(articleSectionVo);
    }

    @RequestMapping(value = "/back/list", method = RequestMethod.GET)
    @ApiOperation(value = "查询版块列表", notes = "查询版块列表", httpMethod = "GET")
    public ResponseData<List<ArticleSectionVo>> sectionList(HttpServletRequest request){
        List<ArticleSectionVo> list = null;
        try{
            list = articleSectionService.findBackSectionList();
        }catch (Exception e){
            _log.error("查询版块列表发生异常",e);
        }
        return new ResponseData<List<ArticleSectionVo>>(list);
    }

    @RequestMapping(value = "/back/delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除版块", notes = "删除版块", httpMethod = "GET")
    public ResponseData<Object> delete(HttpServletRequest request, HttpServletResponse response,@RequestParam("id") Long id){
        try{
           articleSectionService.delete(id);
        }catch (Exception e){
            _log.error("删除版块发生异常 请求参数{} 异常信息 {}",id,e);
            return ResponseObjectConstans.SECTION_DELETE_ERROR;
        }
        return new ResponseData<Object>("删除成功");
    }

    @RequestMapping(value = "/back/position/sort", method = RequestMethod.POST)
    @ApiOperation(value = "版块位置排序", notes = "版块位置排序", httpMethod = "POST")
    public ResponseData<Object> positionSort(HttpServletRequest request, @RequestBody List<ArticleSectionPositionSortVo> position){
        try{
            articleSectionService.positionSort(position);
        }catch (Exception e){
            _log.error("删除版块发生异常 请求参数{} 异常信息 {}",position,e);
            return ResponseObjectConstans.POSITION_UPDATE_ERROR;
        }
        return new ResponseData<Object>("更新成功");
    }
}
