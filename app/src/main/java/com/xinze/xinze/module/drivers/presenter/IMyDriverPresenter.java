package com.xinze.xinze.module.drivers.presenter;

/**
 * Created by feibai on 2018/5/14.
 * desc:
 */

public interface IMyDriverPresenter {
    /**
     * 获取我的司机信息列表
     *
     * @param pageNum    页码
     * @param pageSize   页数
     * @param inviteFlag 查询条件 0拒绝1成功2邀请中
     * @author feibai
     * @time 2018/5/14  21:51
     * @desc
     */
    void myTruckDrivers(int pageNum, int pageSize, String inviteFlag);

    /**
     * 删除我的司机
     *
     * @param itemId id
     * @author feibai
     * @time 2018/5/19  18:26
     * @desc
     */
    void delMyDriver(String itemId);

    /**
     * 邀请添加司机
     *
     * @param phone 手机号
     * @author feibai
     * @time 2018/5/19  18:26
     * @desc
     */
    void inviteDriver(String phone);



}
