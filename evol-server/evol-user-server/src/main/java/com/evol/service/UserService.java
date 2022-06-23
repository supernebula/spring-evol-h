package com.evol.service;

import com.evol.domain.PageBase;
import com.evol.domain.UpdateUserBalanceResult;
import com.evol.domain.dto.UserAddDto;
import com.evol.domain.dto.UserBalanceDTO;
import com.evol.domain.dto.UserModifyDto;
import com.evol.domain.model.User;
import com.evol.domain.request.UpdateUserBalanceParam;
import com.evol.domain.request.UserQueryRequest;

import java.util.Date;

public interface UserService {
    UserBalanceDTO queryBalance(Integer userId);

    PageBase<User> queryPage(UserQueryRequest userQueryRequest);

    UpdateUserBalanceResult updateUserBalance(UpdateUserBalanceParam updateParam);

    Integer AddUser(UserAddDto userAddDto);

    Integer ModifyUser(UserModifyDto userModifyDto);

    User getUserById(Integer userId);

    Integer deleteUserById(Integer userId);

    /**
     * 保存微信Auth授权信息
     * @param openId
     * @param token
     * @param tokenExpires
     * @param refreshToken
     * @return
     */
    public User saveWechatToken(String openId, String token, Date tokenExpires, String refreshToken, String scope);

}
