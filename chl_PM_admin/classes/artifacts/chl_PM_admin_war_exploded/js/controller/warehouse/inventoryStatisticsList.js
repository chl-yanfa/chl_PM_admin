/*库存统计*/
$(document).ready(function(){
	//初始化表格数据
	initDateGrid();
	reLoadData(0);
});
//初始化数据表格
function initDateGrid(){
	var toolbarType = [
		'所属区域','',{id :'tb-lotAreas'},'',
		{text : '查询',id : 'iconSearch'},'&nbsp;',
		{text : '重置',id : 'iconReset'}
	];
	$("#inventoryStatisticsList").datagrid({
    	loadMsg : "数据加载中,请稍后……",
        nowrap : true,
        striped : true,
        fit : false,
        fitColumns : true,
        url : URLMODULE.getStockStatistics,
        method : 'post',
        autoLoad:false,
        idField : 'id',
        view:tcwOrderInfoView,
        columns : [[
            {title : '区域名称', field : 'lotAreas', width : 120, align : 'center',halign:'center'},
            {title : '待拍车辆数', field : 'toPaimaiCount', width : 60, align : 'center'},
            {title : '拍卖中车辆数', field : 'paimaiCount', width : 60,align:'center'},
            {title : '流拍待处理车辆数', field : 'passInCount', width : 60,align:'center'},
            {title:'成交未提货辆数',field:'noTakeCarCount',width:60,align:'center'},
            {title:'未入库车辆数',field:'noWarehouseCount',width:60,align:'center'},
            {title:'统计',field:'statistics',width:60,align:'center'},
        ]],
        toolbar : toolbarType,
        pagination : true,
        singleSelect : true,
        fitStyle : true
    });
	$('#tb-lotAreas').combobox({
		multiple : false,
		editable : false,
		width : 150,
		method : 'get',
		url : '../../areas',
		valueField : 'id',
		textField : 'areasName'
	});
	$('#iconSearch').click(function() {
		reLoadData(0);
	});
	$('#iconReset').click(function() {
		$('#tb-lotAreas').combobox('clear');
		reLoadData(0);
	});
}

//重新加载表格数据
function reLoadData(page){
	var loadType = page==1?"reload":"load";
	$("#inventoryStatisticsList").datagrid(loadType, {
		lotAreasId: $('#tb-lotAreas').combobox('getValue'),
    });
}
