package com.evol.service.impl;

import com.evol.config.SystemConfig;
import com.evol.config.WXPayConfig;
import com.evol.model.request.AbstractPayCustomParams;
import com.evol.model.request.AbstractPayParams;
import com.evol.service.WechatPayService;
import com.evol.util.SignatureUtil;
import com.evol.util.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WechatPayServiceImpl implements WechatPayService {

    @Autowired
    private WXPayConfig wXPayConfig;

    @Override
    public String abstractPayToXml(AbstractPayParams params){
        String sign = SignatureUtil.createSign(params, WXPayConfig.apiKey, SystemConfig.CHARACTER_ENCODING);
        params.setSign(sign);
        return XmlUtil.toSplitXml(params);
    }

    @Override
    public String abstractCustomPayToXml(AbstractPayCustomParams params, Class paramClazz){
        String sign = SignatureUtil.createSign(params, WXPayConfig.apiKey, SystemConfig.CHARACTER_ENCODING);
        params.setSign(sign);
        return XmlUtil.toSplitXmlByClass(params, paramClazz);
    }
}
