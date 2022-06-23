package com.evol.domain;//package com.evol.domain;
//
//import com.evol.contants.enums.ApiResponseEnum;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.Data;
//
//import java.io.Serializable;
//
//@Data
//public class ApiResponse<T> implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * 网关返回码
//     */
//    @JsonProperty("code")
//    private Integer code=0;
//
//    /**
//     * 网关返回码描述
//     */
//    @JsonProperty("msg")
//    private String msg="网关响应成功";
//
//    /**
//     * 业务返回码
//     */
//    @JsonProperty("sub_code")
//    private Integer subCode=0;
//
//    /**
//     * 业务返回码描述
//     */
//    @JsonProperty("sub_msg")
//    private String subMsg="成功";
//
//
//    @JsonProperty("data")
//    private T data;
//
//    public ApiResponse(T data) {
//        this.data = data;
//    }
//
//    public ApiResponse(Integer subCode, String subMsg) {
//        this.subCode = subCode;
//        this.subMsg = subMsg;
//    }
//
//    /**
//     * 失败
//     * @param code
//     * @param msg
//     * @return
//     */
//    public static ApiResponse fail(Integer code, String msg) {
//        ApiResponse response = new ApiResponse();
//        response.setCode(code);
//        response.setMsg(msg);
//        return response;
//    }
//
//    public static ApiResponse fail(ApiResponseEnum responseEnum) {
//        return fail(responseEnum, "");
//    }
//
//    public static ApiResponse fail(ApiResponseEnum responseEnum, String msg) {
//        ApiResponse response = new ApiResponse();
//        response.setCode(responseEnum.getCode());
//        response.setMsg(responseEnum.getDescription() + ":" + msg);
//        return response;
//    }
//
//    public ApiResponse(){}
//
//    public ApiResponse(ApiResponseEnum responseEnum, T data) {
//        this.code = responseEnum.getCode();
//        this.msg = responseEnum.getDescription();
//        this.subCode = 0;
//        this.subMsg = "";
//        this.data = data;
//    }
//
//    public ApiResponse(ApiResponseEnum responseEnum, Integer subCode, String subMsg, T data) {
//        this.code = responseEnum.getCode();
//        this.msg = responseEnum.getDescription();
//        this.subCode = subCode;
//        this.subMsg = subMsg;
//        this.data = data;
//    }
//}
//
