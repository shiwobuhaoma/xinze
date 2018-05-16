package com.xinze.xinze.module.invite.presenter;

/**
 * Created by feibai on 2018/5/14.
 * desc:
 */

interface IInvitePresenter {
    /**
     * 获取我的车主邀请信息
     *  @author feibai
     *  @time 2018/5/14  21:51
     *  @desc
     *  @param pageNum 页码
     *  @param pageSize 页数
     *  @param inviteFlag 查询条件 0拒绝1成功2邀请中
     */
    void getMyTruckOwnerInvitation(int pageNum, int pageSize,String inviteFlag);


    /**
     *  响应邀请信息
     *  @author feibai
     *  @time 2018/5/16  17:41
     *  @desc
     * @param itemId 邀请信息的id
     * @param inviteFlag  响应的邀请结果
     * @param inviteResponseType 响应的消息类别
     * @param content 内容
     */
    void responseInvitation(String itemId, String inviteFlag, String inviteResponseType, String content);
}
