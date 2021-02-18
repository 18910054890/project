package com.lyzd.om.shared.service.log.impl;

import java.lang.reflect.Method;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lyzd.om.shared.dao.log.LogDao;
import com.lyzd.om.shared.log.dto.ErrorLogDto;
import com.lyzd.om.shared.log.dto.LogDto;
import com.lyzd.om.shared.log.dto.LogQuery;
import com.lyzd.om.shared.model.log.MyLog;
import com.lyzd.om.shared.service.log.MyLogService;
import com.lyzd.om.shared.service.log.aop.MyLogA;
import com.lyzd.om.spring.common.dto.Result;
import com.lyzd.om.spring.common.dto.ResultCode;

import cn.hutool.json.JSONObject;

/**
 * @author Thinker
 *
 */
@Service
public class MyLogServiceImpl implements MyLogService {
    @Autowired
    private LogDao logDao;

    @Override
    public Result<LogDto> getFuzzyInfoLogByPage(Integer offectPosition, Integer limit, LogQuery logQuery) {
        Page page = PageHelper.offsetPage(offectPosition,limit);
        List<LogDto> fuzzyLogByPage = logDao.getFuzzyLogByPage(logQuery);
        return Result.ok().count(page.getTotal()).data(fuzzyLogByPage).code(ResultCode.TABLE_SUCCESS);
    }

    @Override
    public Result<ErrorLogDto> getFuzzyErrorLogByPage(Integer offectPosition, Integer limit, LogQuery logQuery) {
        Page page = PageHelper.offsetPage(offectPosition,limit);
        List<ErrorLogDto> fuzzyErrorLogByPage = logDao.getFuzzyErrorLogByPage(logQuery);
        return Result.ok().count(page.getTotal()).data(fuzzyErrorLogByPage).code(ResultCode.TABLE_SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(String userName, String browser, String ip, ProceedingJoinPoint joinPoint, MyLog log) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MyLogA myLog = method.getAnnotation(MyLogA.class);
        // 方法路径
        String methodName = joinPoint.getTarget().getClass().getName()+"."+signature.getName()+"()";
        StringBuilder params = new StringBuilder("{");
        //参数值
        Object[] argValues = joinPoint.getArgs();
        //参数名称
        String[] argNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        if(argValues != null){
            for (int i = 0; i < argValues.length; i++) {
                params.append(" ").append(argNames[i]).append(": ").append(argValues[i]);
            }
        }
        // 描述
        if (log != null) {
            log.setDescription(myLog.value());
        }
        assert log != null;
        log.setIp(ip);
        String loginPath = "login";
        if(loginPath.equals(signature.getName())){
            try {
                assert argValues != null;
                userName = new JSONObject(argValues[0]).get("userName").toString();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        log.setMethod(methodName);
        log.setUserName(userName);
        log.setParams(params.toString() + " }");
        log.setBrowser(browser);
        logDao.save(log);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delAllByError() {
        logDao.delAllByInfo("ERROR");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delAllByInfo() {
        logDao.delAllByInfo("INFO");
    }
}
