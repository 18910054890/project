package com.lyzd.om.web;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lyzd.om.shared.dao.admin.DeptDao;
import com.lyzd.om.shared.dto.admin.DeptDto;
import com.lyzd.om.spring.common.utils.TreeUtil;

/**
 * @author Thinker
 *
 */
@SpringBootTest
public class DeptTest {
    @Autowired
    private DeptDao deptDao;


    @Test
    public void a(){
        List<DeptDto> selectRoleDeptTree = deptDao.selectRoleDeptTree(2);
        System.out.println(selectRoleDeptTree);
        DeptDto deptDto = new DeptDto();
        List<DeptDto> buildAll = deptDao.buildAll(deptDto);
        System.out.println(buildAll);
        List<DeptDto> tree = TreeUtil.deptTree(selectRoleDeptTree, buildAll);
        System.out.println(tree);
    }
}
