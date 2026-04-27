package com.ecommerce.catalogue.domain.entity;

import java.util.List;

public class Order {
        private String orderId;
        private String status;
        private List<Item> items; 
        public Order(String orderId, String customerId, double totalAmount, String status, List<Item> items) {
            this.orderId = orderId;
            this.status = status;
            this.items = items;
        }
        public String getOrderId() {
            return orderId;
        }
        public String getStatus() {
            return status;
        }
        public List<Item> getItems() {
            return items;
        }

}
