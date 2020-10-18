package com.tcl.commondb.member.repository;

import com.tcl.commondb.member.model.MemberGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * 自定义复杂原生sql
 */
@Repository
public class MemberNativeQueryRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Object[]> queryGradeDistribution(List<MemberGrade> configList) {
        StringBuilder sb = new StringBuilder();
        sb.append("select ifnull(t.userNum,0) userNum, concat('V',c.grade) gradeName, c.grade from member_grade_configuration c");
        sb.append(" left join ").append(" (");
        sb.append("select count(*) userNum, case ");
        // 构建case when 等级条件
        for (int i = 0; i < configList.size() - 1; i++) {
            sb.append(" when d.growth_value <= ").append(configList.get(i).getMaxGrowth());
            if (configList.get(i).getMinGrowth() != null) {
                sb.append(" and d.growth_value > ").append(configList.get(i).getMinGrowth());
            }
            sb.append(" then ").append(configList.get(i).getGrade());
        }
        sb.append(" else ").append(configList.get(configList.size() - 1).getGrade())
                .append(" end as memberLevel ")
                .append(" from member_rights_and_interests d group by memberLevel ")
                .append(") t on c.grade = t.memberLevel order by c.grade asc");
        Query query = entityManager.createNativeQuery(sb.toString());
        return (List<Object[]>) query.getResultList();
    }
}
