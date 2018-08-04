package com.lgh.wine.beans;

import java.util.List;

/**
 * Created by niujingtong on 2018/8/4.
 * 模块：
 */
public class TrackerBean {

    /**
     * meta : {"code":200,"type":"Success","message":"Success"}
     * data : {"items":[{"id":"f1e0645c3288bae10b8ab6ad89ca187b","tracking_number":"LS912989618CN","carrier_code":"china-ems","order_create_time":"2017/8/27 16:51","status":"transit","original_country":"China","destination_country":"United States","itemTimeLength":11,"stayTimeLength":4,"service_code":null,"origin_info":{"ItemReceived":"2018-04-07 16:45:00","ItemDispatched":null,"DepartfromAirport":null,"ArrivalfromAbroad":"2018-04-14 17:07:00","CustomsClearance":null,"DestinationArrived":null,"weblink":"http://www.ems.com.cn/","phone":"11183","carrier_code":"china-ems","trackinfo":[{"Date":"2018-04-14 17:07:00","StatusDescription":"到达处理中心,来自中国 郑州","Details":"美国","checkpoint_status":"transit"},{"Date":"2018-04-07 21:18:37","StatusDescription":"离开郑州市 发往旧金山","Details":"郑州市","checkpoint_status":"transit"},{"Date":"2018-04-07 16:45:00","StatusDescription":"中国邮政速递物流股份有限公司河南省国际速递分公司五已收件（揽投员姓名：,联系电话:）","Details":"郑州市","checkpoint_status":"transit"}]},"destination_info":{"ItemReceived":"2018-04-07 16:45","ItemDispatched":null,"DepartfromAirport":null,"ArrivalfromAbroad":"2018-04-16 20:01","CustomsClearance":null,"DestinationArrived":null,"weblink":"http://www.usps.com/","phone":"1-800-275-8777","carrier_code":"usps","trackinfo":[{"Date":"2018-04-17 22:30","StatusDescription":"Arrived at USPS Regional Destination Facility","Details":"DES MOINES IA NETWORK DISTRIBUTION CENTER","checkpoint_status":"transit"},{"Date":"2018-04-17 00:00","StatusDescription":"In Transit to Next Facility","Details":"","checkpoint_status":"transit"},{"Date":"2018-04-16 20:01","StatusDescription":"Arrived at USPS Regional Facility","Details":"SAN JOSE CA DISTRIBUTION CENTER","checkpoint_status":"transit"},{"Date":"2018-04-14 17:07","StatusDescription":"Processed Through Facility","Details":"ISC SAN FRANCISCO (USPS)","checkpoint_status":"transit"},{"Date":"2018-04-07 17:00","StatusDescription":"Processed Through Facility","Details":"ZHENGZHOU EMS,CHINA","checkpoint_status":"transit"},{"Date":"2018-04-07 16:45","StatusDescription":"Acceptance","Details":"CHINA","checkpoint_status":"transit"},{"Date":"2018-04-07 16:45","StatusDescription":"Origin Post is Preparing Shipment","Details":"","checkpoint_status":"transit"}]},"lastEvent":"Arrived at USPS Regional Destination Facility,DES MOINES IA NETWORK DISTRIBUTION CENTER,2018-04-17 22:30","lastUpdateTime":"2018-04-17 22:30"}]}
     */

    private MetaBean meta;
    private DataBean data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class MetaBean {
        /**
         * code : 200
         * type : Success
         * message : Success
         */

        private int code;
        private String type;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class DataBean {
        /**
         * id : f1e0645c3288bae10b8ab6ad89ca187b
         * tracking_number : LS912989618CN
         * carrier_code : china-ems
         * order_create_time : 2017/8/27 16:51
         * status : transit
         * original_country : China
         * destination_country : United States
         * itemTimeLength : 11
         * stayTimeLength : 4
         * service_code : null
         * origin_info : {"ItemReceived":"2018-04-07 16:45:00","ItemDispatched":null,"DepartfromAirport":null,"ArrivalfromAbroad":"2018-04-14 17:07:00","CustomsClearance":null,"DestinationArrived":null,"weblink":"http://www.ems.com.cn/","phone":"11183","carrier_code":"china-ems","trackinfo":[{"Date":"2018-04-14 17:07:00","StatusDescription":"到达处理中心,来自中国 郑州","Details":"美国","checkpoint_status":"transit"},{"Date":"2018-04-07 21:18:37","StatusDescription":"离开郑州市 发往旧金山","Details":"郑州市","checkpoint_status":"transit"},{"Date":"2018-04-07 16:45:00","StatusDescription":"中国邮政速递物流股份有限公司河南省国际速递分公司五已收件（揽投员姓名：,联系电话:）","Details":"郑州市","checkpoint_status":"transit"}]}
         * destination_info : {"ItemReceived":"2018-04-07 16:45","ItemDispatched":null,"DepartfromAirport":null,"ArrivalfromAbroad":"2018-04-16 20:01","CustomsClearance":null,"DestinationArrived":null,"weblink":"http://www.usps.com/","phone":"1-800-275-8777","carrier_code":"usps","trackinfo":[{"Date":"2018-04-17 22:30","StatusDescription":"Arrived at USPS Regional Destination Facility","Details":"DES MOINES IA NETWORK DISTRIBUTION CENTER","checkpoint_status":"transit"},{"Date":"2018-04-17 00:00","StatusDescription":"In Transit to Next Facility","Details":"","checkpoint_status":"transit"},{"Date":"2018-04-16 20:01","StatusDescription":"Arrived at USPS Regional Facility","Details":"SAN JOSE CA DISTRIBUTION CENTER","checkpoint_status":"transit"},{"Date":"2018-04-14 17:07","StatusDescription":"Processed Through Facility","Details":"ISC SAN FRANCISCO (USPS)","checkpoint_status":"transit"},{"Date":"2018-04-07 17:00","StatusDescription":"Processed Through Facility","Details":"ZHENGZHOU EMS,CHINA","checkpoint_status":"transit"},{"Date":"2018-04-07 16:45","StatusDescription":"Acceptance","Details":"CHINA","checkpoint_status":"transit"},{"Date":"2018-04-07 16:45","StatusDescription":"Origin Post is Preparing Shipment","Details":"","checkpoint_status":"transit"}]}
         * lastEvent : Arrived at USPS Regional Destination Facility,DES MOINES IA NETWORK DISTRIBUTION CENTER,2018-04-17 22:30
         * lastUpdateTime : 2018-04-17 22:30
         */

        private String id;
        private String tracking_number;
        private String carrier_code;
        private String order_create_time;
        private String status;
        private String original_country;
        private String destination_country;
        private int itemTimeLength;
        private int stayTimeLength;
        private String service_code;
        private OriginInfoBean origin_info;
        private DestinationInfoBean destination_info;
        private String lastEvent;
        private String lastUpdateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTracking_number() {
            return tracking_number;
        }

        public void setTracking_number(String tracking_number) {
            this.tracking_number = tracking_number;
        }

        public String getCarrier_code() {
            return carrier_code;
        }

        public void setCarrier_code(String carrier_code) {
            this.carrier_code = carrier_code;
        }

        public String getOrder_create_time() {
            return order_create_time;
        }

        public void setOrder_create_time(String order_create_time) {
            this.order_create_time = order_create_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOriginal_country() {
            return original_country;
        }

        public void setOriginal_country(String original_country) {
            this.original_country = original_country;
        }

        public String getDestination_country() {
            return destination_country;
        }

        public void setDestination_country(String destination_country) {
            this.destination_country = destination_country;
        }

        public int getItemTimeLength() {
            return itemTimeLength;
        }

        public void setItemTimeLength(int itemTimeLength) {
            this.itemTimeLength = itemTimeLength;
        }

        public int getStayTimeLength() {
            return stayTimeLength;
        }

        public void setStayTimeLength(int stayTimeLength) {
            this.stayTimeLength = stayTimeLength;
        }

        public String getService_code() {
            return service_code;
        }

        public void setService_code(String service_code) {
            this.service_code = service_code;
        }

        public OriginInfoBean getOrigin_info() {
            return origin_info;
        }

        public void setOrigin_info(OriginInfoBean origin_info) {
            this.origin_info = origin_info;
        }

        public DestinationInfoBean getDestination_info() {
            return destination_info;
        }

        public void setDestination_info(DestinationInfoBean destination_info) {
            this.destination_info = destination_info;
        }

        public String getLastEvent() {
            return lastEvent;
        }

        public void setLastEvent(String lastEvent) {
            this.lastEvent = lastEvent;
        }

        public String getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(String lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }

        public static class OriginInfoBean {
            /**
             * ItemReceived : 2018-04-07 16:45:00
             * ItemDispatched : null
             * DepartfromAirport : null
             * ArrivalfromAbroad : 2018-04-14 17:07:00
             * CustomsClearance : null
             * DestinationArrived : null
             * weblink : http://www.ems.com.cn/
             * phone : 11183
             * carrier_code : china-ems
             * trackinfo : [{"Date":"2018-04-14 17:07:00","StatusDescription":"到达处理中心,来自中国 郑州","Details":"美国","checkpoint_status":"transit"},{"Date":"2018-04-07 21:18:37","StatusDescription":"离开郑州市 发往旧金山","Details":"郑州市","checkpoint_status":"transit"},{"Date":"2018-04-07 16:45:00","StatusDescription":"中国邮政速递物流股份有限公司河南省国际速递分公司五已收件（揽投员姓名：,联系电话:）","Details":"郑州市","checkpoint_status":"transit"}]
             */

            private String ItemReceived;
            private String ItemDispatched;
            private String DepartfromAirport;
            private String ArrivalfromAbroad;
            private String CustomsClearance;
            private String DestinationArrived;
            private String weblink;
            private String phone;
            private String carrier_code;
            private List<TrackinfoBean> trackinfo;

            public String getItemReceived() {
                return ItemReceived;
            }

            public void setItemReceived(String ItemReceived) {
                this.ItemReceived = ItemReceived;
            }

            public String getItemDispatched() {
                return ItemDispatched;
            }

            public void setItemDispatched(String ItemDispatched) {
                this.ItemDispatched = ItemDispatched;
            }

            public String getDepartfromAirport() {
                return DepartfromAirport;
            }

            public void setDepartfromAirport(String DepartfromAirport) {
                this.DepartfromAirport = DepartfromAirport;
            }

            public String getArrivalfromAbroad() {
                return ArrivalfromAbroad;
            }

            public void setArrivalfromAbroad(String ArrivalfromAbroad) {
                this.ArrivalfromAbroad = ArrivalfromAbroad;
            }

            public String getCustomsClearance() {
                return CustomsClearance;
            }

            public void setCustomsClearance(String CustomsClearance) {
                this.CustomsClearance = CustomsClearance;
            }

            public String getDestinationArrived() {
                return DestinationArrived;
            }

            public void setDestinationArrived(String DestinationArrived) {
                this.DestinationArrived = DestinationArrived;
            }

            public String getWeblink() {
                return weblink;
            }

            public void setWeblink(String weblink) {
                this.weblink = weblink;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getCarrier_code() {
                return carrier_code;
            }

            public void setCarrier_code(String carrier_code) {
                this.carrier_code = carrier_code;
            }

            public List<TrackinfoBean> getTrackinfo() {
                return trackinfo;
            }

            public void setTrackinfo(List<TrackinfoBean> trackinfo) {
                this.trackinfo = trackinfo;
            }

        }

        public static class DestinationInfoBean {
            /**
             * ItemReceived : 2018-04-07 16:45
             * ItemDispatched : null
             * DepartfromAirport : null
             * ArrivalfromAbroad : 2018-04-16 20:01
             * CustomsClearance : null
             * DestinationArrived : null
             * weblink : http://www.usps.com/
             * phone : 1-800-275-8777
             * carrier_code : usps
             * trackinfo : [{"Date":"2018-04-17 22:30","StatusDescription":"Arrived at USPS Regional Destination Facility","Details":"DES MOINES IA NETWORK DISTRIBUTION CENTER","checkpoint_status":"transit"},{"Date":"2018-04-17 00:00","StatusDescription":"In Transit to Next Facility","Details":"","checkpoint_status":"transit"},{"Date":"2018-04-16 20:01","StatusDescription":"Arrived at USPS Regional Facility","Details":"SAN JOSE CA DISTRIBUTION CENTER","checkpoint_status":"transit"},{"Date":"2018-04-14 17:07","StatusDescription":"Processed Through Facility","Details":"ISC SAN FRANCISCO (USPS)","checkpoint_status":"transit"},{"Date":"2018-04-07 17:00","StatusDescription":"Processed Through Facility","Details":"ZHENGZHOU EMS,CHINA","checkpoint_status":"transit"},{"Date":"2018-04-07 16:45","StatusDescription":"Acceptance","Details":"CHINA","checkpoint_status":"transit"},{"Date":"2018-04-07 16:45","StatusDescription":"Origin Post is Preparing Shipment","Details":"","checkpoint_status":"transit"}]
             */

            private String ItemReceived;
            private String ItemDispatched;
            private String DepartfromAirport;
            private String ArrivalfromAbroad;
            private String CustomsClearance;
            private String DestinationArrived;
            private String weblink;
            private String phone;
            private String carrier_code;
            private List<TrackinfoBeanX> trackinfo;

            public String getItemReceived() {
                return ItemReceived;
            }

            public void setItemReceived(String ItemReceived) {
                this.ItemReceived = ItemReceived;
            }

            public String getItemDispatched() {
                return ItemDispatched;
            }

            public void setItemDispatched(String ItemDispatched) {
                this.ItemDispatched = ItemDispatched;
            }

            public String getDepartfromAirport() {
                return DepartfromAirport;
            }

            public void setDepartfromAirport(String DepartfromAirport) {
                this.DepartfromAirport = DepartfromAirport;
            }

            public String getArrivalfromAbroad() {
                return ArrivalfromAbroad;
            }

            public void setArrivalfromAbroad(String ArrivalfromAbroad) {
                this.ArrivalfromAbroad = ArrivalfromAbroad;
            }

            public String getCustomsClearance() {
                return CustomsClearance;
            }

            public void setCustomsClearance(String CustomsClearance) {
                this.CustomsClearance = CustomsClearance;
            }

            public String getDestinationArrived() {
                return DestinationArrived;
            }

            public void setDestinationArrived(String DestinationArrived) {
                this.DestinationArrived = DestinationArrived;
            }

            public String getWeblink() {
                return weblink;
            }

            public void setWeblink(String weblink) {
                this.weblink = weblink;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getCarrier_code() {
                return carrier_code;
            }

            public void setCarrier_code(String carrier_code) {
                this.carrier_code = carrier_code;
            }

            public List<TrackinfoBeanX> getTrackinfo() {
                return trackinfo;
            }

            public void setTrackinfo(List<TrackinfoBeanX> trackinfo) {
                this.trackinfo = trackinfo;
            }

            public static class TrackinfoBeanX {
                /**
                 * Date : 2018-04-17 22:30
                 * StatusDescription : Arrived at USPS Regional Destination Facility
                 * Details : DES MOINES IA NETWORK DISTRIBUTION CENTER
                 * checkpoint_status : transit
                 */

                private String Date;
                private String StatusDescription;
                private String Details;
                private String checkpoint_status;

                public String getDate() {
                    return Date;
                }

                public void setDate(String Date) {
                    this.Date = Date;
                }

                public String getStatusDescription() {
                    return StatusDescription;
                }

                public void setStatusDescription(String StatusDescription) {
                    this.StatusDescription = StatusDescription;
                }

                public String getDetails() {
                    return Details;
                }

                public void setDetails(String Details) {
                    this.Details = Details;
                }

                public String getCheckpoint_status() {
                    return checkpoint_status;
                }

                public void setCheckpoint_status(String checkpoint_status) {
                    this.checkpoint_status = checkpoint_status;
                }
            }
        }
    }
}
