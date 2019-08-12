/*手续统计*/
$(document).ready(function(){
	//初始化表格数据
	initDateGrid();
	reLoadData(0);
});
//初始化数据表格
function initDateGrid(){
	//ToorBar
	var toolbarType = [
		'所属区域','',{id :'tb-lotAreas'},'',
		'日期','',{id :'tb-procedureMonthStart'},'&nbsp;','--','&nbsp;',{id :'tb-procedureMonthEnd'},'',
		{text : '查询',id : 'iconSearch'}
	];
	
	$("#procedureStatisticsList").datagrid({
    	loadMsg : "数据加载中,请稍后……",
        nowrap : true,
        striped : true,
        fit : false,
        fitColumns : true,
        url : URLMODULE.getProcedureStatistics,
        method : 'post',
        autoLoad:false,
        idField : 'id',
        view:tcwOrderInfoView,
        columns : [[
    	    {title:'日期',field:'procedureMonth',width:60,align:'center'},
            {title : '车辆总数', field : 'auctionCount', width : 60, align : 'center'},
            {title : '成交总数', field : 'dealCount', width : 60, align : 'center'},
            {title : '未过户', field : 'unTransOwnerCount', width : 60,align:'center'},
            {title : '已过户', field : 'transOwnerCount', width : 60,align:'center'},
            {title:'已归档',field:'recordCount',width:60,align:'center'},
            {title:'未归档',field:'unRecordCount',width:60,align:'center'},
        ]],
        toolbar : toolbarType,
        pagination : true,
        singleSelect : true,
        fitStyle : true
    });
	//初始化所属分公司
	$('#tb-lotAreas').combobox({
		multiple : false,
		editable : false,
		width : 150,
		method : 'get',
		url : '../../areas',
		valueField : 'id',
		textField : 'areasName'
	});
	//日期
	$('#tb-procedureMonthStart').datebox({
		editable : false,
		width : 180
	});
	$('#tb-procedureMonthEnd').datebox({
		editable : false,
		width : 180
	});
	$('#iconSearch').click(function() {
		reLoadData(0);
	});
}
//重新加载表格数据
function reLoadData(page){
	var loadType = page==1?"reload":"load";
	$("#procedureStatisticsList").datagrid(loadType, {
		lotAreasId: $('#tb-lotAreas').combobox('getValue'),
		procedureMonthStart: $('#tb-procedureMonthStart').datebox("getValue"),
		procedureMonthEnd: $('#tb-procedureMonthEnd').datebox("getValue")
    });
	$('#tb-lotAreas').combobox('clear');
	$('#tb-procedureMonthStart').datebox('clear');
	$('#tb-procedureMonthEnd').datebox('clear');
}
