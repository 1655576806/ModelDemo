package com.example.shuiyirenjian.youmu.BaseEnty;

/**
 * Created by Administrator on 2017/9/9 0009.
 */

public class RexResult {
    private boolean  result;
    private String errorNumberString;
    private String errorPassString;
    private String errorNikeString;
    private String errorYouJianString;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getErrorNumberString() {
        return errorNumberString;
    }

    public void setErrorNumberString(String errorNumberString) {
        this.errorNumberString = errorNumberString;
    }

    public String getErrorPassString() {
        return errorPassString;
    }

    public void setErrorPassString(String errorPassString) {
        this.errorPassString = errorPassString;
    }

    public String getErrorNikeString() {
        return errorNikeString;
    }

    public void setErrorNikeString(String errorNikeString) {
        this.errorNikeString = errorNikeString;
    }

    public String getErrorYouJianString() {
        return errorYouJianString;
    }

    public void setErrorYouJianString(String errorYouJianString) {
        this.errorYouJianString = errorYouJianString;
    }
}
