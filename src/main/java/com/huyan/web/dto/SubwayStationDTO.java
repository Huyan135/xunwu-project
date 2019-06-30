package com.huyan.web.dto;

/**
 * @author 胡琰
 * @date 2019/5/7 15:02
 */
public class SubwayStationDTO {

        private Long id;
        private Long subwayId;
        private String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getSubwayId() {
            return subwayId;
        }

        public void setSubwayId(Long subwayId) {
            this.subwayId = subwayId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
