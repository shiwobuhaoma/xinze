package com.xinze.xinze.module.drivers.presenter;

/**
 * Created by feibai on 2018/5/14.
 * desc:
 */

interface IMyDriverPresenter {
    /**
     *   获取我的司机信息列表
     *  @author feibai
     *  @time 2018/5/14  21:51
     *  @desc
     *  @param pageNum 页码
     *  @param pageSize 页数
     *  @param inviteFlag 查询条件 0拒绝1成功2邀请中
     */
    void myTruckDrivers(int pageNum, int pageSize, String inviteFlag);



}
