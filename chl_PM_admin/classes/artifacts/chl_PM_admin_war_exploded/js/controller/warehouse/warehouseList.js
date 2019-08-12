//库存列表
$(document).ready(function() {
	// 初始化表格数据
	initDateGrid();
	// 初始化Tab页数据
	initTabs();
});
// 初始化数据表格
function initDateGrid() {
	$("#wareList").datagrid({
		loadMsg : "数据加载中,请稍后……",
		nowrap : false,
		striped : true,
		fit : false,
		fitColumns : true,
		url : URLMODULE.getWarehouseList,
		method : 'post',
		autoLoad : false,
		idField : 'id',
		view : tcwOrderInfoView,
		rowTitleFormatter : function(row) {
			return "拍品编码：" + row['auctionNo'];
		},
		columns : [[
			{title : '拍品基本信息',field : 'carCode',width : 280,align : 'left',halign : 'center',formatter : function(value, rec, index) {
				return carHeadInfo(rec);
			}},
			{title : '入库状态',field : 'isWarehouse',width : 60,align : 'center'},
			{title : '入库时间',field : 'inStockDate',width : 100,align : 'center'},
			{title : '出库时间',field : 'outStockDate',width : 100,align : 'center'},
			{title : '钥匙状态',field : 'keyState',width : 60,align : 'center'},
			{title : '车牌状态',field : 'carCardState',width : 60,align : 'center'},
			{title : '操作',field : 'id',width : 60,align : 'center',formatter : function(value, row, index) {
				var auctionId = row.auctionId;
				var stockId = row.stockId;
				var carModel = row.drivingBrand;
				var licenseNum = '';
				if (row.hasLicense == "有牌") {
					licenseNum = row.licenseNumber;
				} else if (row.hasLicense != null) {
					licenseNum = row.hasLicense;
				} else {
					licenseNum = "车牌未提供"
				}
				var warehouseDetail = "warehouseDetail('" + auctionId + "')";/*查看*/
				var addWarehouse = "addWarehouse('"+ auctionId + "')";/*入库*/
				/*出库*/
				var openOutWarehouse = "openOutWarehouse('"+ stockId + "','" + carModel+ "','" + licenseNum + "')";
				/*异常出库（需要申请）*/
				var openOutAbnormal = "openOutAbnormal('"+ stockId+ "','"+ carModel+ "','" + licenseNum + "')";
				/*车辆申请出库审核*/
				var outWarehouseAudit = "outWarehouseAudit('"+ auctionId + "','"+ stockId + "')";
				var styleStr='href="javascript:void(0);" style="font-size: 14px;color: #4EA2FF;"';
				var optionStr = '';
				optionStr += '<a '+styleStr+' onclick="'+ warehouseDetail+ '">查看</a><br>';
				if(row.warehouseFlag==0){
					optionStr += '<a '+styleStr+' onclick="'+ addWarehouse+ '">入库</a><br>';					
				}
				if(row.warehouseFlag==1){
					optionStr += '<a '+styleStr+' onclick="'+ openOutWarehouse+ '">出库</a><br>';					
					optionStr += '<a '+styleStr+' onclick="'+ openOutAbnormal+ '">申请出库</a><br>';
					optionStr += '<a '+styleStr+' onclick="'+ outWarehouseAudit+ '">出库审核</a>';
				}
				return optionStr;
			}} 
		]],
		toolbar : toolbarType,
		pagination : true,
		singleSelect : true,
		fitStyle : true
	});

	// 拍品类型
	$('#tb-auctionType').combobox({
		editable : false,
		width : 150,
		valueField : 'id',
		textField : 'value',
		data : [{id : "0",value : "车辆"}, {id : "1",value : "物资"}, {id :"2",value : "其他"}],
		panelHeight : "auto"
	});
	// 分公司审核状态
	$('#tb-subCompanyAudit').combobox({
		editable : false,
		width : 150,
		valueField : 'id',
		textField : 'value',
		data : [{id :"0",value : "待审核"}, {id : "1",value : "审核通过"}, {id :"2",value : "已驳回"}],
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
	// 入库时间
	$('#tb-inStockDate').datebox({
		editable : false,
		width : 180
	});
	// 出库时间
	$('#tb-outStockDate').datebox({
		editable : false,
		width : 180
	});
	$('#searchText').searchbox({
		width : 222,
		prompt : '品牌型号/车牌号',
		searcher : function(value, name) {
			$('#searchText').searchbox("setValue", value.toUpperCase());
		}
	});
	$('#iconSearch').click(function() {
		updateTab(0);
	});
	$('#iconReset').click(function() {
		$('#tb-auctionType').combobox('clear');
		$('#tb-subCompanyAudit').combobox('clear');
		$('#tb-paimaiDateStart').datetimebox('clear');
		$('#tb-paimaiDateEnd').datetimebox('clear');
		$('#tb-inStockDate').datebox('clear');
		$('#tb-outStockDate').datebox('clear');
		$('#searchText').searchbox('clear');
		updateTab(0);
	});
}
function reLoadData(type) {
	updateTab(type);
}
//重新加载表格数据
function initReLoadData(type) {
	var loadType = type == 1 ? "reload" : "load";
	$("#wareList").datagrid(loadType, {
		auctionType:$('#tb-auctionType').combobox('getValue'),
		subCompanyAuditId:$('#tb-subCompanyAudit').combobox('getValue'),
		paimaiDateStart : $('#tb-paimaiDateStart').datetimebox("getValue"),
		paimaiDateEnd : $('#tb-paimaiDateEnd').datetimebox("getValue"),
		inStockDate : $('#tb-inStockDate').datebox("getValue"),
		outStockDate : $('#tb-outStockDate').datebox("getValue"),
		keywords: $('#searchText').searchbox('getValue').trim(),
		stockState:currentStatus
	});
}
var currentStatus='';
//拍品状态
var TabsStatus = {0 :'全部',1 :'待入库',2 :'已入库',3 :'已出库',4 :'待审核',5 :'已驳回'};
// 初始化Tab页
function initTabs() {
	$.post(URLMODULE.getWarehouseTab + '?_' + Math.random(),function(data) {
		for ( var key in TabsStatus) {
			var count = getATabCountNumber(data, key);// key即为tabsStatus编号
			if (count != null) {
				$("#tabs").tabs('add',{
					id : key,
					title : TabsStatus[key]+ '&nbsp;<font color="#fb6f00">' + count+ '</font>',
					closable : false,
					selected : key == '0' ? true : false
				});
			}
		}
	});
	$('#tabs').tabs({
		onSelect : function(title, index) {
			currentStatus = stateToIndex[title.substring(0, title.indexOf('&'))];
			currentStatus = currentStatus == '-1' ? '': stateToIndex[title.substring(0, title.indexOf('&'))];
			initReLoadData(0);
		}
	});
}
// 获取Tab页数据
function getATabCountNumber(data, status) {
	var count = null;
	for (var i = 0; i < data.length; i++) {
		if (data[i].stock_state == status) {// data数据与indexToStatus关联
			count = data[i].TAB_COUNT;
			break;
		}
	}
	return count;
}
var stateToIndex = { '全部' : -1, '待入库' : 0, '已入库' : 1, '已出库' : 2, '待审核' : 1, '已驳回' : 3 };
// 选择标签
// 更新Tab页
function updateTab(type) {
	$.post(URLMODULE.getWarehouseTab + '?_' + Math.random(), {
		auctionType:$('#tb-auctionType').combobox('getValue'),
		subCompanyAuditId:$('#tb-subCompanyAudit').combobox('getValue'),
		paimaiDateStart : $('#tb-paimaiDateStart').datetimebox("getValue"),
		paimaiDateEnd : $('#tb-paimaiDateEnd').datetimebox("getValue"),
		inStockDate : $('#tb-inStockDate').datebox("getValue"),
		outStockDate : $('#tb-outStockDate').datebox("getValue"),
		keywords: $('#searchText').searchbox('getValue').trim()
	}, function(data) {
		var tabs = $('#tabs').tabs('tabs');
		var index = 0;
		for ( var key in TabsStatus) {
			var count = getATabCountNumber(data, key);
			if (count != null) {
				$("#tabs").tabs('update',{
					tab : tabs[index],
					options : {
						title : TabsStatus[key]
								+ '&nbsp;<font color="#fb6f00">'
								+ count + '</font>'
					}
				});
				index = index + 1;
			}
		}
		initReLoadData(type);
	});
}

function reLoadData(type) {
	updateTab(type);
}
function initOutCombobox(){
	//车辆出库时间
	$('#carPlacingTime').datebox({
		required : false,
		editable : false
	});
	//钥匙出库时间
	$('#keyPlacingTime').datebox({
		required : false,
		editable : false
	});
	//车牌出库时间
	$('#licensePlacingTime').datebox({
		required : false,
		editable : false
	});
}
// 查看
function warehouseDetail(auctionId) {
	window.location.href = "../../html/warehouse/warehouseDetail.html?id=" + auctionId+"&type=1";
}
// 入库
function addWarehouse(auctionId) {
	window.location.href = "../../html/warehouse/addWarehouse.html?id="+ auctionId+"&type=1";
}
/*出库-弹窗*/
function openOutWarehouse(stockId, drivingBrand, licenseNum) {
	$('#OUT-DIALOG').dialog({
		title : "办理出库",
		width : 600,
		height : 600,
		resizable : true,
		modal : true,
		href : "../../html/warehouse/outWarehouse.html",
		buttons : 
			[{id : 'submitFormBtn',text : '确认出库',handler : function() {
				outWarehouse("OUT-DIALOG");
			}}, {id : 'submitFormBtn',text : '取消',handler : function() {
				$('#OUT-DIALOG').dialog("close");
			}}
		],
		onLoad : function() {
			initOutCombobox();
			var data = new Object();
			data.carModel = drivingBrand;
			data.licenseNum = licenseNum;
			data.stockId = stockId;
			$("#outWarehouse").form('load', data);
		},
		onClose : function() {
			reLoadData(1);
		}

	});
}
// 出库提交
function outWarehouse(_dialogId) {
	if (!$('#outWarehouse').form("validate")) {
		return;
	}
	var ff =$('#outWarehouse input,#outWarehouse textarea').serialize();
	$.ajax({
		type : 'post',
		url : '../../warehouse/outOfWarehouse',
		dataType : 'json',
		data : ff,
		success : function(data) {
			if (data.returnCode == "000000") {
				$('#' + _dialogId).dialog("close");
				$.messager.alert('提示','出库成功','info',function() {
					window.location.href = "../../html/warehouse/warehouseList.html";
				});
			} else {
				$.messager.alert('提示', "请先打印提货单",'info',function() {
					window.location.href = "../../html/warehouse/warehouseList.html";
				});
			}
		},
		error : function(e) {
			$.messager.alert('提示', "失败");
		}
	});
}
/*异常出库（需要申请）-弹窗*/
function openOutAbnormal(stockId, drivingBrand, licenseNum) {
	$('#OUT-DIALOG').dialog({
		title : "出库申请",
		width : 600,
		height : 500,
		resizable : true,
		modal : true,
		href : "../../html/warehouse/outWarehouseAbnormal.html",
		buttons : [
			{id : 'submitFormBtn',text : '申请出库',handler : function() {
				outWarehouseAbnormal("OUT-DIALOG");
			}}, {id : 'submitFormBtn',text : '取消',handler : function() {
				$('#OUT-DIALOG').dialog("close");
			}} 
		],
		onLoad : function() {
			initOutCombobox();
			var data = new Object();
			data.carModel = drivingBrand;
			data.licenseNum = licenseNum;
			data.stockId = stockId;
			$("#applyStore form").form('load', data);
		},
		onClose : function() {
			reLoadData(1);
		}

	});
}
//申请出库提交
function outWarehouseAbnormal(_dialogId) {
	if (!$('#outWarehouseAbnormal').form("validate")) {
		return;
	}
	var ff =$('#outWarehouseAbnormal input,#outWarehouseAbnormal textarea').serialize();
	$.ajax({
		type : 'post',
		url : '../../warehouse/outOfWarehouseAbnormal',
		dataType : 'json',
		data :ff,
		success : function(data) {
			if (data.returnCode == "000000") {
				$('#' + _dialogId).dialog("close");
				$.messager.alert('提示','申请出库成功','info',function() {
					window.location.href = "../../html/warehouse/warehouseList.html";
				});
			} else {
				$.messager.alert('提示', "申请出库失败");
			}
		},
		error : function(e) {
			$.messager.alert('提示', "失败");
		}
	});
}
//车辆出库申请审核
function outWarehouseAudit(auctionId,stockId) {
	window.location.href = "../../html/warehouse/outWarehouseAudit.html?id="+ auctionId+"&stockId="+stockId;
}