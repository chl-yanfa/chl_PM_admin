$(document).ready(function(){
	//初始化表格数据
	initDateGrid();
	reLoadData(0);
});
var currentStatus='';
//初始化数据表格
function initDateGrid(){
	$("#list").datagrid({
    	loadMsg : "数据加载中,请稍后……",
        nowrap : false,/*不换行*/
        striped : true,
        fit : false,
        fitColumns : true,
        url : URLMODULE.getAuctionSaveList,
        method : 'post',
        autoLoad:false,
        idField : 'id',
        view:tcwOrderInfoView,
        rowTitleFormatter:function(row){
        	return "拍品编码："+row['auctionNo'];
        },
        columns : [[
			{title : '拍品基本信息',field : 'carCode',width : 220,align : 'left',halign : 'center',formatter : function(value, rec, index) {
				return carHeadInfo(rec);
			}},
			{title : '拍品类型',field : 'auctionTypeFlag',width : 60,align : 'center',formatter : function(value, row, index) {
				if(value == 0){
					return '全车配件';
				}else if(value == 1){
					return '高价值配件';
				}else{
					return '大宗配件';
				}
			}},
			{title : '保存时间',field : 'saveTime',width : 60,align : 'center'},
			{title : '操作',field : 'id',width : 60,align : 'center',formatter : function(value, row, index) {
				var auctionId = value;
				var optionStr = '';
				var editor = "editor('"+ auctionId + "')";
				var deleteRow = "deleteRow('"+ auctionId + "')";
				optionStr += '<a href="javascript:void(0);" style="font-size: 14px;color: #4EA2FF;" onclick="'+ editor+ '">编辑</a>&nbsp;&nbsp;';
				optionStr += '<a href="javascript:void(0);" style="font-size: 14px;color: #4EA2FF;" onclick="'+ deleteRow+ '">删除</a>';
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
		$('#tb-paimaiDateEnd').datetimebox('clear');
		$('#searchText').searchbox('clear');
		reLoadData(0);
	});
	
	
}
//重新加载表格数据
function reLoadData(page){
	var loadType = page==1?"reload":"load";
	$("#list").datagrid(loadType, {
		auctionType:$('#tb-auctionType').combobox('getValue'),
        paimaiDateStart: $('#tb-paimaiDateStart').datetimebox("getValue"),
		paimaiDateEnd: $('#tb-paimaiDateEnd').datetimebox("getValue"),
		keywords: $('#searchText').searchbox('getValue').trim()
    });   
}
//编辑
function editor(carCode) {
	window.location.href="../../html/auction/editorSave.html?id="+carCode;
}
//删除
function deleteRow(carcode) {
	$.messager.confirm('提示', '确定删除吗?', function(r){
		if (r){
			$.ajax({type: 'post', url:'../../auction/deleteAuction',
				data:{id : carcode},
				success:function(data){
					$.messager.alert('提示', "删除成功");
					reLoadData(1);
				},
				error: function(e) {
					$.messager.alert('提示', "删除失败");
				}
			});
		}
	});
}