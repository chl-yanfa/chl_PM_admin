//流拍管理列表页
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
        fit : false,
        fitColumns : true,
        url : URLMODULE.getBreachList,
        method : 'post',
        autoLoad:false,
        idField : 'id',
        view:tcwOrderInfoView,
        rowTitleFormatter:function(row){
        	return "拍品编码："+row['auctionNo'];
        },
        columns : [[
                {title : '拍品基本信息', field : 'carCode', width : 260, align : 'left',halign:'center',formatter: function(value,rec,index){
                	return carHeadInfo(rec);
                }},
                {title : '付款状态', field : 'payState',width:60,align:'center'},
                {title : '提货状态', field : 'takeCarState', width : 60,align:'center'},
                {title : '拍品状态', field : 'vehicleState',width:60,align:'center'},
                {title : '操作', field : 'id', width : 60, align : 'center',formatter:function(value,row,index){
					var auctionId = value;
					var auctionSetId = row.auctionSetId;
					var abortiveState = row.abortiveStateFlag;
					var auctionAgain="auctionAgain('"+auctionId+"')";
					var refund="refund('"+auctionId+"','"+auctionSetId+"')";
					var optionStr = '';
					var styleStr='href="javascript:void(0);" style="font-size: 14px;color: #4EA2FF;"';
					optionStr += '<a '+styleStr+' onclick="'+auctionAgain+'">再次拍卖</a><br/>';
					optionStr += '<a '+styleStr+' onclick="'+refund+'">退款跟踪</a>';
					return optionStr;
               }}
              ]],
        toolbar : toolbarType,
        pagination : true,
        singleSelect : true,
        fitStyle : true
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
	   width :280,
	   prompt : '车牌号/品牌型号',
	   searcher:function(value,name){
		   $('#searchText').searchbox("setValue",value.toUpperCase());
		   updateTab(0);
	   }
	});
	//自动将搜索框中的小写字母转成大写
	$("input.searchbox-text").change(function(){
		$(this).val($(this).val().toUpperCase());
	});
	$('#iconSearch').click(function() {
		reLoadData(0);
	});
	$('#iconReset').click(function() {
		$('#tb-auctionType').combobox('clear');
		$('#searchText').searchbox('clear');
		reLoadData(0);
	});
}
//重新加载表格数据
function reLoadData(page){
	var loadType = page==1?"reload":"load";
	$("#list").datagrid(loadType, {
		auctionType:$('#tb-auctionType').combobox('getValue'),
		keywords: $('#searchText').searchbox('getValue').trim()
    });
}

function refund(auctionId,auctionSetId){
	window.location.href="../../html/aftersale/refund.html?auctionId="+auctionId+"&auctionSetId="+auctionSetId;
}
function auctionAgain(auctionId){
	window.location.href="../../html/aftersale/auctionAgain.html?auctionId="+auctionId+"&type=TC";
}
