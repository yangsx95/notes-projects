package me.feathers.dependencies;

/**
 * @author Feahters
 * @version 1.0
 * @date 2019/4/1
 */
public class ClientBean {

    private TargetBean targetName;

    public void setTargetName(TargetBean targetName) {
        this.targetName = targetName;
    }

    public TargetBean getTargetName() {
        return targetName;
    }
}
