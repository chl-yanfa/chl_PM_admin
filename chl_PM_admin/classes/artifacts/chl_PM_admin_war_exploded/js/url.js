var readImgUrl ="";
(function () {
	if (window.location.hostname === "47.104.225.242") {
		
		readImgUrl = "http://47.104.225.242";
		
	} else {
		readImgUrl = "http://localhost:8080/SDIC-Inner/resources/upload/";
	}
})();

var URLMODULE = {
		/*车辆管理*/
		getAuctionList: "../../auction/getAuctionList", //获取车辆列表
		getAuctionTab: "../../auction/getAuctionTab", 	//获取车辆列表页TAB
		getAuctionSaveList: "../../auction/getAuctionSaveList", //获取暂存车辆列表
		getScrapCarList: "../../auction/getScrapCarList", 	//获取报废车辆列表
		getScrapCarTab: "../../auction/getScrapCarTab", 	//获取报废车辆列表页TAB
		/*手续管理*/
		getProcedureList:"../../procedure/getProcedureList",//获取手续列表
		getProcedureStatistics:"../../procedure/getProcedureStatistics",//获取手续统计
		/*入库管理*/
		getWarehouseList:"../../warehouse/getWarehouseList",//获取入库列表
		getTowingCompanyList:"../../towingCompany/getTowingCompanyList",//获取拖车公司列表
		getWarehouseTab: "../../warehouse/getWarehouseTab", 	//获取仓库列表页TAB
		getTakeCarList: "../../warehouse/getTakeCarList", 	//获取打印提货单列表页
		getTowingCompanyList: "../../towingCompany/getTowingCompanyList", 	//获取拖车公司列表页
		getWarehouseSaveList: "../../warehouse/getWarehouseSaveList", 	//获取暂存入库列表页
		getStockStatistics: "../../warehouse/getStockStatistics", 	//获取库存统计列表
		/*拍卖管理*/
		getPmAuctionList:"../../paimai/getPmAuctionList", 	//拍品管理列表
		getPaimaiList:"../../paimai/getPaimaiList", 	//拍卖会列表
		getPaimaiTab:"../../paimai/getPaimaiTab", 	//拍卖会列表TAB
		/*售后管理*/
		getDealList:"../../aftersale/getDealList",//成交列表
		getDealTab:"../../aftersale/getDealTab",	//成交Tab
		getAbnormalList:"../../aftersale/getAbnormalList",//售后异常列表
		getAbnormalTab:"../../aftersale/getAbnormalTab",	//售后异常Tab
		getAbortiveList:"../../aftersale/getAbortiveList",//流拍列表
		getAbortiveTab:"../../aftersale/getAbortiveTab",	//流拍Tab
		getTransStatistics:"../../aftersale/getTransStatistics",	//流拍Tab
		getBreachList:"../../aftersale/getBreachList",	//流拍Tab
		/*财务管理*/
		getFinanceList:"../../finance/getFinanceList",
		getFinanceTab:"../../finance/getFinanceTab",
		/*用户管理*/
		getCustomerList:"../../customer/getCustomerList",
		getMarginWaterList:"../../customer/getMarginWaterList",
}
var APIMANEGER = {
		
}
