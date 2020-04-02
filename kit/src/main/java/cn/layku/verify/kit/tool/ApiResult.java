package cn.layku.verify.kit.tool;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author dongdingzhuo
 * @Title: ApiResult
 * @Package cn.layku.verify.kit.tool;
 * @Description: API响应格式
 * @date 2020/4/2 10:07
 */
public class ApiResult {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long count;

    private ApiResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ApiResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private ApiResult(int code, String msg, Object data, Long count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }

    public static ApiResult successResult() {
        return new ApiResult(0, "success");
    }

    public static ApiResult successResult(String msg) {
        return new ApiResult(0, msg);
    }

    public static ApiResult successResultForData(Object data) {
        return new ApiResult(0, "success", data);
    }

    public static ApiResult successResultForPage(Object data, long count) {
        return new ApiResult(0, "success", data, count);
    }

    public static ApiResult failResult(String message) {
        return failResult(500, message);
    }

    public static ApiResult failResult(int code, String message) {
        return new ApiResult(code, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
