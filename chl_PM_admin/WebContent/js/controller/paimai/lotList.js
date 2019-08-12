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
        nowrap : false,
        striped : true,
        fit : false,
        fitColumns : true,
        url : URLMODULE.getPmAuctionList,
        method : 'post',
        autoLoad:false,
        idField : 'id',
        view:tcwOrderInfoView,
        rowTitleFormatter:function(row){
        	return "拍品编码："+row['auctionNo'];
        },
        columns : [[
            {title : '拍品基本信息', field : 'carCode', width : 350, align : 'left',halign:'center',formatter: function(value,rec,index){
            	return carHeadInfo(rec);
            }},
            {title:'拍品状态',field:'vehicleState',width:100,align:'center'},
            {title : '起拍价', field : 'startingPrice', width : 90,align:'center',formatter:function(value,row,index){
            	if(value!=null){
            		return value+'元';
            	}
            }},
            {title : '操作', field : 'auctionId', width : 150, align : 'center',formatter:function(value,row,index){
            	var editor="editor('"+value+"')";/*编辑*/
            	var deleteThis="deleteThis('"+value+"')";/*删除*/
            	var auctionSet="auctionSet('"+value+"')";/*参拍设置*/
            	var styleStr='href="javascript:void(0);" style="font-size: 14px;color: #4EA2FF;"';
            	var optionStr = '';
            	optionStr += '<a '+styleStr+' onclick="'+editor+'">编辑</a>&nbsp;&nbsp;';
            	optionStr += '<a '+styleStr+' onclick="'+deleteThis+'">删除</a><br/>';
            	optionStr += '<a '+styleStr+' onclick="'+auctionSet+'">查看参拍设置</a>';
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
	//拍卖时间
	$('#tb-paimaiDate').datetimebox({
		editable : false,
		width : 180,
		showSeconds : false
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
		$('#tb-paimaiDate').datetimebox('clear');
		$('#searchText').searchbox('clear');
		reLoadData(0);
	});
}
//重新加载表格数据
function reLoadData(type){
	var loadType = type==1?"reload":"load";
	$("#list").datagrid(loadType, {
		auctionType:$('#tb-auctionType').combobox('getValue'),
		paimaiDate: $('#tb-paimaiDate').datetimebox("getValue"),
		keywords: $('#searchText').searchbox('getValue').trim()
  });
}
//编辑
function editor(carCode) {
	window.location.href="../../html/auction/editorAuction.html?id="+carCode;
}
function deleteThis(auctionId) {
	$.messager.confirm('提示', '确定删除吗?', function(r){
		if (r){
			$.post('../../paimai/deleteAuction',{
				auctionId : auctionId
			},function(data) {
				$.messager.alert('提示', "删除成功");
				reLoadData(1);
			});
		}
	});
}

/*参拍设置*/
function auctionSet(auctionId){
	window.location.href="../../html/auction/auctionSet.html?id="+auctionId;
}
