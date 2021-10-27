package com.user.staticdata;


public enum TokenType {
    ACCESS_TOKEN { //0
        public String toString() {
            return "ACCESS_TOKEN";
        }
    },
    REFRESH_TOKEN { // 1
        public String toString() {
            return "REFRESH_TOKEN";
        }
    }
}
