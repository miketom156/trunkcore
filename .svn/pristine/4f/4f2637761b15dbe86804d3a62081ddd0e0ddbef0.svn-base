package com.job5156.core.bo;
/**
{
        address: "CN|吉林|长春|None|CERNET|1|None",
        content: {
           address: "吉林省长春市",
           address_detail: {
              city: "长春市",
              city_code: 53,
              district: "",
              province: "吉林省",
              street: "",
              street_number: ""
            },
            point: {
              x: "125.31364243",
              y: "43.89833761"
            }
        },
        status: 0
        }
 **/
public class BaiduIpBo {

    private Integer status;
    private String address;
    private Content content;


    public static class Content{
        private String address;
        private Addrs address_detail;
        private Point point;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Addrs getAddress_detail() {
            return address_detail;
        }

        public void setAddress_detail(Addrs address_detail) {
            this.address_detail = address_detail;
        }

        public Point getPoint() {
            return point;
        }

        public void setPoint(Point point) {
            this.point = point;
        }
    }

    /**
     * 地址详细内容
     */
    public static class Addrs{
        private String province;
        private String city;
        private Integer city_code; //百度的城市代码
        private String district;   //区
        private String street;
        private String street_number;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Integer getCity_code() {
            return city_code;
        }

        public void setCity_code(Integer city_code) {
            this.city_code = city_code;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getStreet_number() {
            return street_number;
        }

        public void setStreet_number(String street_number) {
            this.street_number = street_number;
        }
    }

    public static class Point{ //百度经纬度坐标值
        private Double x;
        private Double y;
        public Double getX() {
            return x;
        }

        public void setX(Double x) {
            this.x = x;
        }

        public Double getY() {
            return y;
        }

        public void setY(Double y) {
            this.y = y;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
