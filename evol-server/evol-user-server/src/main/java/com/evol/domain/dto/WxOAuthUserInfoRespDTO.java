package com.evol.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class WxOAuthUserInfoRespDTO {

    @JSONField(name = "openid")
    private String openId;

    @JSONField(name = "nickname")
    private String nickname;   //用户昵称

    @JSONField(name = "sex")
    private int sex;        //用户的性别，值为1时是男性，值为2时是女性，值为0时是未知

    @JSONField(name = "province")
    private String province;   //用户个人资料填写的省份

    @JSONField(name = "city")
    private String city;       //普通用户个人资料填写的城市

    @JSONField(name = "country")
    private String country;    //国家，如中国为CN

    @JSONField(name = "headimgurl")
    private String headImgUrl; //用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640
    // 正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。

    @JSONField(name = "privilege")
    private String privilege;  //用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）

    @JSONField(name = "unionid")
    private String unionId;    //只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段

}
