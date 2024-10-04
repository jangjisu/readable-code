package cleancode.mission.tobe;

import java.util.List;

public class mission2 {
    private static Log log;

    public boolean validateOrder(Order order) {
        if (order.getItem().isEmpty()) {
            log.info("주문 항목이 없습니다.");
            return false;
        }

        if (order.getTotalPrice() < 0) {
            log.info("올바르지 않은 총 가격입니다.");
            return false;
        }

        return order.hasCustomerInfo();
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
