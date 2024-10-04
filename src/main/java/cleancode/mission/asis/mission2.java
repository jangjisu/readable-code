package cleancode.mission.asis;

import java.util.List;

public class mission2 {
    private static Log log;

    public boolean validateOrder(Order order) {
        if (order.getItem().size() == 0) {
            log.info("주문 항목이 없습니다.");
            return false;
        } else {
            if (order.getTotalPrice() > 0) {
                if (!order.hasCustomerInfo()) {
                    log.info("사용자 정보가 없습니다.");
                    return false;
                } else {
                    return true;
                }
            } else if (!(order.getTotalPrice() > 0)) {
                log.info("올바르지 않은 총 가격입니다.");
                return false;
            }
        }

        return true;
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
