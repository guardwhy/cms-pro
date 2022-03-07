package com.cms.service.api;

/**
 * @author guardwhy
 * @date 2022/3/6 19:06
 * 获取验证码接口
 */
public interface CommonService {
    /***
     * 生成图片验证码
     */
    void imageCaptcha();

    /***
     * 验证，验证码
     * @param captcha
     * @return
     */
    String verifyImageCaptcha(String captcha);
}
