//暂存入库列表
$(document).ready(function() {
	// 初始化表格数据
	initDateGrid();
	reLoadData(0);
});
var currentStatus = '';
// 初始化数据表格
function initDateGrid() {
	$("#TwareList").datagrid({
		loadMsg : "数据加载中,请稍后……",
		nowrap : true,
		striped : true,
		fit : false,
		fitColumns : true,
		url : URLMODULE.getWarehouseSaveList,
		method : 'post',
		autoLoad : false,
		idField : 'id',
		view : tcwOrderInfoView,
		rowTitleFormatter : function(row) {
			return "提交时间：" + row['saveTime']+ "<span style='margin-left:30px'/>"+"拍品编码："+ row['auctionNo']+ "<span style='margin-left:30px'/>";
		},
		columns : [[
			{title : '拍品基本信息',field : 'carCode',width : 200,align : 'left',halign : 'center',formatter : function(value, rec, index) {
				return carHeadInfo(rec);
			}},
			{title : '状态',field : 'isWarehouse',width : 60,align : 'center'},
			{title : '存放地',field : 'stockAddress',width : 120,align : 'center'},
			{title : '操作',field : 'id',width : 60,align : 'center',formatter : function(value, row, index) {
				var auctionId = row.auctionId;
				var stockId = row.stockId;
				var getDetail = "getDetail('" + auctionId + "')";
				var transToWarehouse = "transToWarehouse('"+ auctionId + "')";
				var optionStr = '';
				var styleStr='href="javascript:void(0);" style="font-size: 14px;color: #4EA2FF;"';
				optionStr += '<a '+styleStr+' onclick="'+ getDetail+ '">查看</a><br>';
				optionStr += '<a '+styleStr+' onclick="'+ transToWarehouse+ '">转为入库</a>';
				return optionStr;
			}}
		]],
		toolbar : toolbarType,
		pagination : true,
		singleSelect : true,
		fitStyle : true
	});
	// 初始化车辆存放地
	initAddressPCAS("", "#tb-province", "#tb-city", "#tb-area", false,150,25);
	// 初始化拍品类型
	$('#tb-auctionType').combobox({
		editable : false,
		width : 150,
		valueField : 'id',
		textField : 'value',
		data: [{id:"0",value: "全车配件"},{id:"1",value: "高价值配件"},{id:"2",value: "大宗配件"}],
		panelHeight : "auto"
	});
	// 拍卖时间
	$('#tb-paimaiDateStart').datetimebox({
		editable : false,
		width : 180,
		showSeconds : false
	});
	$('#tb-paimaiDateEnd').datetimebox({
		editable : false,
		width : 180,
		showSeconds : false
	});
	$('#searchText').searchbox({
		width : 222,
		prompt : '品牌型号/车牌号',
		searcher : function(value, name) {
			$('#searchText').searchbox("setValue", value.toUpperCase());
			reLoadData(0);
		}
	});
	$('#iconSearch').click(function() {
		reLoadData(0);
	});
	$('#iconReset').click(function() {
		$('#tb-auctionType').combobox('clear');
		$('#tb-paimaiDateStart').datetimebox('clear');
		$('#tb-paimaiDateEnd').datetimebox('clear');
		$('#tb-province').combobox('clear');
		$('#tb-city').combobox('clear');
		$('#tb-area').combobox('clear');
		$('#searchText').searchbox('clear');
		reLoadData(0);
	});

}
// 重新加载表格数据
function reLoadData(page) {
	var loadType = page == 1 ? "reload" : "load";
	$("#TwareList").datagrid(loadType, {
		auctionType:$('#tb-auctionType').combobox('getValue'),
		paimaiDateStart: $('#tb-paimaiDateStart').datetimebox("getValue"),
		paimaiDateEnd: $('#tb-paimaiDateEnd').datetimebox("getValue"),
		provinceId : $('#tb-province').combobox('getValue'),
		cityId : $('#tb-city').combobox('getValue'),
		areaId : $('#tb-area').combobox('getValue'),
		keywords: $('#searchText').searchbox('getValue').trim()
	});
}
//查看
function getDetail(auctionId) {
	window.location.href = "../../html/warehouse/warehouseDetail.html?id=" + auctionId+"&type=-1";
}
//转为入库
function transToWarehouse(auctionId) {
	window.location.href = "../../html/warehouse/transToWarehouse.html?id="+ auctionId+"&type=-1";
}