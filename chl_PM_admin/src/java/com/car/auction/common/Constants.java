package com.car.auction.common;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Constants {
	
	public final static String USER_COOKIEKEY = "v_v-s-ticket";

	public final static String ENABLE_Y = "Y";
	public final static String ENABLE_N = "N";
	
	public final static String STATUS_NORMAL = "1";
	public final static String STATUS_DELETE = "2";
	public final static String STATUS_FORBIDDEN = "3";

	public final static String SYS_MENU_LEVEL_ONE = "1";
    public final static String PLATFORM_ECP_CODE = "P00";
    
    public static final ObjectMapper MAPPER = new ObjectMapper();
    
    public static class MenuType {
        /**菜单T00*/
        public static String MENU = "T00";
        /**按钮T01*/
        public static String BUTN = "T01";
    }

    public static class InfoType {
		/**DEPARTMENT 部门 S000*/
		public static String DEPARTMENT = "S000";
		/**ROLE 角色 S001*/
		public static String ROLE = "S001";
		/**PLATFORM 平台 S002*/
		public static String PLATFORM = "S002";
        
    }
    public static class sendType{
    	public static final String ACCOMPLISH = "accomplish";
    }
    /**
	 * AuctionStatus 拍品状态
     */
    public static final class AuctionStatus{
    	/**SAVE 拍品暂存  0 */
    	public static final Integer SAVE = 0;
    	/**SUBMIT 拍品提交 1 */
    	public static final Integer SUBMIT = 1;
    	/**LOT_POOL 拍品池 10 */
    	public static final Integer LOT_POOL = 10;
    	/**IN_PMH 进入拍卖会 2*/
    	public static final Integer IN_PMH = 2;
    	/**PMH_TO_PUBLISH 待发布 3*/
    	public static final Integer PMH_TO_PUBLISH = 3;
    	/**PAIMAIING 拍卖中  4*/
    	public static final Integer PAIMAIING = 4;
    	/**DEAL 已成交  5*/
    	public static final Integer DEAL = 5;
    	/**FINISH 已完结 7*/
    	public static final Integer FINISH = 7;
    	/**ABORTIVE 已流拍  8*/
    	public static final Integer ABORTIVE = 8;
    	/**SCRAP 报废  -1*/
    	public static final Integer SCRAP = -1;
    	/**SECOND_ASK 二询  -8*/
    	public static final Integer SECOND_ASK = -8;
    	/**RETURNCAR_AUCTION 已退货 -10*/
    	public static final Integer RETURNCAR_AUCTION = -10;
    }
    /**
	 * AuctionAftersaleState 拍品售后状态
     */
    public static final class AuctionAftersaleState{
    	/**TO_PAY 待付款  0 */
    	public static final Integer TO_PAY = 0;
    	/**HAS_PAY 已付款 1 */
    	public static final Integer HAS_PAY = 1;
    	/**TO_TAKECAR 待提货 2 */
    	public static final Integer TO_TAKECAR = 2;
    	/**HAS_TAKECAR 已提货 3*/
    	public static final Integer HAS_TAKECAR = 3;
    	/**TO_TRANSFER 待过户 4*/
    	public static final Integer TO_TRANSFER = 4;
    	/**HAS_TRANSFER 已过户  5*/
    	public static final Integer HAS_TRANSFER = 5;
    	/**DEFER_NO_PAY 延期未付款  -1*/
    	public static final Integer DEFER_NO_PAY = -1;
    	/**DEFER_NO_TAKECAR 延期未提货 -3*/
    	public static final Integer DEFER_NO_TAKECAR = -3;
    	/**DEFER_NO_TRANSFER 延期未过户  -5*/
    	public static final Integer DEFER_NO_TRANSFER = -5;
    	/**RETURNCAR_AUCTION 已退货 -10*/
    	public static final Integer RETURNCAR_AUCTION = -10;
    }
    /**
	 * OrderStatus 订单状态
     */
    public static final class OrderStatus{
    	/**HAS_NOT_AUCTION 未上拍 0 */
    	public static final String HAS_NO_AUCTION = "0";
    	/**TO_AUCTION 待竞拍 10 */
    	public static final String TO_AUCTION = "10";
    	/**COMPETE 竞拍中 20 */
    	public static final String COMPETE = "20";
    	/**TO_COMPETE 待处理 30*/
    	public static final String TO_COMPETE = "30";
    	/**TO_SETTLE 待付款 50*/
    	public static final String TO_SETTLE = "50";
    	/**TO_ABORTIVE 流拍 40*/
    	public static final String TO_ABORTIVE = "40";
    	/**TO_TAKECAR 待提货 60*/
    	public static final String TO_TAKECAR = "60";
    	/**TO_TRANSFER 待过户 70*/
    	public static final String TO_TRANSFER = "70";
    	/**TRANSFERED 已过户 71*/
    	public static final String TRANSFERED = "71";
    	/**FINISHED 已完成 80*/
    	public static final String FINISHED = "80";
    	/**DUPLICATE_AUCTION 已复拍 -8*/
    	public static final String DUPLICATE_AUCTION = "-8";
    	/**OVERTURN_AUCTION 已撤拍 -9*/
    	public static final String OVERTURN_AUCTION = "-9";
    	/**RETURNCAR_AUCTION 已退货 -10*/
    	public static final String RETURNCAR_AUCTION = "-10";
    }
    /**
	 * FileType 文件类型
     */
    public static final class FileType{
    	/**AUCTION 拍品信息 0*/
    	public static final Integer AUCTION = 0;
    	/**PROCEDURE 手续 1*/
    	public static final Integer PROCEDURE = 1;
    	/**WAREHOUSE 入库 2*/
    	public static final Integer WAREHOUSE = 2;
    	/**AFTERSALE 售后 3*/
    	public static final Integer AFTERSALE = 3;
    	/**FINANCE 财政 4*/
    	public static final Integer FINANCE = 4;
    }
    /**
	 * ScrapOrderStatus 报废订单状态
     */
    public static final class ScrapOrderStatus{
    	/**SCRAP_CAR 整车  1 */
    	public static final Integer SCRAP_CAR = 1;
    	/**SCRAP_AUTOPARTS 旧件  2 */
    	public static final Integer SCRAP_AUTOPARTS = 2;
    	/**CAR_FINISH 整车:已完成 7 */
    	public static final Integer CAR_FINISH = 7;
    	/**AUTOPARTS_FINISH 旧件:已完成 8 */
    	public static final Integer AUTOPARTS_FINISH = 8;
    	/**ORDER_AUCTION 拍卖  91 */
    	public static final Integer ORDER_AUCTION = 91;
    	/**ORDER_DEL_AUCTION 删除拍品  92 */
    	public static final Integer ORDER_DEL_AUCTION = 92;
    }
    
    /**
	 * ProcedureState 手续出入库状态
     */
    public static final class ProcedureState{
    	/**IN_STATE 已入库 0*/
    	public static final Integer IN_STATE = 0;
    	/**OUT_STATE 已出库 1*/
    	public static final Integer OUT_STATE = 1;
    }
    /**
	 * PayWaterState 付款流水状态
     */
    public static final class PayWaterState{
    	/** 付款 pay 1*/
    	public static final Integer PAY = 1;
    	/** 退款 REFUND 2*/
    	public static final Integer REFUND = 2;
    }
    /**IS_HISTORY 历史数据 1*/
    public final static Integer IS_HISTORY = 1;
    /**NOT_HISTORY 不是历史数据0*/
	public final static Integer NOT_HISTORY = 0;
	/**IS_DELETE 已删除 1*/
	public final static Integer IS_DELETE = 1;
	/**NOT_DELETE 未删除 0*/
	public final static Integer NOT_DELETE = 0;
	
	public final static Integer HASHITERATIONS = 2;
    
}