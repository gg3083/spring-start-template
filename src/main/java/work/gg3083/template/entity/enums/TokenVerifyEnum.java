package work.gg3083.template.entity.enums;

/**
 * @author Gimi
 * @date 2020-10-20 14:05
 */
public enum TokenVerifyEnum {


    PASS(1, "pass","验证通过"),
    EXPIRED(2, "expired", "过期"),
    INVALID(3, "invalid", "无效");


    private Integer key;

    private String value;

    private String desc;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    TokenVerifyEnum(){

    }
    TokenVerifyEnum(Integer key, String value, String desc){
        this.key = key;
        this.value = value;
        this.desc = desc;
    }
}
