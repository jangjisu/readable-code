package cleancode.mission.tobe;

import java.util.List;

public class mission2advance {
    private static Log log;

    public boolean validateOrder(Order order) {
        if (orderHasNoItem(order)) {
            log.info("주문 항목이 없습니다.");
            return false;
        }

        if (orderHasNoCustomerInfo(order)) {
            log.info("사용자 정보가 없습니다.");
            return false;
        }

        if (orderHasInvalidTotalPrice(order)) {
            log.info("올바르지 않은 총 가격입니다.");
            return false;
        }

        return true;
    }

    private static boolean orderHasNoCustomerInfo(Order order) {
        return !order.hasCustomerInfo();
    }

    private static boolean orderHasInvalidTotalPrice(Order order) {
        return order.getTotalPrice() < 0;
    }

    private static boolean orderHasNoItem(Order order) {
        return order.getItem().isEmpty();
    }

    public class Order {
        private List<String> item;

        public List<String> getItem() {
            return this.item;
        }

        public int getTotalPrice() {
            return 0;
        }

        public boolean hasCustomerInfo() {
            return false;
        }
    }

    public class Log {
        public void info(String log) {

        }
    }
}
