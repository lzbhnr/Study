import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.locks.Lock;

public class TestAli {

    /**
     * 业务背景
     * 在一组商品数据中，需要根据每个商品的特点进行广告投放推荐。你需要实现这个方法，方法的输入是ItemRecDto的List，
     * ItemRecDto里面有商品id和3个指标，这些字段输入的时候就有值了。有一个推荐理由tips在输入时为null，需要你在方法中设置
     * <p>
     * 业务规则
     * 1. 商品的每个指标在输入的这一组商品中会有一个排名，指标数值越大排名越靠前。
     * 2. 每个商品都有3个指标，对应3个排名，根据3个排名中最靠前的那个指标类型，给该商品设置相应的推荐理由。
     * 如果ipv 排名最靠前，推荐理由是“拿量能力较高”
     * 如果wcvr排名最靠前，推荐理由是“蓄水能力较高”
     * 如果cvr 排名最靠前，推荐理由是“转化能力较高”
     * <p>
     * 特殊情况处理
     * 1. 如果多个商品的同一个指标数值一样，那么排名按ItemRecDto在List中的先后次序。
     * 2. 如果某个商品的各指标排名有一样的情况，那么按 转化(cvr)、蓄水(wcvr)、拿量(ipv) 的优先级设置推荐理由
     * <p>
     * 例如：
     * 输入：
     * itemId ipv wcvr cvr  tips
     * 1001   10  3.1  4.9  null
     * 1002   20  3.1  4.2  null
     * 1003   20  2.6  6.3  null
     * 1004   70  9.9  9.9  null
     * <p>
     * 方法结束时：
     * itemId ipv wcvr cvr  tips
     * 1001   10  3.1  4.9  蓄水能力较高
     * 1002   20  3.1  4.2  拿量能力较高
     * 1003   20  2.6  6.3  转化能力较高
     * 1004   70  9.9  9.9  转化能力较高
     * <p>
     *
     * 转化(cvr)、蓄水(wcvr)、拿量(ipv)
     * 解释：
     * 以itemId=1002的商品为例，ipv=20，仅次于1004号商品的70，虽然1003号商品的ipv也是20，但1002号在itemRecDtoList中出现更早，
     * 所以认为1002排名更靠前，ipv的排名是第2。同理，1002号商品的wcvr排名是第3，cvr排名是第4。
     * 1002号商品的3个排名是2，3，4。其中ipv排名第2是最靠前的，表现在3个指标里面最好，所以推荐理由是“拿量能力较高”
     * <p>
     * 要求
     * 1. 正确实现业务规则，代码具备良好的可维护性和可扩展性
     * 2. 可以新增方法，新增类。Java版本1.8，可以使用Lombok
     * 3. 时间40分钟内，可以把题目粘贴到本地IDE编写代码
     */
    static void setItemRecTips(List<ItemRecDto> itemRecDtoList) {

        PriorityQueue<ipv> listipv = new PriorityQueue();
        PriorityQueue<wcvr> listwcvr = new PriorityQueue();
        PriorityQueue<cvr> listcvr = new PriorityQueue();

        for (int i = 0; i < itemRecDtoList.size(); i++) {
            listipv.add(new ipv(itemRecDtoList.get(i), i));
            listwcvr.add(new wcvr(itemRecDtoList.get(i), i));
            listcvr.add(new cvr(itemRecDtoList.get(i), i));
        }

        listipv.stream().forEach(
                o-> System.out.println(o.getDto().getItemId())
        );

        System.out.println("ipv---------");
        listwcvr.stream().forEach(
                o-> System.out.println(o.getDto().getItemId())
        );
        System.out.println("wcvr---------");
        listcvr.stream().forEach(
                o-> System.out.println(o.getDto().getItemId())
        );
        System.out.println("cvr---------");
        Set<Long> iterm = new HashSet<>();

        for (int i = 0; i < itemRecDtoList.size(); i++) {
            ipv ipv = listipv.poll();

            Long i1 = ipv.getDto().getItemId();

            cvr cvr = listcvr.poll();
            if (!iterm.contains(cvr.getDto().getItemId())) {
                cvr.getDto().setTips("转化能力较高");
                iterm.add(cvr.getDto().getItemId());
                System.out.println(i + "--cvr-" + cvr.getDto().getItemId() +"-----" );

            }



            wcvr wcvr = listwcvr.poll();
            if (!iterm.contains(wcvr.getDto().getItemId())) {
                wcvr.getDto().setTips("蓄水能力较高");
                iterm.add(wcvr.getDto().getItemId());
                System.out.println(i + "--wcvr-" + wcvr.getDto().getItemId() +"-----" );
            }


            if (!iterm.contains(i1)) {
                ipv.getDto().setTips("拿量能力较高");
                iterm.add(i1);
                System.out.println(i + "--ipv-" + i1 +"-----" );
            }


        }


    }


    static class ipv implements Comparable<ipv> {
        ItemRecDto dto;
        private int index;

        public ipv(ItemRecDto dto, int index) {
            this.dto = dto;
            this.index = index;
        }

        public ItemRecDto getDto() {
            return dto;
        }

        public void setDto(ItemRecDto dto) {
            this.dto = dto;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public int compareTo(ipv o) {
            if (o.getDto().getIpv().equals(this.getDto().getIpv())) {
                return -o.getIndex() + this.getIndex();
            } else {
                return -this.getDto().getIpv().compareTo(o.getDto().getIpv());
            }
        }
    }

    static class wcvr implements Comparable<wcvr> {
        ItemRecDto dto;
        private int index;

        public wcvr(ItemRecDto dto, int index) {
            this.dto = dto;
            this.index = index;
        }

        public ItemRecDto getDto() {
            return dto;
        }

        public void setDto(ItemRecDto dto) {
            this.dto = dto;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public int compareTo(wcvr o) {
            if (o.getDto().getWcvr().equals(this.getDto().getWcvr())) {
                return -o.getIndex() + this.getIndex();
            } else {
                return -this.getDto().getWcvr().compareTo(o.getDto().getWcvr());
            }
        }
    }

    static class cvr implements Comparable<cvr> {
        ItemRecDto dto;
        private int index;

        public ItemRecDto getDto() {
            return dto;
        }

        public void setDto(ItemRecDto dto) {
            this.dto = dto;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        // 大的在前面
        @Override
        public int compareTo(cvr o) {
            if (o.getDto().getCvr().equals(this.getDto().getCvr())) {
                return -o.getIndex() + this.getIndex();
            } else {
                return -this.getDto().getCvr().compareTo(o.getDto().getCvr());
            }
        }

        public cvr(ItemRecDto dto, int index) {
            this.dto = dto;
            this.index = index;
        }
    }

    static class ItemRecDto {
        @Override
        public String toString() {
            return "ItemRecDto{" +
                    "itemId=" + itemId +
                    ", ipv=" + ipv +
                    ", wcvr=" + wcvr +
                    ", cvr=" + cvr +
                    ", tips='" + tips + '\'' +
                    '}';
        }

        Long itemId; // 商品id
        Long ipv; // 拿量指标
        BigDecimal wcvr; // 蓄水指标
        BigDecimal cvr; // 转化指标
        String tips; // 推荐理由

        ItemRecDto(Long itemId, Long ipv, BigDecimal wcvr, BigDecimal cvr) {
            this.itemId = itemId;
            this.ipv = ipv;
            this.wcvr = wcvr;
            this.cvr = cvr;
        }

        public Long getItemId() {
            return itemId;
        }

        public void setItemId(Long itemId) {
            this.itemId = itemId;
        }

        public Long getIpv() {
            return ipv;
        }

        public void setIpv(Long ipv) {
            this.ipv = ipv;
        }

        public BigDecimal getWcvr() {
            return wcvr;
        }

        public void setWcvr(BigDecimal wcvr) {
            this.wcvr = wcvr;
        }

        public BigDecimal getCvr() {
            return cvr;
        }

        public void setCvr(BigDecimal cvr) {
            this.cvr = cvr;
        }

        public String getTips() {
            return tips;
        }

        public void setTips(String tips) {
            this.tips = tips;
        }
    }

    public static void main(String[] args) {
        ItemRecDto d1 = new ItemRecDto(1001L, 10L, new BigDecimal("3.1"), new BigDecimal("4.9"));
        ItemRecDto d2 = new ItemRecDto(1002L, 20L, new BigDecimal("3.1"), new BigDecimal("4.2"));
        ItemRecDto d3 = new ItemRecDto(1003L, 20L, new BigDecimal("2.6"), new BigDecimal("6.3"));
        ItemRecDto d4 = new ItemRecDto(1004L, 70L, new BigDecimal("9.9"), new BigDecimal("9.9"));

        List<ItemRecDto> itemRecDtoList = Arrays.asList(d1, d2, d3, d4);
        setItemRecTips(itemRecDtoList);
        itemRecDtoList.forEach(System.out::println);


    }

}
