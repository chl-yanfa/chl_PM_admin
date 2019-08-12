//拍品管理
$(document).ready(function(){
	//初始化表格数据
	initDateGrid();
	reLoadData(0);
});
//初始化数据表格
function initDateGrid(){
	$("#list").datagrid({
		loadMsg : "数据加载中,请稍后……",
        nowrap : true,
        striped : true,
        fitColumns : true,
        url : URLMODULE.getPmAuctionList,
        method : 'post',
        autoLoad:false,
        view:tcwOrderInfoView,
        rowTitleCheckboxFormatter:function(row){
        	return "拍品编码："+row['auctionNo']+"<span style='margin-left:30px'/>";
        },
        columns : [[
            {title : '拍品基本信息', field : 'carCode', width : 200, align : 'left',halign:'center',formatter: function(value,rec,index){
            	return carHeadInfo(rec);
            }},
            {title:'手续状态',field:'procedureState',width:60,align:'center'},
            {title:'库存状态', field : 'stockState', width : 60,align:'center'},
            {title:'拍品状态',field:'vehicleState',width:60,align:'center'},
            {title : '起拍价', field : 'startingPrice', width : 60,align:'center'}
        ]],
        toolbar : toolbarType,
        pagination : true,
        singleSelect : true
    });
	//拍品类型
	$('#tb-auctionType').combobox({
		editable : false,
    	width : 150,
		valueField:'id',
		textField:'value',
		data: [{id:"0",value: "全车配件"},{id:"1",value: "高价值配件"},{id:"2",value: "大宗配件"}],
		panelHeight:"auto"
	});
	$('#searchText').searchbox({
	   width :222,
	   prompt : '品牌型号/车牌号',
	   searcher:function(value,name){
		   $('#searchText').searchbox("setValue",value.toUpperCase());
		   reLoadData(0);
	   }
	});
	$('#iconSearch').click(function() {
		reLoadData(0);
	});
	$('#iconReset').click(function() {
		$('#tb-auctionType').combobox('clear');
		$('#searchText').searchbox('clear');
		reLoadData(0);
	});
	$('#submitCheck').click(function() {
		submitCheck();
	});
	$('#cancelCheck').click(function() {
		cancelCheck();
	});
}

//重新加载表格数据
function reLoadData(type){
	var loadType = type==1?"reload":"load";
	$("#list").datagrid(loadType, {
		auctionType:$('#tb-auctionType').combobox('getValue'),
		keywords: $('#searchText').searchbox('getValue').trim()
  });
}
function submitCheck(){
	var paimaiId = getParamValue("id");
	var arr = new Array();
    $("input[name='auctionCarBox']:checked").each(function(i){
        arr[i] = $(this).val();
    });
    if(arr.length==0){
    	return;
    }
    var vals = arr.join(",");
    var paimaiId = getParamValue("id");
    $.post('../../paimai/addAuctionCarList',{
		id : paimaiId,
		selAuctionCarStr:vals
	},function(res) {
		if(res.returnCode=='000000'){
			$.messager.alert('提示', "选择成功",'info',function(){
				window.history.back(-1);
				window.parent.scrollTo(0, 0);
			});
		}
	});
}
function cancelCheck(){
	var paimaiId = getParamValue("id");
	window.history.back(-1);
	window.parent.scrollTo(0, 0);
}
