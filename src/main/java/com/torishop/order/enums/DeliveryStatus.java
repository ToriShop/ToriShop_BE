package com.torishop.order.enums;

public enum DeliveryStatus {
    PENDING,    // 주문이 접수되고 처리를 기다리는 상태
    PROCESSING, // 주문이 확인되고 배송 준비 중인 상태
    SHIPPED,    // 주문이 배송업체에 인계된 상태
    DELIVERING, // 배송이 진행 중인 상태
    DELIVERED,  // 주문이 수령인에게 배송 완료된 상태
    CANCELED,   // 주문이 취소된 상태
    RETURNED    // 주문이 반송된 상태
}
