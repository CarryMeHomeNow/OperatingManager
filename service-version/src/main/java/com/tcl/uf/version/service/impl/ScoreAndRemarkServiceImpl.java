package com.tcl.uf.version.service.impl;

import com.tcl.commondb.version.model.ExternalVersionModel;
import com.tcl.commondb.version.model.VersionFeedbackModel;
import com.tcl.commondb.version.repository.ExternalVersionRepository;
import com.tcl.commondb.version.repository.VersionFeedbackRepository;
import com.tcl.uf.common.base.constant.MessageConstant;
import com.tcl.uf.common.base.dto.AbstractBaseController;
import com.tcl.uf.common.base.ex.JMException;
import com.tcl.uf.version.dto.ScoreAndRemarkDto;
import com.tcl.uf.version.service.ScoreAndRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class ScoreAndRemarkServiceImpl extends AbstractBaseController implements ScoreAndRemarkService {

    @Autowired
    private VersionFeedbackRepository versionFeedbackRepository;

    @Autowired
    private ExternalVersionRepository externalVersionRepository;

    /**
     * app用户评分
     *
     * @param scoreAndRemarkDto
     * @return
     */
    @Transactional
    @Override
    public String addScoreAndRemark(ScoreAndRemarkDto scoreAndRemarkDto, HttpServletRequest httpServletRequest) {
        //应用反馈数据验证与转换
        VersionFeedbackModel versionFeedbackModel = formatFeedBack(scoreAndRemarkDto, httpServletRequest);
        if (versionFeedbackModel == null) {
            throw new JMException("参数不能为空");
        }
        versionFeedbackRepository.saveAndFlush(versionFeedbackModel);
        Integer score = versionFeedbackModel.getScore();
        if (score != null && score > 3) {
            //返回该渠道的评分URL
            String appVer = scoreAndRemarkDto.getAppVer();
            String platformId = scoreAndRemarkDto.getPlatformId();
            if (StringUtils.isEmpty(appVer) || StringUtils.isEmpty(platformId)) {
                return null;
            }
            List<ExternalVersionModel> externalVersionModels = externalVersionRepository.queryByPlatformIdAndAppVerAndUseStatus(platformId, appVer, 1);
            if (!externalVersionModels.isEmpty()) {
                return externalVersionModels.get(0).getScoreUrl();
            }
        }
        return null;
    }

    /**
     * 应用反馈数据验证与转换
     *
     * @param scoreAndRemarkDto
     * @return
     */
    private VersionFeedbackModel formatFeedBack(ScoreAndRemarkDto scoreAndRemarkDto, HttpServletRequest httpServletRequest) {
        if (!StringUtils.isEmpty(scoreAndRemarkDto)) {
            VersionFeedbackModel versionFeedbackModel = new VersionFeedbackModel();
            //获取用户ssoid
            try {
                Long ssoid = getAppUserInfo(httpServletRequest).getAccountId();
                versionFeedbackModel.setSsoid(ssoid);
            } catch (Exception e) {
                throw new JMException(MessageConstant.MSG_NULL_SSOID);
            }
            //用户评分
            if (!StringUtils.isEmpty(scoreAndRemarkDto.getScore())) {
                versionFeedbackModel.setScore(scoreAndRemarkDto.getScore());
            }
            //反馈意见
            if (!StringUtils.isEmpty(scoreAndRemarkDto.getRemark())) {
                versionFeedbackModel.setRemark(scoreAndRemarkDto.getRemark());
            }
            //应用渠道版本
            if (!StringUtils.isEmpty(scoreAndRemarkDto.getAppVer())) {
                versionFeedbackModel.setAppVer(scoreAndRemarkDto.getAppVer());
            }
            //应用内部版本号
            if (!StringUtils.isEmpty(scoreAndRemarkDto.getInternalVer())) {
                versionFeedbackModel.setInternalVer(scoreAndRemarkDto.getInternalVer());
            }
            //应用渠道id
            if (!StringUtils.isEmpty(scoreAndRemarkDto.getPlatformId())) {
                versionFeedbackModel.setPlatformId(scoreAndRemarkDto.getPlatformId());
            }
            return versionFeedbackModel;
        }
        return null;
    }

}
