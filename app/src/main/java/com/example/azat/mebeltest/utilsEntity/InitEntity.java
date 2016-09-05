package com.example.azat.mebeltest.utilsEntity;


public class InitEntity {
    private String uuid;
    private Integer deviceType;
    private String deviceName;

    public InitEntity(String uuid, Integer deviceType, String deviceName) {
        this.uuid = uuid;
        this.deviceType = deviceType;
        this.deviceName = deviceName;
    }

    /**
     *
     * @return
     * The uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     *
     * @param uuid
     * The uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     *
     * @return
     * The deviceType
     */
    public Integer getDeviceType() {
        return deviceType;
    }

    /**
     *
     * @param deviceType
     * The device_type
     */
    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    /**
     *
     * @return
     * The deviceName
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     *
     * @param deviceName
     * The device_name
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
