package com.jara.autotestdemo;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * 命令管理pojo
 * 遇到这种嵌套的 Gson反序列化同样适用
 * 如：<p><code>
 * Gson gson = new Gson();<br />
 * HiCmd obj = gson.fromJson(result, HiCmd.class);<br /></code>
 * 如果遇到需要解析成List则：<br /><code>
 * List<HiCmd> list = new ArrayList<HiCmd>; <br />
 * Type type = new TypeToken<ArrayList<HiCmd>>(){}.getType(); <br />
 * hiCmdList = gson.fromJson(jsonArray.toString(), type); </code><br /></p>
 * JsonObject是Gson提供的 可以不解析嵌套的json 返回给我们一个String类型
 * Created by guojiaqi on 2017-7-11.
 */

public class HiCmd {

    @Expose (serialize = false)
    private String cmd_type = "";
    @Expose (serialize = false)
    private String cmd_code = "";
    @Expose (serialize = false)
    private String to_dev_id = "";
    @Expose (serialize = false)
    private String to_app_id = "";
    @Expose
    @SerializedName(value = "app_client_id", alternate = {"to_app_client_id"})
    public String to_app_client_id = "";
    @Expose
    private String cmd_serial = "";
    @Expose (serialize = false)
    private String cmd_createtime;
    @Expose
    private String cmd_status = "";
    @Expose
    private String cmd_result = "";
    @Expose
    private String cmd_errcode = "";
    @Expose
    @SerializedName("cmd_errmsg")
    private String cmd_errdesc = "";
    @Expose (serialize = false)
    private String exec_timeout_secs;
    @Expose
    private JsonObject cmd_params;

    public String getCmd_type() {
        return cmd_type;
    }

    public void setCmd_type(String cmd_type) {
        this.cmd_type = cmd_type;
    }

    public String getCmd_code() {
        return cmd_code;
    }

    public void setCmd_code(String cmd_code) {
        this.cmd_code = cmd_code;
    }

    public String getTo_dev_id() {
        return to_dev_id;
    }

    public void setTo_dev_id(String to_dev_id) {
        this.to_dev_id = to_dev_id;
    }

    public String getTo_app_id() {
        return to_app_id;
    }

    public void setTo_app_id(String to_app_id) {
        this.to_app_id = to_app_id;
    }

    public String getTo_app_client_id() {
        return to_app_client_id;
    }

    public void setTo_app_client_id(String to_app_client_id) {
        this.to_app_client_id = to_app_client_id;
    }

    public String getCmd_serial() {
        return cmd_serial;
    }

    public void setCmd_serial(String cmd_serial) {
        this.cmd_serial = cmd_serial;
    }

    public String getCmd_createtime() {
        return cmd_createtime;
    }

    public void setCmd_createtime(String cmd_createtime) {
        this.cmd_createtime = cmd_createtime;
    }

    public String getExec_timeout_secs() {
        return exec_timeout_secs;
    }

    public void setExec_timeout_secs(String exec_timeout_secs) {
        this.exec_timeout_secs = exec_timeout_secs;
    }

    public String getCmd_status() {
        return cmd_status;
    }

    public void setCmd_status(String cmd_status) {
        this.cmd_status = cmd_status;
    }

    public String getCmd_result() {
        return cmd_result;
    }

    public void setCmd_result(String cmd_result) {
        this.cmd_result = cmd_result;
    }

    public String getCmd_errcode() {
        return cmd_errcode;
    }

    public void setCmd_errcode(String cmd_errcode) {
        this.cmd_errcode = cmd_errcode;
    }

    public String getCmd_errdesc() {
        return cmd_errdesc;
    }

    public void setCmd_errdesc(String cmd_errdesc) {
        this.cmd_errdesc = cmd_errdesc;
    }

    public JsonObject getCmd_params() {
        return cmd_params;
    }

    public void setCmd_params(JsonObject cmd_params) {
        this.cmd_params = cmd_params;
    }

    @Override
    public String toString() {
        return "{" +
                "cmd_type='" + cmd_type + '\'' +
                ", cmd_code='" + cmd_code + '\'' +
                ", to_dev_id='" + to_dev_id + '\'' +
                ", to_app_id='" + to_app_id + '\'' +
                ", to_app_client_id='" + to_app_client_id + '\'' +
                ", cmd_serial='" + cmd_serial + '\'' +
                ", cmd_createtime='" + cmd_createtime + '\'' +
                ", cmd_status='" + cmd_status + '\'' +
                ", cmd_result='" + cmd_result + '\'' +
                ", cmd_errcode='" + cmd_errcode + '\'' +
                ", cmd_errdesc='" + cmd_errdesc + '\'' +
                ", exec_timeout_secs='" + exec_timeout_secs + '\'' +
                ", cmd_params=" + cmd_params +
                '}';
    }
}
